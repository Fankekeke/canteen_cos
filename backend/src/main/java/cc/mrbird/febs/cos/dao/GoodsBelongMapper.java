package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.GoodsBelong;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
public interface GoodsBelongMapper extends BaseMapper<GoodsBelong> {

    // 根据单号获取物品信息
    List<LinkedHashMap<String, Object>> getGoodsByNum(@Param("num") String num);

    // 根据单号获取物品详细信息
    List<LinkedHashMap<String, Object>> getGoodsDetailByNum(@Param("num") String num);
}
