package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.GoodsBelong;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
public interface IGoodsBelongService extends IService<GoodsBelong> {

    // 根据单号获取物品信息
    List<LinkedHashMap<String, Object>> getGoodsByNum(String num);

    // 根据单号获取物品详细信息
    List<LinkedHashMap<String, Object>> getGoodsDetailByNum(String num);
}
