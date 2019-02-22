package com.sams.controller;

import com.sams.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("exam")
public class ExamController {
    @Autowired
    private ExamService examService;

}
