package com.jefflee.service.information.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jefflee.entity.information.Course;
import com.jefflee.mapper.information.CourseMapper;
import com.jefflee.po.information.CoursePo;
import com.jefflee.service.information.CourseService;

@Service("courseService")
public class CourseServiceImpl implements CourseService {

	@Resource(name = "courseMapper")
	private CourseMapper courseMapper;

	@Override
	public Integer insert(CoursePo coursePo) {
		if (courseMapper.insert(coursePo) == 1) {
			return coursePo.getCourseId();
		} else {
			return null;
		}
	}

	@Override
	public List<CoursePo> selectAll() {
		return courseMapper.selectAll();
	}

	@Override
	public CoursePo selectById(Integer courseId) {
		return courseMapper.selectByPrimaryKey(courseId);
	}

	@Override
	public Integer updateById(CoursePo coursePo) {
		if (courseMapper.updateByPrimaryKey(coursePo) == 1) {
			return coursePo.getCourseId();
		} else {
			return null;
		}
	}

	@Override
	public Integer deleteById(Integer courseId) {
		if (courseMapper.deleteByPrimaryKey(courseId) == 1) {
			return courseId;
		} else {
			return null;
		}
	}

	@Override
	public List<Course> selectCourseList() {
		return courseMapper.selectEntityList();
	}
}
