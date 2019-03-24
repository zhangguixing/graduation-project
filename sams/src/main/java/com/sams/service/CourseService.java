package com.sams.service;

import com.sams.dao.CourseDao;
import com.sams.dao.EScoreDao;
import com.sams.entity.Course;
import com.sams.entity.EScore;
import com.sams.response.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private EScoreDao scoreDao;

    public List<Course> findByGradeid(int gradeid) {
        Example example = new Example(Course.class);
        example.createCriteria().andEqualTo("gradeid",gradeid);
        return courseDao.selectByExample(example);
    }

    public List<Course> courseList() {
        return courseDao.selectAll();
    }

    public JsonResult deleteCourse(Integer courseid) {
        try {
            //删除成绩表
            EScore score = new EScore();
            score.setClazzid(courseid);
            scoreDao.delete(score);
            //删除班级的课程和老师的关联
            courseDao.deletelazzCourseTeacherByCourseid(courseid);
            //删除年级与课程关联
            courseDao.deleteGradeCourseByCourseid(courseid);
            //最后删除课程
            courseDao.deleteByPrimaryKey(courseid);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("删除失败");
        }
        return JsonResult.ok();
    }

    public JsonResult addCourse(Course course){
        try {
            courseDao.insert(course);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("添加失败");
        }
        return JsonResult.ok();
    }
}
