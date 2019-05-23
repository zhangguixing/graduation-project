package com.ssms.controller;

import com.ssms.common.BaseController;
import com.ssms.common.CommonResponse;
import com.ssms.common.ResponseUtil;
import com.ssms.model.Course;
import com.ssms.model.CourseTimeTable;
import com.ssms.model.User;
import com.ssms.service.CourseService;
import com.ssms.service.CourseTimeTableService;
import com.ssms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Guixing
 * @Date 2019/5/6 14:46
 * @Description 课程
 */
@Controller
@RequestMapping("course")
public class CourseController extends BaseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseTimeTableService courseTimeTableService;

    @GetMapping("manage")
    public String manage(Model model) {
        return "/course/courseManage.html";
    }

    @GetMapping("editForm")
    public String editForm(Model model) {
        return "/course/courseForm.html";
    }

    @GetMapping("timeTableManage")
    public String timeTableManage(Model model) {
        return "/course/timeTableManage.html";
    }

    @GetMapping("view")
    public String view(Model model) {
        User user = this.getLoginUser();
        Map<String, Integer> result = new HashMap<>();
        Integer collegeId = user.getCollegeId();
        Integer subjectId = user.getSubjectId();
        result.put("collegeId", collegeId);
        result.put("subjectId", subjectId);
        if (User.STUDENT_TYPE == user.getPersonType()) {
            Integer gradeId = user.getGradeId();
            Integer classId = user.getClassId();
            result.put("gradeId", gradeId);
            result.put("classId", classId);
        }
        model.addAttribute("collegeInfo", result);
        return "/course/classCourse.html";
    }

    @GetMapping("myCourse")
    public String myCourse(Model model) {
        model.addAttribute("loginUser", getLoginUser());
        return "course/myCourse.html";
    }

    @ResponseBody
    @GetMapping("listByCollege")
    public CommonResponse listByCollege(Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester) {
        return ResponseUtil.generateResponse(courseService.listByCollege(gradeId, collegeId, subjectId, classId, schoolYear, semester));
    }

    @ResponseBody
    @GetMapping("listTimeTable")
    public CommonResponse listTimeTable(Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester, Integer weekNum) {
        return ResponseUtil.generateResponse(courseService.listTimeTable(gradeId, collegeId, subjectId, classId, schoolYear, semester, weekNum));
    }

    @ResponseBody
    @GetMapping("listTeacherIdAndName")
    public CommonResponse listTeacherIdAndName(Integer collegeId, Integer subjectId) {
        return ResponseUtil.generateResponse(userService.listUserIdAndName(collegeId, subjectId, null, null, User.TEACHER_TYPE));
    }

    @ResponseBody
    @GetMapping("listCourseIdAndName")
    public CommonResponse listCourseIdAndName(Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester) {
        return ResponseUtil.generateResponse(courseService.listCourseIdAndName(gradeId, collegeId, subjectId, classId, schoolYear, semester));
    }

    @ResponseBody
    @PostMapping("timeTable")
    public CommonResponse addTimeTable(@RequestBody CourseTimeTable courseTimeTable) {
        return ResponseUtil.generateResponse(courseTimeTableService.addTimeTable(courseTimeTable));
    }

    @ResponseBody
    @PutMapping("timeTable")
    public CommonResponse updateTimeTable(@RequestBody CourseTimeTable courseTimeTable) {
        return ResponseUtil.generateResponse(courseTimeTableService.updateTimeTable(courseTimeTable));
    }

    @ResponseBody
    @DeleteMapping("timeTable/{id}")
    public CommonResponse deleteTimeTable(@PathVariable Integer id) {
        return ResponseUtil.generateResponse(courseTimeTableService.deleteTimeTable(id));
    }

    @ResponseBody
    @GetMapping("getCourseInfo")
    public CommonResponse getCourseInfo(@RequestParam Map<String, Object> map) {
        return ResponseUtil.generateResponse(courseTimeTableService.getCourseInfo(map));
    }

    @ResponseBody
    @GetMapping("myTimeTable")
    public CommonResponse myTimeTable(String schoolYear, Integer semester) {
        Integer teacherId = this.getLoginUserId();
        return ResponseUtil.generateResponse(courseTimeTableService.getMyTimeTable(schoolYear, semester, teacherId));
    }

    @ResponseBody
    @GetMapping("all")
    public CommonResponse all(Integer pageNum, Integer pageSize, Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester, String searchKey, String searchValue) {
        return ResponseUtil.generateResponse(courseService.all(pageNum, pageSize, gradeId, collegeId, subjectId, classId, schoolYear, semester, searchKey, searchValue));
    }

    @ResponseBody
    @PostMapping("add")
    public CommonResponse add(Course course) {
        return ResponseUtil.generateResponse(courseService.add(course));
    }

    @ResponseBody
    @PostMapping("update")
    public CommonResponse update(Course course) {
        return ResponseUtil.generateResponse(courseService.update(course));
    }

    @ResponseBody
    @DeleteMapping("delete/{id}")
    public CommonResponse delete(@PathVariable Integer id) {
        return ResponseUtil.generateResponse(courseService.delete(id));
    }

}
