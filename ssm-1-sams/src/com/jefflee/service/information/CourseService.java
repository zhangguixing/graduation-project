package com.jefflee.service.information;

import java.util.List;

import com.jefflee.entity.information.Course;
import com.jefflee.po.information.CoursePo;

public interface CourseService {

	Integer insert(CoursePo coursePo);

	List<CoursePo> selectAll();

	CoursePo selectById(Integer courseId);

	Integer updateById(CoursePo coursePo);

	Integer deleteById(Integer courseId);

	List<Course> selectCourseList();

}
