package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.StockOut;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
public interface StockOutMapper extends BaseMapper<StockOut> {

    // 分页获取出库管理
    IPage<LinkedHashMap<String, Object>> stockOutByPage(Page page, @Param("stockOut") StockOut stockOut);

    /**
     * 获取九天前采购统计
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectStockOutByLast(@Param("userId") String userId);
}
