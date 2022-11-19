package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.StockPut;
import cc.mrbird.febs.cos.dao.StockPutMapper;
import cc.mrbird.febs.cos.service.IStockPutService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
@Service
public class StockPutServiceImpl extends ServiceImpl<StockPutMapper, StockPut> implements IStockPutService {

    @Override
    public IPage<LinkedHashMap<String, Object>> stockPutByPage(Page page, StockPut stockPut) {
        return baseMapper.stockPutByPage(page, stockPut);
    }
}
