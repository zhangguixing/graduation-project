package com.sams.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.sams.dao.*;
import com.sams.entity.*;
import com.sams.response.JsonResult;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ClazzService {
    @Autowired
    private ClazzDao clazzDao;
    @Autowired
    private EScoreDao scoreDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ExamDao examDao;

    public String getClazzList(Integer gradeId) {
        Example example = new Example(Clazz.class);
        example.createCriteria().andEqualTo("gradeid",gradeId);
        List<Clazz> clazzeList = clazzDao.selectByExample(example);
        //json化
        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"grade", "studentList"});
        return JSONArray.fromObject(clazzeList, config).toString();
    }

    public JsonResult addClazz(Clazz clazz) {
        try {
            clazzDao.insert(clazz);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("添加失败");
        }
        return JsonResult.ok();
    }

    public String clazzDetailList(Integer gradeid, Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<Map<String,Object>> mapList = clazzDao.clazzDetailList(gradeid);
        List<Clazz> list = new ArrayList<>();
        for (Map map:mapList) {
            Clazz clazz = new Clazz();
            Grade grade = new Grade();
            grade.setId(map.get("gid")!=null?null:Integer.valueOf(map.get("gid").toString()));
            grade.setName(map.get("gname")!=null?null:map.get("gname").toString());
            clazz.setId(map.get("cid")!=null?null:Integer.valueOf(map.get("cid").toString()));
            clazz.setName(map.get("cname")!=null?null:map.get("cname").toString());
            clazz.setGradeid(map.get("gid")!=null?null:Integer.valueOf(map.get("gid").toString()));
            //添加
            clazz.setGrade(grade);
            //添加到集合
            list.add(clazz);
        }
        PageHelper.clearPage();
        Example example = new Example(Clazz.class);
        if(gradeid!=null){
            example.createCriteria().andEqualTo("gradeid",gradeid);
        }
        clazzDao.selectCountByExample(example);
        int total = clazzDao.selectCountByExample(example);
        JSONObject result = new JSONObject();
        result.put("rows",list);
        result.put("total",total);
        return JSONUtils.toJSONString(result);
    }

    public JsonResult deleteClazz(Integer clazzid) {
       try {
           //删除成绩表
           EScore score = new EScore();
           score.setClazzid(clazzid);
           scoreDao.delete(score);
           //删除考试记录
           Exam exam = new Exam();
           exam.setClazzid(clazzid);
           examDao.delete(exam);
           //删除用户
           Student student = new Student();
           student.setClazzid(clazzid);
           List<Student> list = studentDao.select(student);
           if(list.size() > 0){
               List<String> accountList = new ArrayList<>();
               for (Student stu:list) {
                   accountList.add(stu.getNumber());
               }
               Example userExample = new Example(User.class);
               userExample.createCriteria().andIn("account",accountList);
               userDao.deleteByExample(userExample);
               //删除学生
               studentDao.delete(student);
           }
           //删除班级的课程和老师的关联
           clazzDao.deleteClazzCourseTeacherByClazzid(clazzid);
           //最后删除班级
           clazzDao.deleteByPrimaryKey(clazzid);
       }catch (Exception e){
           e.printStackTrace();
           return JsonResult.error("删除失败");
       }
       return JsonResult.ok();
    }
}
