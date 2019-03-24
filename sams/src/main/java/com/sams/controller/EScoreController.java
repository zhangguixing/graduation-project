package com.sams.controller;

import com.sams.entity.Course;
import com.sams.entity.Exam;
import com.sams.response.JsonResult;
import com.sams.service.EScoreService;
import com.sams.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("score")
public class EScoreController {
    @Autowired
    private EScoreService scoreService;
    @Autowired
    private StudentService studentService;

    @PostMapping("columnList")
    public List<Course> columnList(@RequestBody Exam exam){
        return scoreService.columnList(exam);
    }

    @GetMapping("scoreList")
    public List<Map<String,Object>> scoreList(@RequestParam Exam exam){
        return scoreService.scoreList(exam);
    }

    @GetMapping("exportScore")
    public void exportScore(@RequestParam Exam exam, HttpServletResponse response){
//        //获取需要导出的数据
//        StringBuffer stuSb = new StringBuffer("SELECT id, name, number FROM student WHERE gradeid=? ");
//        stuParam.add(exam.getGradeid());
//        if(exam.getClazzid() != 0){
//            stuSb.append(" AND clazzid=?");
//            stuParam.add(exam.getClazzid());
//        }
//        studentService.
//        List<Map<String, Object>> list = dao.getScoreList(exam);
//        //获取考试信息
//        Exam em = (Exam) dao.getObject(Exam.class, "SELECT name, time FROM exam WHERE id=?", new Object[]{exam.getId()});
//        //设置文件名
//        String fileName = em.getName()+".xls";
//        //定义输出类型
//        response.setContentType("application/msexcel;charset=utf-8");
//        //设定输出文件头
//        try {
//            response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(fileName, "UTF-8"));
//        } catch (UnsupportedEncodingException e1) {
//            e1.printStackTrace();
//        }
//
//        //获取导出的课程
//        List<Object> courseList = getColumn(exam);
//
//        //表头长度
//        int len = 2 + courseList.size();
//        if(exam.getType() == Exam.EXAM_GRADE_TYPE){
//            len += 1;
//        }
//        //设置excel的列名
//        String[] headers = new String[len];
//        headers[0] = "姓名";
//        headers[1] = "学号";
//
//        int index = 2;
//        for(Object obj : courseList){
//            Course course = (Course) obj;
//            headers[index++] = course.getName();
//        }
//
//        if(exam.getType() == Exam.EXAM_GRADE_TYPE){
//            headers[len-1] = "总分";
//        }
//
//        ExcelTool et = new ExcelTool<>();
//        //导出
//        try {
//            et.exportMapExcel(headers, list, response.getOutputStream());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @PostMapping("setScore")
    public JsonResult setScore(String[] score){
        return scoreService.setScorse(score);
    }
}
