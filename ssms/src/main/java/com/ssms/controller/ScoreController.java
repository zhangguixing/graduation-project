package com.ssms.controller;

import com.ssms.common.BaseController;
import com.ssms.common.CommonResponse;
import com.ssms.common.ResponseUtil;
import com.ssms.model.User;
import com.ssms.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("score")
@Controller
public class ScoreController extends BaseController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping("manage")
    public String manage(Model model) {
        User loginUser = getLoginUser();
        Map<String, Integer> collegeInfo = new HashMap<>();
        if (loginUser.getPersonType() != null && loginUser.getPersonType().equals(User.TEACHER_TYPE)) {
            //教师默认展示本班级成绩
            collegeInfo.put("collegeId", loginUser.getCollegeId());
            collegeInfo.put("subjectId", loginUser.getSubjectId());
            collegeInfo.put("classId", loginUser.getClassId());
            collegeInfo.put("gradeId", loginUser.getGradeId());
        }
        model.addAttribute("collegeInfo", collegeInfo);
        return "score/scoreManage.html";
    }

    @GetMapping("class")
    public String classScore(Model model) {
        User loginUser = getLoginUser();
        Map<String, Integer> collegeInfo = new HashMap<>();
        if (loginUser.getPersonType() != null && loginUser.getPersonType().equals(User.STUDENT_TYPE)) {
            //学生展示默认展示本班级成绩
            collegeInfo.put("gradeId", loginUser.getGradeId());
            collegeInfo.put("collegeId", loginUser.getCollegeId());
            collegeInfo.put("subjectId", loginUser.getSubjectId());
            collegeInfo.put("classId", loginUser.getClassId());
        }
        model.addAttribute("collegeInfo", collegeInfo);
        return "score/classScore.html";
    }

    @GetMapping("operateScore")
    public String operateScore(Model model,Integer id){
        Map<String,Object> collegeAndScore = new HashMap<>();
        if(id != null){
            collegeAndScore = scoreService.getCollegeInfoById(id);
        }
        model.addAttribute("collegeAndScore",collegeAndScore);
        return "score/operateScore.html";
    }

    @GetMapping("person")
    public String personScore(Model model) {
        return "score/personScore.html";
    }

    @GetMapping("trend")
    public String scoreTrend(Model model) {
        model.addAttribute("loginUser", getLoginUser());
        return "score/scoreTrend.html";
    }

    @ResponseBody
    @DeleteMapping("delete/{id}")
    public CommonResponse delete(@PathVariable Integer id){
        return ResponseUtil.generateResponse(scoreService.delete(id));
    }

    @ResponseBody
    @PostMapping("add")
    public CommonResponse add(@RequestBody Map<String,Object> map){
        return ResponseUtil.generateResponse(scoreService.saveOrUpdate(map));
    }

    @ResponseBody
    @GetMapping("chartsData")
    public CommonResponse getChartsData(){
        return ResponseUtil.generateResponse(scoreService.getChartsData(getLoginUserId()));
    }

    @ResponseBody
    @GetMapping("personScore")
    public CommonResponse getPersonScore(String schoolYear,Integer semester) {
        User user = this.getLoginUser();
        if (user.getPersonType() == User.STUDENT_TYPE) {
            //学生--查询所在班级
            Integer gradeId = user.getGradeId();
            Integer collegeId = user.getCollegeId();
            Integer subjectId = user.getSubjectId();
            Integer classId = user.getClassId();
            Integer studentId = user.getUserId();
            return ResponseUtil.generateResponse(scoreService.getPersonScore(gradeId,collegeId,subjectId,classId,studentId,schoolYear,semester));
        }
        return ResponseUtil.generateResponse(true);
    }

    @ResponseBody
    @GetMapping("all")
    public CommonResponse all(Model model,Integer pageNum, Integer pageSize, Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester, String searchKey, String searchValue) {
        User user = this.getLoginUser();
        if (user.getPersonType() == User.STUDENT_TYPE) {
            //学生--查询所在班级
            gradeId = user.getGradeId();
            collegeId = user.getCollegeId();
            subjectId = user.getSubjectId();
            classId = user.getClassId();
        }
        return ResponseUtil.generateResponse(scoreService.listScore(pageNum, pageSize, gradeId, collegeId, subjectId, classId, schoolYear, semester, searchKey, searchValue));
    }
}
