package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.ComboInfo;
import cc.mrbird.febs.cos.dao.ComboInfoMapper;
import cc.mrbird.febs.cos.service.IComboInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
@Service
public class ComboInfoServiceImpl extends ServiceImpl<ComboInfoMapper, ComboInfo> implements IComboInfoService {

    @Override
    public IPage<LinkedHashMap<String, Object>> comboInfoByPage(Page page, ComboInfo comboInfo) {
        return baseMapper.comboInfoByPage(page, comboInfo);
    }
}
