package com.jefflee.mapper.relation;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

import com.jefflee.entity.relation.GroupTeacher;
import com.jefflee.po.relation.GroupTeacherPo;


public interface GroupTeacherMapper extends Mapper<GroupTeacherPo> {

	@Select("select * from rlat_group_teacher where group_teacher_id = #{groupTeacherId}")
	@Results({ @Result(id = true, column = "group_teacher_id", property = "groupTeacherId"),
			@Result(column = "group_id", property = "groupId"), @Result(column = "teacher_id", property = "teacherId")})
	//column 是数据库表中字段，property 是对应实体的属性
	public GroupTeacher selectEntityById(Integer groupTeacherId);

}