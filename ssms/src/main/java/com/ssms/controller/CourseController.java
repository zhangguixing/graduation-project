package com.ssms.controller;

import com.ssms.common.BaseController;
import com.ssms.common.CommonResponse;
import com.ssms.common.ResponseUtil;
import com.ssms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("manage")
    public String manage(Model model){
        return "/course/courseManage.html";
    }

    @ResponseBody
    @GetMapping("listByCollege")
    public CommonResponse listByCollege(Integer gradeId, Integer collegeId, Integer subjectId, Integer classId,String schoolYear,Integer semester){
        return ResponseUtil.generateResponse(courseService.listByCollege(gradeId,collegeId,subjectId,classId,schoolYear,semester));
    }

}
