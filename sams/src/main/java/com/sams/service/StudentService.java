package com.sams.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.sams.dao.*;
import com.sams.entity.*;
import com.sams.response.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private EScoreDao scoreDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ClazzDao clazzDao;
    @Autowired
    private GradeDao gradeDao;

    public List<Student> findByGradeid(int gradeid) {
        Example example = new Example(Student.class);
        example.createCriteria().andEqualTo("gradeid",gradeid);
        return studentDao.selectByExample(example);
    }

    public List<Student> findByClazzid(int clazzid) {
        Example example = new Example(Student.class);
        example.createCriteria().andEqualTo("clazzid",clazzid);
        return studentDao.selectByExample(example);
    }

    public String studentList(Integer gradeid, Integer clazzid, Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        Example example = new Example(Student.class);
        Example.Criteria criteria = example.createCriteria();
        if(gradeid!=null){
            criteria.andEqualTo("gradeid",gradeid);
        }
        if(clazzid != null){
            criteria.andEqualTo("clazzid",clazzid);
        }
        example.setOrderByClause("id desc");
        List<Student> studentList = studentDao.selectByExample(example);
        int total = studentDao.selectCount(new Student());
        JSONObject result = new JSONObject();
        result.put("rows",studentList);
        result.put("total",total);
        return JSONUtils.toJSONString(result);

    }

    public JsonResult deleteStudent(String[] numbers, String[] ids) {
        List<String> idList = Arrays.asList(ids);
        try {
            Example escoreExample = new Example(EScore.class);
            escoreExample.createCriteria().andIn("studentid",idList);
            scoreDao.deleteByExample(escoreExample);

            Example studentExample = new Example(Student.class);
            studentExample.createCriteria().andIn("id",idList);
            studentDao.deleteByExample(studentExample);

            Example userExample = new Example(EScore.class);
            userExample.createCriteria().andIn("account",idList);
            userDao.deleteByExample(userExample);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("删除失败");
        }
        return JsonResult.ok();
    }

    public JsonResult addStudent(Student student) {
        try{
            studentDao.insert(student);
            User user = new User();
            user.setAccount(student.getNumber());
            user.setName(student.getName());
            user.setType(User.USER_STUDENT);
            userDao.insert(user);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("添加失败");
        }
        return JsonResult.ok();
    }

    public JsonResult editStudent(Student student) {
        try{
            studentDao.updateByPrimaryKeySelective(student);
            User user = new User();
            user.setName(student.getName());
            user.setAccount(student.getNumber());
            userDao.updateByPrimaryKeySelective(user);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("修改失败");
        }
        return JsonResult.ok();
    }

    public Student findByNumber(String number) {
        Student student = new Student();
        student.setNumber(number);
        return studentDao.selectOne(student);
    }

    public String findByGradeidAndClazzid(Student student,int page,int rows) {
        PageHelper.startPage(page,rows);
        Example example = new Example(Student.class);
        if(student.getGrade() != null){
            example.createCriteria().andEqualTo("gradeid",student.getGradeid());
        }
        if(student.getClazz()!=null){
            example.createCriteria().andEqualTo("clazzid",student.getClazzid());
        }
        example.orderBy("id desc");
        List<Student> studentList = studentDao.selectByExample(example);
        PageHelper.clearPage();
        for (Student stu:studentList) {
            Clazz clazz = clazzDao.selectByPrimaryKey(stu.getClazzid());
            Grade grade = gradeDao.selectByPrimaryKey(stu.getGradeid());
            stu.setClazz(clazz);
            stu.setGrade(grade);
        }
        int total = studentDao.selectCount(student);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        //total键 存放总记录数，必须的
        jsonMap.put("total", total);
        //rows键 存放每页记录 list
        jsonMap.put("rows", studentList);
        //格式化Map,以json格式返回数据
        return JSONObject.toJSONString(jsonMap);
    }

}
