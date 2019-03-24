package com.sams.controller;

import com.sams.entity.Course;
import com.sams.response.JsonResult;
import com.sams.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("courseList")
    public List<Course> getCourseList(Integer gradeid){
        List<Course> courseList = null;
        if(gradeid == null){
            courseList = courseService.courseList();
        }else {
            courseList = courseService.findByGradeid(gradeid);
        }
        return courseList;
    }

    @PostMapping("deleteCourse")
    public JsonResult deleteCourse(Integer courseid){
        return courseService.deleteCourse(courseid);
    }

    @PostMapping("addCourse")
    public JsonResult addCourse(Course course){
        return courseService.addCourse(course);
    }
}
