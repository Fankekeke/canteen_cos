package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ComboInfo;
import cc.mrbird.febs.cos.entity.GoodsBelong;
import cc.mrbird.febs.cos.service.IComboInfoService;
import cc.mrbird.febs.cos.service.IGoodsBelongService;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
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
@RequestMapping("/cos/combo-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ComboInfoController {

    private final IComboInfoService comboInfoService;

    private final IGoodsBelongService goodsBelongService;

    @GetMapping("/list")
    public R list() {
        return R.ok(comboInfoService.list());
    }

    /**
     * 分页获取实验套餐信息
     * @param page
     * @param comboInfo
     * @return
     */
    @GetMapping("/page")
    public R page(Page page, ComboInfo comboInfo) {
        return R.ok(comboInfoService.page(page, Wrappers.<ComboInfo>lambdaQuery()
                .like(!StrUtil.isBlank(comboInfo.getCode()), ComboInfo::getCode, comboInfo.getCode())
                .like(!StrUtil.isBlank(comboInfo.getName()), ComboInfo::getName, comboInfo.getName())));
    }

    /**
     * 添加实验套餐信息
     * @param comboInfo
     * @return
     */
    @PostMapping
    public R save(ComboInfo comboInfo) {
        comboInfo.setCode("COMBO-"+new Date().getTime());
        comboInfo.setCreateDate(DateUtil.formatDateTime(new Date()));

        JSONArray array = JSONUtil.parseArray(comboInfo.getGoods());
        List<GoodsBelong> goodsBelongList = JSONUtil.toList(array, GoodsBelong.class);
        goodsBelongList.forEach(item -> {
            GoodsBelong goodsBelong = new GoodsBelong();
            goodsBelong.setNum(comboInfo.getCode());
            goodsBelong.setCreateDate(DateUtil.formatDateTime(new Date()));
            goodsBelong.setAmount(item.getAmount());
            goodsBelong.setName(item.getName());
            goodsBelong.setPrice(item.getPrice());
            goodsBelong.setType(item.getType());
            goodsBelong.setTypeId(item.getTypeId());
            goodsBelong.setUnit(item.getUnit());
            goodsBelongService.save(goodsBelong);
        });
        return R.ok(comboInfoService.save(comboInfo));
    }

    /**
     * 修改实验套餐信息
     * @param comboInfo
     * @return
     */
    @PutMapping
    public R edit(ComboInfo comboInfo) {
        goodsBelongService.remove(Wrappers.<GoodsBelong>lambdaQuery().eq(GoodsBelong::getNum, comboInfo.getCode()));
        JSONArray array = JSONUtil.parseArray(comboInfo.getGoods());
        List<GoodsBelong> goodsBelongList = JSONUtil.toList(array, GoodsBelong.class);
        goodsBelongList.forEach(item -> {
            GoodsBelong goodsBelong = new GoodsBelong();
            goodsBelong.setNum(comboInfo.getCode());
            goodsBelong.setCreateDate(DateUtil.formatDateTime(new Date()));
            goodsBelong.setAmount(item.getAmount());
            goodsBelong.setName(item.getName());
            goodsBelong.setPrice(item.getPrice());
            goodsBelong.setType(item.getType());
            goodsBelong.setTypeId(item.getTypeId());
            goodsBelong.setUnit(item.getUnit());
            goodsBelongService.save(goodsBelong);
        });
        return R.ok(comboInfoService.updateById(comboInfo));
    }

    /**
     * 删除实验套餐信息
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(comboInfoService.removeByIds(ids));
    }

}
