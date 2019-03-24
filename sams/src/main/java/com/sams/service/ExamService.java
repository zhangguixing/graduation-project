package com.sams.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.sams.dao.*;
import com.sams.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ExamService {
    @Autowired
    private ExamDao examDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ClazzDao clazzDao;
    @Autowired
    private GradeDao gradeDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private CourseItemDao courseItemDao;

    public String examList(Integer page, Integer rows, Integer gradeid, Integer clazzid) {
        PageHelper.startPage(page,rows);
        Example example = new Example(Exam.class);
        Example.Criteria criteria = example.createCriteria();
        if(gradeid!=null){
            criteria.andEqualTo("gradeid",gradeid);
        }
        if(clazzid != null){
            criteria.andEqualTo("clazzid",clazzid);
        }
        example.setOrderByClause("id desc");
        List<Exam> examList = examDao.selectByExample(example);
        int total = examDao.selectCount(new Exam());
        JSONObject result = new JSONObject();
        result.put("rows",examList);
        result.put("total",total);
        return JSONUtils.toJSONString(result);
    }

    public void deleteExam(Integer id) {
        examDao.deleteByPrimaryKey(id);
    }

    public void insert(Exam exam) {
        examDao.insert(exam);

    }

    public List<Exam> findByNumber(String number) {
        Student student = new Student();
        student.setNumber(number);
        Student stu = studentDao.selectOne(student);
        List<Exam> list = examDao.findByGradeidAndClazzid(stu.getGradeid(),stu.getClazzid());
        for (Exam exam:list) {
            Clazz clazz = clazzDao.selectByPrimaryKey(exam.getClazzid());
            Grade grade = gradeDao.selectByPrimaryKey(exam.getGradeid());
            Course course = courseDao.selectByPrimaryKey(exam.getCourseid());
            exam.setClazz(clazz);
            exam.setGrade(grade);
            exam.setCourse(course);
        }
        return list;
    }
}
