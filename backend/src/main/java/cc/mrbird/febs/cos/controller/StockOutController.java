package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.GoodsRequest;
import cc.mrbird.febs.cos.entity.StockOut;
import cc.mrbird.febs.cos.entity.StudentInfo;
import cc.mrbird.febs.cos.service.IGoodsRequestService;
import cc.mrbird.febs.cos.service.IStockOutService;
import cc.mrbird.febs.cos.service.IStudentInfoService;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author FanK
 */
@RestController
@RequestMapping("/cos/stock-out")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StockOutController {

    private final IStockOutService stockOutService;

    private final IStudentInfoService studentInfoService;

    private final IGoodsRequestService goodsRequestService;

    /**
     * 库房出库
     *
     * @param stockOut
     * @return
     */
    @PostMapping("/stockOut")
    public R stockOut(StockOut stockOut) {
        goodsRequestService.update(Wrappers.<GoodsRequest>lambdaUpdate().set(GoodsRequest::getStep, 1).eq(GoodsRequest::getId, stockOut.getApplyId()));
        return R.ok(stockOutService.stockOut(stockOut));
    }

    /**
     * 根据用户获取统计
     *
     * @param userId 用户ID
     * @return 结果
     */
    @GetMapping("/stockOut/statistics/{userId}")
    public R selectStatisticsByUserId(@PathVariable("userId") String userId) {
        return R.ok(stockOutService.selectStatisticsByUserId(userId));
    }

    @PostMapping("/audit")
    public R audit(Integer id) {
        return R.ok(goodsRequestService.update(Wrappers.<GoodsRequest>lambdaUpdate().set(GoodsRequest::getStep, 2).eq(GoodsRequest::getId, id)));
    }

    /**
     * 分页获取出库管理
     *
     * @param page
     * @param stockOut
     * @return
     */
    @GetMapping("/page")
    public R page(Page page, StockOut stockOut) {
        return R.ok(stockOutService.stockOutByPage(page, stockOut));
    }

    /**
     * 添加出库
     *
     * @param stockOut
     * @return
     */
    @PostMapping
    public R save(StockOut stockOut) {
        return R.ok();
    }

}
