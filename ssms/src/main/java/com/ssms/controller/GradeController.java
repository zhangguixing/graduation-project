package com.ssms.controller;

import com.ssms.common.BaseController;
import com.ssms.common.CommonResponse;
import com.ssms.common.ResponseUtil;
import com.ssms.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("grade")
public class GradeController extends BaseController {

    @Autowired
    private GradeService gradeService;

    @GetMapping("list")
    public CommonResponse list(){
        return ResponseUtil.generateResponse(gradeService.list());
    }
}
