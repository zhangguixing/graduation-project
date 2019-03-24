package com.sams.controller;

import com.sams.constant.Constants;
import com.sams.entity.Student;
import com.sams.entity.User;
import com.sams.response.JsonResult;
import com.sams.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("studentList")
    public String studentList(Integer gradeid,Integer clazzid,Integer page,Integer rows){
        return studentService.studentList(gradeid,clazzid,page,rows);
    }

    @PostMapping("deleteStudent")
    public JsonResult deleteStudent(String[] numbers,String[] ids){
        return studentService.deleteStudent(numbers,ids);
    }

    @PostMapping("addStudent")
    public JsonResult addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @PostMapping("editStudent")
    public JsonResult editStudent(@RequestBody Student student){
        return studentService.editStudent(student);
    }

    @PostMapping("getStudentDetail")
    public JsonResult getStudentDetail(HttpSession session){
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        String number = user.getAccount();
        Student student = studentService.findByNumber(number);
        session.setAttribute(Constants.USER_DETAIL,student);
        return JsonResult.ok();
    }

    @PostMapping("studentClazzList")
    public String studentClazzList(Integer page,Integer rows,HttpSession session){
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        String number = user.getAccount();
        Student student = studentService.findByNumber(number);
        return studentService.findByGradeidAndClazzid(student,page,rows);
    }
}
