package com.sams.service;

import com.sams.dao.CourseDao;
import com.sams.dao.GradeDao;
import com.sams.entity.Course;
import com.sams.entity.Grade;
import com.sams.util.StringUtils;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class GradeService {
    @Autowired
    private GradeDao gradeDao;
    @Autowired
    private CourseDao courseDao;

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
}
