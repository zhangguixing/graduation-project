package com.sams.controller;

import com.sams.constant.Constants;
import com.sams.entity.*;
import com.sams.response.JsonResult;
import com.sams.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @PostMapping("teacherList")
    public String getTeacherList(Integer page, Integer rows) {
        return teacherService.getTeacherList(page,rows);
    }

    @PostMapping("deleteTeacher")
    public JsonResult deleteTeacher(String[] numbers, String[] ids){
        return teacherService.deleteStudent(numbers,ids);
    }

    @PostMapping("addTeacher")
    public JsonResult addTeacher(@RequestBody Teacher teacher){
        return teacherService.addTeacher(teacher);
    }

    @PostMapping("editTeacher")
    public JsonResult editTeacher(@RequestBody Teacher teacher){
        return teacherService.editTeacher(teacher);
    }

    @PostMapping("getTeacher")
    public Teacher getTeacher(HttpSession session){
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        String number = user.getAccount();
        return teacherService.getTeacher(number);
    }

    @GetMapping("getExamClazz")
    public List<Clazz> getExamClazz(Integer gradeid,HttpSession session){
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        String number = user.getAccount();
        return teacherService.getExamClazz(gradeid,number);
    }

    @GetMapping("getExamCourse")
    public List<CourseItem> getExamCourse(Integer gradeid,Integer clazzid,HttpSession session){
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        String number = user.getAccount();
        return teacherService.getExamCourse(gradeid,clazzid,number);
    }

    @PostMapping("editTeacherPersonal")
    public JsonResult editTeacherPersonal(@RequestBody Teacher teacher){
        return teacherService.editTeacherPersonal(teacher);
    }
}
