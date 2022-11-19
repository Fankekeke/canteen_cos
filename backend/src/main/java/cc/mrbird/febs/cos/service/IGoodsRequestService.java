package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.GoodsRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface IGoodsRequestService extends IService<GoodsRequest> {

    // 分页获取耗材申请
    IPage<LinkedHashMap<String, Object>> goodsRequestByPage(Page page, GoodsRequest goodsRequest);

}
