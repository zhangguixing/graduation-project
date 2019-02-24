package com.sams.controller;

import com.sams.entity.Course;
import com.sams.entity.EScore;
import com.sams.entity.Exam;
import com.sams.entity.Student;
import com.sams.response.JsonResult;
import com.sams.service.CourseService;
import com.sams.service.EScoreService;
import com.sams.service.ExamService;
import com.sams.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("exam")
@Slf4j
public class ExamController {
    @Autowired
    private ExamService examService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private EScoreService scoreService;

    @PostMapping("examList")
    public String examList(Integer page, Integer rows, Integer gradeid, Integer clazzid) {
        return examService.examList(page, rows, gradeid, clazzid);
    }

    @PostMapping("deleteExam")
    public JsonResult deleteExam(Integer id) {
        try {
            examService.deleteExam(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return JsonResult.error("删除失败");
        }
        return JsonResult.ok();
    }

    @PostMapping("addExam")
    public JsonResult addExam(@RequestBody Exam exam) {
        try{
            //添加考试信息
            examService.insert(exam);
            if (exam.getType() == Exam.EXAM_GRADE_TYPE) { //年级统考
                //查询该年级的课程
                List<Course> couList = courseService.findByGradeid(exam.getGradeid());
                //查询该年级下的学生
                List<Student> stuList = studentService.findByGradeid(exam.getGradeid());
                //批量参数
                for (int i = 0; i < stuList.size(); i++) {
                    for (int j = 0; j < couList.size(); j++) {
                        EScore score = new EScore();
                        score.setExamid(exam.getId());
                        score.setClazzid(stuList.get(i).getClazzid());
                        score.setStudentid(stuList.get(i).getId());
                        score.setGradeid(exam.getGradeid());
                        score.setCourseid(couList.get(j).getId());
                        scoreService.insert(score);
                    }
                }
            } else {  //平时考试

                //查询该班级下的学生
                List<Student> stuList = studentService.findByClazzid(exam.getClazzid());
                //批量参数
                Object[][] param = new Object[stuList.size()][5];
                for (int i = 0; i < stuList.size(); i++) {
                    EScore score = new EScore();
                    score.setExamid(exam.getId());
                    score.setClazzid(exam.getClazzid());
                    score.setStudentid(stuList.get(i).getId());
                    score.setGradeid(exam.getGradeid());
                    score.setCourseid(exam.getCourseid());
                    scoreService.insert(score);
                }
            }
            return JsonResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return JsonResult.error("添加失败!");
    }
}
