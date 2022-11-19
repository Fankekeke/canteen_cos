package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.ComboInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface IComboInfoService extends IService<ComboInfo> {

    // 分页获取实验套餐信息
    IPage<LinkedHashMap<String, Object>> comboInfoByPage(Page page, ComboInfo comboInfo);
}
