package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.StockOut;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface IStockOutService extends IService<StockOut> {

    // 库房出库
    Boolean stockOut(StockOut stockOut);

    // 分页获取出库管理
    IPage<LinkedHashMap<String, Object>> stockOutByPage(Page page, StockOut stockOut);

    /**
     * 根据用户获取统计
     *
     * @param userId 用户ID
     * @return 结果
     */
    LinkedHashMap<String, Object> selectStatisticsByUserId(String userId);
}
