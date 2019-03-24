package com.sams.controller;

import com.sams.entity.Clazz;
import com.sams.response.JsonResult;
import com.sams.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clazz")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    @PostMapping("clazzList")
    public String getClazzList(Integer gradeId){
        return clazzService.getClazzList(gradeId);
    }

    @PostMapping("addClazz")
    public JsonResult addClazz(Clazz clazz){
        return clazzService.addClazz(clazz);
    }

    @PostMapping("clazzDetailList")
    public String clazzDetailList(Integer gradeid,Integer page,Integer rows){
        return clazzService.clazzDetailList(gradeid,page,rows);
    }

    @PostMapping("deleteClazz")
    public JsonResult deleteClazz(Integer clazzid){
        return clazzService.deleteClazz(clazzid);
    }
}
