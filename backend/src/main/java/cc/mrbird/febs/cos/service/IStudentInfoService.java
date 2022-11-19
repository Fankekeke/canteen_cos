package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.StudentInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface IStudentInfoService extends IService<StudentInfo> {

    // 分页获取学生信息
    IPage<LinkedHashMap<String, Object>> studentInfoByPage(Page page, StudentInfo studentInfo);

}
