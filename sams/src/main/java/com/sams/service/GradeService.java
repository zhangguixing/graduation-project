package com.sams.service;

import com.sams.dao.*;
import com.sams.entity.*;
import com.sams.response.JsonResult;
import com.sams.util.StringUtils;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class GradeService {
    @Autowired
    private GradeDao gradeDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private EScoreDao scoreDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ExamDao examDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ClazzDao clazzDao;

    public String gradeService(String course) {
        //获取数据
        List<Grade> list = gradeDao.selectAll();
        JsonConfig config = new JsonConfig();
        if(StringUtils.isBlank(course)){ //如果没有传进course参数，则返回年级的id和名称即可
            config.setExcludes(new String[]{"clazzList", "clazzList", "studentList"});
        } else{ //不为空需要再将年级下的班级获取出来
            //获取课程
            for(Grade grade : list){
                List<Course> courseList = courseDao.findByGradeid(grade.getId());
                grade.setCourseList(courseList);
            }
            config.setExcludes(new String[]{"clazzList", "studentList"});
        }
        //json化
        String result = JSONArray.fromObject(list, config).toString();
        return result;
    }

    public JsonResult deleteGrade(Integer gradeid) {
        try {
            //删除成绩表
            Example scoreExample = new Example(EScore.class);
            scoreExample.createCriteria().andEqualTo("gradeid",gradeid);
            scoreDao.deleteByExample(scoreExample);
            //删除考试记录
            Example examExample = new Example(Exam.class);
            examExample.createCriteria().andEqualTo("gradeid",gradeid);
            examDao.deleteByExample(examExample);
            //删除班级的课程和老师的关联
            gradeDao.deleteClassCourseTeacherByGradeid(gradeid);
            //删除班级的课程和老师的关联
            gradeDao.deleteGradeCourseByGradeid(gradeid);
            //删除用户
            Example studentExample = new Example(Student.class);
            studentExample.createCriteria().andEqualTo("gradeid",gradeid);
            List<Student> studentList = studentDao.selectByExample(studentExample);
            if(studentList.size() > 0){
                List<String> accountList = new ArrayList<>();
                for (Student student:studentList) {
                    accountList.add(student.getNumber());
                }
                Example userExample = new Example(User.class);
                userExample.createCriteria().andIn("account",accountList);
                userDao.deleteByExample(userExample);
                //删除学生
                Student student = new Student();
                student.setGradeid(gradeid);
                studentDao.delete(student);
            }
            //删除班级
            Clazz clazz = new Clazz();
            clazz.setGradeid(gradeid);
            clazzDao.delete(clazz);
            //最后删除年级
            gradeDao.deleteByPrimaryKey(gradeid);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("删除失败");
        }
        return JsonResult.ok();
    }

    public JsonResult addGrade(Integer[] clazzid, String name) {
        try{
            Integer gradeid = gradeDao.insertGradeReturnId(name);
            gradeDao.insertGradeCourse(gradeid,clazzid);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("添加失败");
        }
        return JsonResult.ok();
    }
}
