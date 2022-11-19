package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.GoodsRequest;
import cc.mrbird.febs.cos.dao.GoodsRequestMapper;
import cc.mrbird.febs.cos.service.IGoodsRequestService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
@Service
public class GoodsRequestServiceImpl extends ServiceImpl<GoodsRequestMapper, GoodsRequest> implements IGoodsRequestService {

    @Override
    public IPage<LinkedHashMap<String, Object>> goodsRequestByPage(Page page, GoodsRequest goodsRequest) {
        return baseMapper.goodsRequestByPage(page, goodsRequest);
    }
}
