package com.jefflee.mapper.schedule;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.jefflee.entity.schedule.Arrangement;
import com.jefflee.po.schedule.ArrangementPo;

import tk.mybatis.mapper.common.Mapper;

public interface ArrangementMapper extends Mapper<ArrangementPo> {

	@Select("select * from schd_arrangement where arrangement_id = #{arrangementId}")
	@Results({ @Result(id = true, column = "arrangement_id", property = "arrangementId"),
			@Result(column = "schedule_id", property = "scheduleId"),
			@Result(column = "period_id", property = "period", one = @One(select = "com.jefflee.mapper.information.PeriodMapper.selectEntityById") ),
			@Result(column = "course_id", property = "course", one = @One(select = "com.jefflee.mapper.information.CourseMapper.selectEntityById") ),
			@Result(column = "room_id", property = "room", one = @One(select = "com.jefflee.mapper.information.RoomMapper.selectEntityById") ),
			@Result(column = "tclass_id", property = "tclass", one = @One(select = "com.jefflee.mapper.information.TclassMapper.selectEntityById") ),
			@Result(column = "teacher_id", property = "teacher", one = @One(select = "com.jefflee.mapper.information.TeacherMapper.selectEntityById") ),
			@Result(column = "arranged", property = "arranged"), @Result(column = "priority", property = "priority") })
	public Arrangement selectEntityById(Integer arrangementId);

	@Select("select * from schd_arrangement where schedule_id = #{scheduleId}")
	@Results({ @Result(id = true, column = "arrangement_id", property = "arrangementId"),
			@Result(column = "schedule_id", property = "scheduleId"),
			@Result(column = "period_id", property = "period", one = @One(select = "com.jefflee.mapper.information.PeriodMapper.selectEntityById") ),
			@Result(column = "course_id", property = "course", one = @One(select = "com.jefflee.mapper.information.CourseMapper.selectEntityById") ),
			@Result(column = "room_id", property = "room", one = @One(select = "com.jefflee.mapper.information.RoomMapper.selectEntityById") ),
			@Result(column = "tclass_id", property = "tclass", one = @One(select = "com.jefflee.mapper.information.TclassMapper.selectEntityById") ),
			@Result(column = "teacher_id", property = "teacher", one = @One(select = "com.jefflee.mapper.information.TeacherMapper.selectEntityById") ),
			@Result(column = "arranged", property = "arranged"), @Result(column = "priority", property = "priority") })
	public List<Arrangement> selectEntityListByScheduleId(Integer scheduleId);

	public Integer insertList(List<ArrangementPo> arrangementPoList);

}
