package com.sams.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.sams.dao.*;
import com.sams.entity.*;
import com.sams.response.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CourseItemDao courseItemDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private GradeDao gradeDao;
    @Autowired
    private ClazzDao clazzDao;

    public String getTeacherList(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        Example example = new Example(Teacher.class);
        example.setOrderByClause("id desc");
        List<Teacher> examList = teacherDao.selectByExample(example);
        int total = teacherDao.selectCount(new Teacher());
        JSONObject result = new JSONObject();
        result.put("rows",examList);
        result.put("total",total);
        return JSONUtils.toJSONString(result);
    }

    public JsonResult deleteStudent(String[] numbers, String[] ids) {
        try{
            //删除教师与课程的关联
            teacherDao.deleteClazzCourseTeacherByTeacheridIn(ids);
            //删除教师
            Example teacherExample = new Example(Teacher.class);
            List<String> idList = Arrays.asList(ids);
            teacherExample.createCriteria().andIn("id",idList);
            teacherDao.deleteByExample(teacherExample);
            //删除系统用户
            List<String> numberList = Arrays.asList(numbers);
            Example userExample = new Example(User.class);
            userExample.createCriteria().andIn("account",numberList);
            userDao.deleteByExample(userExample);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("删除失败");
        }
        return JsonResult.ok();
    }

    public JsonResult addTeacher(Teacher teacher) {
        try {
            //添加教师信息
            teacherDao.insertReturnKey(teacher);
            int teacherid = teacher.getId();
            //设置课程
            if(teacher.getCourse() != null && teacher.getCourse().length > 0){
                for(String course : teacher.getCourse()){
                    String[] gcc = course.split("_");
                    int gradeid = Integer.parseInt(gcc[0]);
                    int clazzid = Integer.parseInt(gcc[1]);
                    int courseid = Integer.parseInt(gcc[2]);
                    teacherDao.insertClazzCourseTeacher(gradeid,clazzid,courseid,teacherid);
                }
            }
            //添加用户记录
            User user = new User();
            user.setAccount(teacher.getNumber());
            user.setName(teacher.getName());
            user.setType(User.USER_TEACHER);
            userDao.insert(user);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("插入异常");
        }
        return JsonResult.ok();
    }

    public JsonResult editTeacher(Teacher teacher) {
        try{
            teacherDao.updateByPrimaryKeySelective(teacher);
            teacherDao.deleteClazzCourseTeacherByTeacherid(teacher.getId());
            //设置课程
            if(teacher.getCourse() != null && teacher.getCourse().length > 0){
                for(String course : teacher.getCourse()){
                    String[] gcc = course.split("_");
                    int gradeid = Integer.parseInt(gcc[0]);
                    int clazzid = Integer.parseInt(gcc[1]);
                    int courseid = Integer.parseInt(gcc[2]);
                    teacherDao.insertClazzCourseTeacher(gradeid,clazzid,courseid,teacher.getId());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("修改失败");
        }
        return JsonResult.ok();
    }

    public Teacher getTeacher(String number) {
        Example teacherExample = new Example(Teacher.class);
        teacherExample.createCriteria().andEqualTo("number",number);
        Teacher teacher = teacherDao.selectOneByExample(teacherExample);
        Example courseItemExample = new Example(CourseItem.class);
        courseItemExample.createCriteria().andEqualTo("teacherid",teacher.getId());
        findTeacherByExample(courseItemExample,teacher);
        return teacher;
    }

    public List<Clazz> getExamClazz(Integer gradeid, String number) {
        Example teacherExample = new Example(Teacher.class);
        teacherExample.createCriteria().andEqualTo("number",number);
        Teacher teacher = teacherDao.selectOneByExample(teacherExample);
        Example courseItemExample = new Example(CourseItem.class);
        courseItemExample.createCriteria().andEqualTo("teacherid",teacher.getId());
        courseItemExample.createCriteria().andEqualTo("gradeid",gradeid);
        findTeacherByExample(courseItemExample,teacher);
        List<Clazz> clazzList = new LinkedList<>();
        List<CourseItem> courseItem = teacher.getCourseList();
        for(CourseItem item : courseItem){
            boolean flag = true;
            for(Clazz clazz : clazzList){
                if(clazz.getId() == item.getClazzid()){
                    flag = false;
                    break;
                }
            }
            if(flag){
                clazzList.add(item.getClazz());
            }
        }
        return clazzList;
    }

    private void findTeacherByExample(Example example,Teacher teacher){
        List<CourseItem> courseItemList = courseItemDao.selectByExample(example);
        for (CourseItem courseItem:courseItemList) {
            Clazz clazz = clazzDao.selectByPrimaryKey(courseItem.getClazzid());
            Grade grade = gradeDao.selectByPrimaryKey(courseItem.getGradeid());
            Course course = courseDao.selectByPrimaryKey(courseItem.getCourseid());
            courseItem.setClazz(clazz);
            courseItem.setGrade(grade);
            courseItem.setCourse(course);
        }
        teacher.setCourseList(courseItemList);
    }

    public List<CourseItem> getExamCourse(Integer gradeid, Integer clazzid, String number) {
        Example teacherExample = new Example(Teacher.class);
        teacherExample.createCriteria().andEqualTo("number",number);
        Teacher teacher = teacherDao.selectOneByExample(teacherExample);
        Example courseItemExample = new Example(CourseItem.class);
        courseItemExample.createCriteria().andEqualTo("teacherid",teacher.getId());
        courseItemExample.createCriteria().andEqualTo("clazzid",clazzid);
        courseItemExample.createCriteria().andEqualTo("gradeid",gradeid);
        findTeacherByExample(courseItemExample,teacher);
        return teacher.getCourseList();
    }

    public JsonResult editTeacherPersonal(Teacher teacher) {
        try {
            teacherDao.updateTeacherByNumber(teacher);
            userDao.updateNameByAccount(teacher.getName(),teacher.getNumber());
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("更新失败");
        }
        return JsonResult.ok();
    }
}
