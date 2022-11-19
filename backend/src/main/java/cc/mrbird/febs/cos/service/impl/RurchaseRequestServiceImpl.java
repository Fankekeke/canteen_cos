package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.GoodsBelong;
import cc.mrbird.febs.cos.entity.RurchaseRequest;
import cc.mrbird.febs.cos.dao.RurchaseRequestMapper;
import cc.mrbird.febs.cos.entity.StockInfo;
import cc.mrbird.febs.cos.entity.StockPut;
import cc.mrbird.febs.cos.service.IGoodsBelongService;
import cc.mrbird.febs.cos.service.IRurchaseRequestService;
import cc.mrbird.febs.cos.service.IStockInfoService;
import cc.mrbird.febs.cos.service.IStockPutService;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RurchaseRequestServiceImpl extends ServiceImpl<RurchaseRequestMapper, RurchaseRequest> implements IRurchaseRequestService {

    private final IGoodsBelongService goodsBelongService;

    private final IStockPutService stockPutService;

    private final IStockInfoService stockInfoService;

    @Override
    public IPage<LinkedHashMap<String, Object>> rurchaseRequestByPage(Page page, RurchaseRequest rurchaseRequest) {
        return baseMapper.rurchaseRequestByPage(page, rurchaseRequest);
    }

    @Override
    public Boolean rurchaseRequestAdd(RurchaseRequest rurchaseRequest) {
        rurchaseRequest.setNum("RUR-"+new Date().getTime());
        JSONArray array = JSONUtil.parseArray(rurchaseRequest.getGoods());
        List<GoodsBelong> goodsBelongList = JSONUtil.toList(array, GoodsBelong.class);
        rurchaseRequest.setStep(0);
        this.save(rurchaseRequest);
        goodsBelongList.forEach(item -> {
            item.setCreateDate(DateUtil.formatDateTime(new Date()));
            item.setNum(rurchaseRequest.getNum());
        });
        return goodsBelongService.saveBatch(goodsBelongList);
    }

    @Override
    public Boolean rurchasePut(RurchaseRequest rurchaseRequest) {
        JSONArray array = JSONUtil.parseArray(rurchaseRequest.getGoods());
        List<GoodsBelong> goodsBelongList = JSONUtil.toList(array, GoodsBelong.class);
        // 添加入库单
        StockPut stockPut = new StockPut();
        stockPut.setContent(rurchaseRequest.getRurchaseContent());
        stockPut.setCreateDate(DateUtil.formatDateTime(new Date()));
        stockPut.setCustodian(rurchaseRequest.getCustodian());
        stockPut.setPutUser(rurchaseRequest.getPutUser());
        stockPut.setPrice(rurchaseRequest.getPrice());
        stockPut.setNum("PUT-"+new Date().getTime());
        stockPutService.save(stockPut);

        goodsBelongList.forEach(item -> {
            item.setCreateDate(DateUtil.formatDateTime(new Date()));
            item.setNum(stockPut.getNum());
            // 判断库房物品是否存在
            StockInfo stockInfo = stockInfoService.getOne(Wrappers.<StockInfo>lambdaQuery().eq(StockInfo::getName, item.getName()).eq(StockInfo::getTypeId, item.getTypeId()).eq(StockInfo::getIsIn, 0));
            if (stockInfo != null) {
                // 更改库房数据
                stockInfoService.update(Wrappers.<StockInfo>lambdaUpdate().set(StockInfo::getAmount, stockInfo.getAmount()+item.getAmount())
                        .set(StockInfo::getPrice, stockInfo.getPrice())
                        .eq(StockInfo::getName, stockInfo.getName()));
            } else {
                // 重新添加库房数据
                StockInfo stock = new StockInfo();
                stock.setName(item.getName());
                stock.setAmount(item.getAmount());
                stock.setCreateDate(DateUtil.formatDateTime(new Date()));
                stock.setType(item.getType());
                stock.setTypeId(item.getTypeId());
                stock.setUnit(item.getUnit());
                stock.setPrice(item.getPrice());
                stock.setIsIn(0);
                stockInfo = stock;
                stockInfoService.save(stock);
            }
            // 添加入库记录
            StockInfo stockInfoPut = new StockInfo();
            stockInfoPut.setParentId(stockInfo.getId());
            stockInfoPut.setName(item.getName());
            stockInfoPut.setAmount(item.getAmount());
            stockInfoPut.setCreateDate(DateUtil.formatDateTime(new Date()));
            stockInfoPut.setType(item.getType());
            stockInfoPut.setTypeId(item.getTypeId());
            stockInfoPut.setUnit(item.getUnit());
            stockInfoPut.setPrice(item.getPrice());
            stockInfoPut.setIsIn(1);
            stockInfoService.save(stockInfoPut);

            // 添加所属信息
            GoodsBelong goodsBelong = new GoodsBelong();
            goodsBelong.setNum(stockPut.getNum());
            goodsBelong.setCreateDate(DateUtil.formatDateTime(new Date()));
            goodsBelong.setAmount(item.getAmount());
            goodsBelong.setName(item.getName());
            goodsBelong.setPrice(item.getPrice());
            goodsBelong.setType(item.getType());
            goodsBelong.setTypeId(item.getTypeId());
            goodsBelong.setUnit(item.getUnit());
            goodsBelongService.save(goodsBelong);
        });
        // 修改状态
        this.update(Wrappers.<RurchaseRequest>lambdaUpdate().set(RurchaseRequest::getStep, 1).eq(RurchaseRequest::getId, rurchaseRequest.getId()));
        return true;
    }
}
