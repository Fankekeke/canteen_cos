package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.service.IGoodsBelongService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * @author FanK
 */
@RestController
@RequestMapping("/cos/goods-belong")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GoodsBelongController {

    private final IGoodsBelongService goodsBelongService;

    /**
     * 根据单号获取物品信息
     * @param num
     * @return
     */
    @GetMapping("/getGoodsByNum")
    public R getGoodsByNum(String num) {
        return R.ok(goodsBelongService.getGoodsByNum(num));
    }

    /**
     * 根据单号获取物品详细信息
     * @param num
     * @return
     */
    @GetMapping("/getGoodsDetailByNum")
    public R getGoodsDetailByNum(String num) {
        return R.ok(goodsBelongService.getGoodsDetailByNum(num));
    }

}
