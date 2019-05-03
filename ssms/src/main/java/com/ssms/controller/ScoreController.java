package com.ssms.controller;

import com.ssms.model.User;
import com.ssms.service.ScoreService;
import com.ssms.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("score")
@Controller
public class ScoreController extends BaseController {
    @GetMapping("manage")
    public String manage(){
        return "score/scoreManage.html";
    }
    @GetMapping("class")
    public String classScore(){
        return "score/classScore.html";
    }

    @Autowired
    private ScoreService scoreService;

    @ResponseBody
    @GetMapping("all")
    public ResponseResult all(Integer pageNum, Integer pageSize){
        User loginUser = this.getLoginUser();
        return ResponseResult.ok(scoreService.listScore(pageNum,pageSize,loginUser));
    }
}
