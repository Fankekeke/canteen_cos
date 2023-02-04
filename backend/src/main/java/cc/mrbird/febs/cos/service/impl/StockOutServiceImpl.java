package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.ConsumableType;
import cc.mrbird.febs.cos.entity.GoodsBelong;
import cc.mrbird.febs.cos.entity.StockInfo;
import cc.mrbird.febs.cos.entity.StockOut;
import cc.mrbird.febs.cos.dao.StockOutMapper;
import cc.mrbird.febs.cos.service.IConsumableTypeService;
import cc.mrbird.febs.cos.service.IGoodsBelongService;
import cc.mrbird.febs.cos.service.IStockInfoService;
import cc.mrbird.febs.cos.service.IStockOutService;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author FanK
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StockOutServiceImpl extends ServiceImpl<StockOutMapper, StockOut> implements IStockOutService {

    private final IStockInfoService stockInfoService;

    private final IGoodsBelongService goodsBelongService;

    private final IConsumableTypeService consumableTypeService;

    @Override
    public Boolean stockOut(StockOut stockOut) {
        // 添加出库单
        stockOut.setCreateDate(DateUtil.formatDateTime(new Date()));
        stockOut.setNum("OUT-" + System.currentTimeMillis());
        this.save(stockOut);

        // 出库
        JSONArray array = JSONUtil.parseArray(stockOut.getGoods());
        List<GoodsBelong> goodsBelongList = JSONUtil.toList(array, GoodsBelong.class);
        goodsBelongList.forEach(item -> {
            StockInfo stockInfo = stockInfoService.getOne(Wrappers.<StockInfo>lambdaQuery().eq(StockInfo::getName, item.getName()).eq(StockInfo::getIsIn, 0));
            // 更改库房数据
            stockInfoService.update(Wrappers.<StockInfo>lambdaUpdate().set(StockInfo::getAmount, stockInfo.getAmount() - item.getAmount()).eq(StockInfo::getId, stockInfo.getId()));
            item.setNum(stockOut.getNum());

            // 添加出库记录
            StockInfo stockInfoOut = new StockInfo();
            stockInfoOut.setParentId(stockInfo.getId());
            stockInfoOut.setName(item.getName());
            stockInfoOut.setAmount(item.getAmount());
            stockInfoOut.setCreateDate(DateUtil.formatDateTime(new Date()));
            stockInfoOut.setType(item.getType());
            stockInfoOut.setTypeId(item.getTypeId());
            stockInfoOut.setUnit(item.getUnit());
            stockInfoOut.setPrice(item.getPrice());
            stockInfoOut.setIsIn(2);
            stockInfoOut.setToUserId(stockOut.getUserId());
            stockInfoService.save(stockInfoOut);

            // 添加所属信息
            GoodsBelong goodsBelong = new GoodsBelong();
            goodsBelong.setNum(stockOut.getNum());
            goodsBelong.setCreateDate(DateUtil.formatDateTime(new Date()));
            goodsBelong.setAmount(item.getAmount());
            goodsBelong.setName(item.getName());
            goodsBelong.setPrice(item.getPrice());
            goodsBelong.setType(item.getType());
            goodsBelong.setTypeId(item.getTypeId());
            goodsBelong.setUnit(item.getUnit());
            goodsBelongService.save(goodsBelong);
        });
        return true;
    }

    @Override
    public IPage<LinkedHashMap<String, Object>> stockOutByPage(Page page, StockOut stockOut) {
        return baseMapper.stockOutByPage(page, stockOut);
    }

    /**
     * 根据用户获取统计
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> selectStatisticsByUserId(String userId) {
        // 返回结果
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>() {
            {
                put("type", null);
                put("bar", null);
            }
        };
        // 采购统计
        List<StockOut> stockOutList = this.list(Wrappers.<StockOut>lambdaQuery().eq(StockOut::getUserId, userId));
        if (CollectionUtil.isNotEmpty(stockOutList)) {
            result.put("bar", baseMapper.selectStockOutByLast(userId));
        }
        List<StockInfo> stockInfoList = stockInfoService.list(Wrappers.<StockInfo>lambdaQuery().eq(StockInfo::getToUserId, userId));
        // 物品类型
        List<ConsumableType> consumableTypeList = consumableTypeService.list();
        Map<Integer, String> consumableTypeMap = consumableTypeList.stream().collect(Collectors.toMap(ConsumableType::getId, ConsumableType::getName));
        if (CollectionUtil.isNotEmpty(stockInfoList)) {
            Map<String, Integer> stockOutTypeCount = new HashMap<String, Integer>();
            for (Integer type : consumableTypeMap.keySet()) {
                long count = stockInfoList.stream().filter(e -> e.getTypeId().equals(type)).count();
                if (count == 0) {
                    continue;
                }
                stockOutTypeCount.put(consumableTypeMap.get(type), Integer.valueOf(StrUtil.toString(count)));
            }
            result.put("type", stockOutTypeCount);
        }
        return result;
    }
}
