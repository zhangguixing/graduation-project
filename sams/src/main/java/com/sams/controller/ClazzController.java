package com.sams.controller;

import com.sams.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clazz")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

}
