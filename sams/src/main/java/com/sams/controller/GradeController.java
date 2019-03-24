package com.sams.controller;

import com.sams.entity.Grade;
import com.sams.response.JsonResult;
import com.sams.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @PostMapping("gradeList")
    public String gradeService(String course){
        return gradeService.gradeService(course);
    }

    @PostMapping("deleteGrade")
    public JsonResult deleteGrade(Integer gradeid){
        return gradeService.deleteGrade(gradeid);
    }

    @PostMapping("addGrade")
    public JsonResult addGrade(Integer[] clazzid,String name){
        return gradeService.addGrade(clazzid,name);
    }
}
