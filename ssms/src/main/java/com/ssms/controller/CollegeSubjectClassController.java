package com.ssms.controller;

import com.ssms.service.CollegeSubjectClassService;
import com.ssms.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("collegeSubjectClass")
public class CollegeSubjectClassController extends BaseController {

    @Autowired
    private CollegeSubjectClassService collegeSubjectClassService;

    @GetMapping("/{parentId}")
    public ResponseResult getCollegeSubjectClassByParentId(@PathVariable Integer parentId){
        return ResponseResult.ok(collegeSubjectClassService.getCollegeSubjectClassByParentId(parentId));
    }
}
