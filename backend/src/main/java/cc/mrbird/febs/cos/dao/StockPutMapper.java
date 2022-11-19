package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.StockPut;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface StockPutMapper extends BaseMapper<StockPut> {

    // 分页获取入库记录
    IPage<LinkedHashMap<String, Object>> stockPutByPage(Page page, @Param("stockPut") StockPut stockPut);
}
