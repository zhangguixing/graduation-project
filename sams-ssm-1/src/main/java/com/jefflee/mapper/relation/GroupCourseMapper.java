package com.jefflee.mapper.relation;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

import com.jefflee.entity.relation.GroupCourse;
import com.jefflee.po.relation.GroupCoursePo;


public interface GroupCourseMapper extends Mapper<GroupCoursePo> {

	@Select("select * from rlat_group_course where group_course_id = #{groupCourseId}")
	@Results({ @Result(id = true, column = "group_course_id", property = "groupCourseId"),
			@Result(column = "group_id", property = "groupId"), @Result(column = "course_id", property = "courseId")})
	//column 是数据库表中字段，property 是对应实体的属性
	public GroupCourse selectEntityById(Integer groupCourseId);

}