package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.GoodsBelong;
import cc.mrbird.febs.cos.entity.GoodsRequest;
import cc.mrbird.febs.cos.entity.StudentInfo;
import cc.mrbird.febs.cos.service.IGoodsBelongService;
import cc.mrbird.febs.cos.service.IGoodsRequestService;
import cc.mrbird.febs.cos.service.IStudentInfoService;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author FanK
 */
@RestController
@RequestMapping("/cos/goods-request")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GoodsRequestController {

    private final IGoodsRequestService goodsRequestService;

    private final IGoodsBelongService goodsBelongService;

    private final IStudentInfoService studentInfoService;

    /**
     * 分页获取耗材申请
     * @param page
     * @param goodsRequest
     * @return
     */
    @GetMapping("/page")
    public R page(Page page, GoodsRequest goodsRequest) {
        return R.ok(goodsRequestService.goodsRequestByPage(page, goodsRequest));
    }

    /**
     * 新增耗材申请
     * @param goodsRequest
     * @return
     */
    @PostMapping
    public R save(GoodsRequest goodsRequest) {
        goodsRequest.setNum("REQ-"+new Date().getTime());
        goodsRequest.setStep(0);
        goodsRequest.setCreateDate(DateUtil.formatDateTime(new Date()));
        StudentInfo studentInfo = studentInfoService.getOne(Wrappers.<StudentInfo>lambdaQuery().eq(StudentInfo::getUserId, goodsRequest.getUserId()));
        goodsRequest.setUserId(studentInfo.getId());

        JSONArray array = JSONUtil.parseArray(goodsRequest.getGoods());
        List<GoodsBelong> goodsBelongList = JSONUtil.toList(array, GoodsBelong.class);
        goodsBelongList.forEach(item -> {
            // 添加所属信息
            GoodsBelong goodsBelong = new GoodsBelong();
            goodsBelong.setNum(goodsRequest.getNum());
            goodsBelong.setCreateDate(DateUtil.formatDateTime(new Date()));
            goodsBelong.setAmount(item.getAmount());
            goodsBelong.setName(item.getName());
            goodsBelong.setPrice(item.getPrice());
            goodsBelong.setType(item.getType());
            goodsBelong.setTypeId(item.getTypeId());
            goodsBelong.setUnit(item.getUnit());
            goodsBelongService.save(goodsBelong);
        });
        return R.ok(goodsRequestService.save(goodsRequest));
    }

    /**
     * 修改耗材申请
     * @param goodsRequest
     * @return
     */
    @PutMapping
    public R edit(GoodsRequest goodsRequest) {
        return R.ok(goodsRequestService.updateById(goodsRequest));
    }

    /**
     * 删除耗材申请
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(goodsRequestService.removeByIds(ids));
    }
}
