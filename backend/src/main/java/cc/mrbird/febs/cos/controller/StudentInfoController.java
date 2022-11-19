package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.StudentInfo;
import cc.mrbird.febs.cos.service.IStudentInfoService;
import cc.mrbird.febs.system.domain.User;
import cc.mrbird.febs.system.service.UserService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author FanK
 */
@RestController
@RequestMapping("/cos/student-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentInfoController {

    private final IStudentInfoService studentInfoService;

    private final UserService userService;

    /**
     * 获取所有学生
     * @return
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(studentInfoService.list());
    }

    /**
     * 修改账户状态
     * @param userId
     * @param status
     * @return
     */
    @PostMapping("/accountStatusEdit")
    public R accountStatusEdit(Integer userId, Integer status) {
        return R.ok(userService.update(Wrappers.<User>lambdaUpdate().set(User::getStatus, status).eq(User::getUserId, userId)));
    }

    /**
     * 分页获取学生信息
     * @param page
     * @param studentInfo
     * @return
     */
    @GetMapping("/page")
    public R page(Page page, StudentInfo studentInfo) {
        return R.ok(studentInfoService.studentInfoByPage(page, studentInfo));
    }

    /**
     * 修改学生信息
     * @param studentInfo
     * @return
     */
    @PutMapping
    public R edit(StudentInfo studentInfo) {
        return R.ok(studentInfoService.updateById(studentInfo));
    }

    /**
     * 删除学生信息
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(studentInfoService.removeByIds(ids));
    }

}
