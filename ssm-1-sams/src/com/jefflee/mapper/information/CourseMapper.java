package com.jefflee.mapper.information;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.jefflee.entity.information.Course;
import com.jefflee.po.information.CoursePo;

import tk.mybatis.mapper.common.Mapper;

@Repository("courseMapper")
public interface CourseMapper extends Mapper<CoursePo> {

	@Select("select * from info_course where course_id = #{courseId}")
	@Results({ @Result(id = true, column = "course_id", property = "courseId"),
			@Result(column = "course_no", property = "courseNo"), @Result(column = "name", property = "name"),
			@Result(column = "short_name", property = "shortName"), @Result(column = "type", property = "type") })
	public Course selectEntityById(Integer courseId);

	@Select("select * from info_course")
	@Results({ @Result(id = true, column = "course_id", property = "courseId"),
			@Result(column = "course_no", property = "courseNo"), @Result(column = "name", property = "name"),
			@Result(column = "short_name", property = "shortName"), @Result(column = "type", property = "type") })
	public List<Course> selectEntityList();

}
