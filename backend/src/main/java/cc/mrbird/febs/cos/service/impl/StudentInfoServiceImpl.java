package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.StudentInfo;
import cc.mrbird.febs.cos.dao.StudentInfoMapper;
import cc.mrbird.febs.cos.service.IStudentInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
@Service
public class StudentInfoServiceImpl extends ServiceImpl<StudentInfoMapper, StudentInfo> implements IStudentInfoService {

    @Override
    public IPage<LinkedHashMap<String, Object>> studentInfoByPage(Page page, StudentInfo studentInfo) {
        return baseMapper.studentInfoByPage(page, studentInfo);
    }
}
