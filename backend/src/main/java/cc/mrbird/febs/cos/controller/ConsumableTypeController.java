package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ConsumableType;
import cc.mrbird.febs.cos.service.IConsumableTypeService;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
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
@RequestMapping("/cos/consumable-type")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ConsumableTypeController {

    private final IConsumableTypeService consumableTypeService;

    /**
     * 获取所有耗材类别
     * @return
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(consumableTypeService.list());
    }

    /**
     * 分页查询耗材类型信息
     * @param page
     * @param consumableType
     * @return
     */
    @GetMapping("/page")
    public R page(Page page, ConsumableType consumableType) {
        return R.ok(consumableTypeService.page(page, Wrappers.<ConsumableType>lambdaQuery()
                .like(!StrUtil.isBlank(consumableType.getName()), ConsumableType::getName, consumableType.getName())
                .like(!StrUtil.isBlank(consumableType.getContent()), ConsumableType::getContent, consumableType.getContent())));
    }

    /**
     * 添加耗材类型信息
     * @param consumableType
     * @return
     */
    @PostMapping
    public R save(ConsumableType consumableType) {
        consumableType.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(consumableTypeService.save(consumableType));
    }

    /**
     * 修改耗材类型信息
     * @param consumableType
     * @return
     */
    @PutMapping
    public R edit(ConsumableType consumableType) {
        return R.ok(consumableTypeService.updateById(consumableType));
    }

    /**
     * 删除耗材类型信息
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(consumableTypeService.removeByIds(ids));
    }


}
