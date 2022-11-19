package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.GoodsBelong;
import cc.mrbird.febs.cos.dao.GoodsBelongMapper;
import cc.mrbird.febs.cos.service.IGoodsBelongService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
@Service
public class GoodsBelongServiceImpl extends ServiceImpl<GoodsBelongMapper, GoodsBelong> implements IGoodsBelongService {

    @Override
    public List<LinkedHashMap<String, Object>> getGoodsByNum(String num) {
        return baseMapper.getGoodsByNum(num);
    }

    @Override
    public List<LinkedHashMap<String, Object>> getGoodsDetailByNum(String num) {
        return baseMapper.getGoodsDetailByNum(num);
    }
}
