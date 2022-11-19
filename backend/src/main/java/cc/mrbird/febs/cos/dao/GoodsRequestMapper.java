package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.GoodsRequest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface GoodsRequestMapper extends BaseMapper<GoodsRequest> {

    // 分页获取耗材申请
    IPage<LinkedHashMap<String, Object>> goodsRequestByPage(Page page, @Param("goodsRequest") GoodsRequest goodsRequest);
}
