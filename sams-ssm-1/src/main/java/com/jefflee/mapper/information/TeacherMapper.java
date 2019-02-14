package com.jefflee.mapper.information;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.jefflee.entity.information.Teacher;
import com.jefflee.po.information.TeacherPo;

import tk.mybatis.mapper.common.Mapper;

@Repository("teacherMapper")
public interface TeacherMapper extends Mapper<TeacherPo> {

	@Select("select * from info_teacher where teacher_id = #{teacherId}")
	@Results({ @Result(id = true, column = "teacher_id", property = "teacherId"),
			@Result(column = "teacher_no", property = "teacherNo"), @Result(column = "name", property = "name"),
			@Result(column = "type", property = "type") })
	public Teacher selectEntityById(Integer teacherId);

	@Select("select * from info_teacher")
	@Results({ @Result(id = true, column = "teacher_id", property = "teacherId"),
			@Result(column = "teacher_no", property = "teacherNo"), @Result(column = "name", property = "name"),
			@Result(column = "type", property = "type") })
	public List<Teacher> selectEntityList();

}
