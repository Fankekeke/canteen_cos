package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.RurchaseRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface IRurchaseRequestService extends IService<RurchaseRequest> {

    // 分页获取采购申请
    IPage<LinkedHashMap<String, Object>> rurchaseRequestByPage(Page page, RurchaseRequest rurchaseRequest);

    // 添加采购申请
    Boolean rurchaseRequestAdd(RurchaseRequest rurchaseRequest);

    // 采购申请入库
    Boolean rurchasePut(RurchaseRequest rurchaseRequest);
}
