package com.ssms.controller;

import com.alibaba.fastjson.JSON;
import com.ssms.common.BaseController;
import com.ssms.common.CommonResponse;
import com.ssms.common.JsonResult;
import com.ssms.common.ResponseUtil;
import com.ssms.model.User;
import com.ssms.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
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
            //教师默认展示本专业成绩
            collegeInfo.put("collegeId", loginUser.getCollegeId());
            collegeInfo.put("subjectId", loginUser.getSubjectId());
        }
        model.addAttribute("collegeInfo", collegeInfo);
        return "score/scoreManage.html";
    }

    @GetMapping("distribdution")
    public String scoreDistribution(Model model) {
        User loginUser = getLoginUser();
        Map<String, Integer> collegeInfo = new HashMap<>();
        if (loginUser.getPersonType() != null && loginUser.getPersonType().equals(User.STUDENT_TYPE)) {
            //学生展示默认展示本班级成绩
            collegeInfo.put("gradeId", loginUser.getGradeId());
            collegeInfo.put("collegeId", loginUser.getCollegeId());
            collegeInfo.put("subjectId", loginUser.getSubjectId());
            collegeInfo.put("classId", loginUser.getClassId());
        } else if (loginUser.getPersonType() != null && loginUser.getPersonType().equals(User.TEACHER_TYPE)) {
            //教师展示默认展示本专业
            collegeInfo.put("collegeId", loginUser.getCollegeId());
            collegeInfo.put("subjectId", loginUser.getSubjectId());
        }
        model.addAttribute("personType", loginUser.getPersonType());
        model.addAttribute("collegeInfo", collegeInfo);
        return "score/scoreDistribution.html";
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
        } else if (loginUser.getPersonType() != null && loginUser.getPersonType().equals(User.TEACHER_TYPE)) {
            //教师展示默认展示本专业
            collegeInfo.put("collegeId", loginUser.getCollegeId());
            collegeInfo.put("subjectId", loginUser.getSubjectId());
        }
        model.addAttribute("personType", loginUser.getPersonType());
        model.addAttribute("collegeInfo", collegeInfo);
        return "score/classScore.html";
    }

    @GetMapping("operateScore")
    public String operateScore(Model model, Integer studentId, Integer collegeId, Integer subjectId, Integer classId, Integer gradeId, String schoolYear, Integer semester) {
        Map<String, Object> collegeAndScore = new HashMap<>();
        if (studentId != null && collegeId != null && subjectId != null && classId != null && schoolYear != null && semester != null) {
            collegeAndScore = scoreService.getCollegeInfo(studentId, collegeId, subjectId, classId, gradeId, schoolYear, semester);
        }
        model.addAttribute("collegeAndScore", collegeAndScore);
        return "score/operateScore.html";
    }

    @GetMapping("person")
    public String personScore(Model model) {
        return "score/personScore.html";
    }

    @GetMapping("showForm")
    public String showForm(Model model, Integer studentId, Integer collegeId, Integer subjectId, Integer classId, Integer gradeId, String schoolYear, Integer semester) {
        Map<String, Object> collegeNameAndScore = new HashMap<>();
        if (studentId != null && collegeId != null && subjectId != null && classId != null && schoolYear != null && semester != null) {
            collegeNameAndScore = scoreService.getCollegeNameAndScore(studentId, collegeId, subjectId, classId, gradeId, schoolYear, semester);
        }
        model.addAttribute("collegeNameAndScore", collegeNameAndScore);
        return "score/showForm.html";
    }

    @GetMapping("trend")
    public String scoreTrend(Model model) {
        model.addAttribute("loginUser", getLoginUser());
        return "score/scoreTrend.html";
    }

    @GetMapping("compare")
    public String scoreompare(Model model) {
        return "score/scoreCompare.html";
    }

    @ResponseBody
    @PostMapping("export")
    public CommonResponse export(@RequestBody List<Map<String, Object>> list) {
        return ResponseUtil.generateResponse(scoreService.export(list));
    }

    @ResponseBody
    @DeleteMapping("delete")
    public CommonResponse delete(@RequestBody Map<String, Object> map) {
        return ResponseUtil.generateResponse(scoreService.delete(map));
    }

    @ResponseBody
    @PostMapping("add")
    public CommonResponse add(@RequestBody Map<String, Object> map) {
        return ResponseUtil.generateResponse(scoreService.saveOrUpdate(map));
    }

    @ResponseBody
    @GetMapping("chartsData")
    public CommonResponse getChartsData() {
        return ResponseUtil.generateResponse(scoreService.getChartsData(getLoginUserId()));
    }

    @GetMapping("chartsData2")
    public String getChartsData2(Model model, Integer userId, String nickName) {
        Map<String, Object> chartsData = scoreService.getChartsData(userId);
        model.addAttribute("chartsData", JSON.toJSONString(chartsData));
        model.addAttribute("nickName", nickName);
        return "/system/scoreTrend.html";
    }

    @ResponseBody
    @GetMapping("personScore")
    public CommonResponse getPersonScore(String schoolYear, Integer semester) {
        User user = this.getLoginUser();
        if (user.getPersonType() == User.STUDENT_TYPE) {
            //学生--查询所在班级
            Integer gradeId = user.getGradeId();
            Integer collegeId = user.getCollegeId();
            Integer subjectId = user.getSubjectId();
            Integer classId = user.getClassId();
            Integer studentId = user.getUserId();
            return ResponseUtil.generateResponse(scoreService.getPersonScore(gradeId, collegeId, subjectId, classId, studentId, schoolYear, semester));
        }
        return ResponseUtil.generateResponse(true);
    }

    @ResponseBody
    @GetMapping("all")
    public CommonResponse all(Model model, Integer pageNum, Integer pageSize, Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester,String courseName, String searchKey, String searchValue) {
        User user = this.getLoginUser();
        if (user.getPersonType() == User.STUDENT_TYPE) {
            //学生--查询所在班级
            gradeId = user.getGradeId();
            collegeId = user.getCollegeId();
            subjectId = user.getSubjectId();
            classId = user.getClassId();
        } else if (user.getPersonType() == User.TEACHER_TYPE) {
            //教师--查询所在专业
            collegeId = user.getCollegeId();
            subjectId = user.getSubjectId();
        }
        return ResponseUtil.generateResponse(scoreService.listScore(pageNum, pageSize, gradeId, collegeId, subjectId, classId, schoolYear, semester,courseName, searchKey, searchValue));
    }

    @ResponseBody
    @RequestMapping("addScores")
    public CommonResponse addScores(@RequestParam MultipartFile file) {
        try {
            scoreService.addScores(file);
            return ResponseUtil.generateResponse("添加成功", true);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.generateResponse(e.getMessage(), false);
        }
    }

    @ResponseBody
    @GetMapping("searchPersonNum")
    public CommonResponse searchPersonNum(Integer collegeId, Integer subjectId, Integer classId, Integer gradeId, String schoolYear, Integer semester,String courseName){
        return ResponseUtil.generateResponse(scoreService.searchPersonNum(gradeId,collegeId,subjectId,classId,schoolYear,semester,courseName));
    }
    @ResponseBody
    @GetMapping("searchNameAndScore")
    public CommonResponse searchNameAndScore(Integer gradeIdA, Integer collegeIdA, Integer subjectIdA, Integer classIdA, Integer gradeIdB, Integer collegeIdB, Integer subjectIdB, Integer classIdB,String courseName){
        return ResponseUtil.generateResponse(scoreService.searchNameAndScore(gradeIdA, collegeIdA, subjectIdA, classIdA,gradeIdB, collegeIdB, subjectIdB, classIdB,courseName));
    }
}
