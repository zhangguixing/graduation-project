package com.ssms.controller;

import com.ssms.common.BaseController;
import com.ssms.common.CommonResponse;
import com.ssms.common.ResponseUtil;
import com.ssms.model.User;
import com.ssms.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("score")
@Controller
public class ScoreController extends BaseController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping("manage")
    public String manage(){
        return "score/scoreManage.html";
    }

    @GetMapping("class")
    public String classScore(Model model){
        User loginUser = getLoginUser();
        if(loginUser.getPersonType().equals(User.STUDENT_TYPE)){
            //学生展示默认展示本班级成绩
            Map<String,Integer> collegeInfo = new HashMap<>();
            collegeInfo.put("gradeId",loginUser.getGradeId());
            collegeInfo.put("collegeId",loginUser.getCollegeId());
            collegeInfo.put("subjectId",loginUser.getSubjectId());
            collegeInfo.put("classId",loginUser.getClassId());
            model.addAttribute("collegeInfo",collegeInfo);
        }
        return "score/classScore.html";
    }

    @ResponseBody
    @GetMapping("all")
    public CommonResponse all(Integer pageNum, Integer pageSize,String searchKey,String searchValue){
        return ResponseUtil.generateResponse(scoreService.listScore(pageNum,pageSize,this.getLoginUser(),searchKey,searchValue));
    }
}
