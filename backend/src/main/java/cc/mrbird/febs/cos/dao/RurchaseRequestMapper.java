package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.RurchaseRequest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface RurchaseRequestMapper extends BaseMapper<RurchaseRequest> {

    // 分页获取采购申请
    IPage<LinkedHashMap<String, Object>> rurchaseRequestByPage(Page page, @Param("rurchaseRequest") RurchaseRequest rurchaseRequest);
}
