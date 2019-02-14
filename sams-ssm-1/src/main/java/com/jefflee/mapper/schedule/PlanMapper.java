package com.jefflee.mapper.schedule;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.jefflee.entity.schedule.Plan;
import com.jefflee.po.schedule.PlanPo;

import tk.mybatis.mapper.common.Mapper;

public interface PlanMapper extends Mapper<PlanPo> {

	@Select("select * from schd_plan where plan_id = #{planId}")
	@Results({ @Result(id = true, column = "plan_id", property = "planId"),
			@Result(column = "schedule_id", property = "scheduleId"),
			@Result(column = "course_id", property = "course", one = @One(select = "com.jefflee.mapper.information.CourseMapper.selectEntityById") ),
			@Result(column = "room_id", property = "room", one = @One(select = "com.jefflee.mapper.information.RoomMapper.selectEntityById") ),
			@Result(column = "tclass_id", property = "tclass", one = @One(select = "com.jefflee.mapper.information.TclassMapper.selectEntityById") ),
			@Result(column = "teacher_id", property = "teacher", one = @One(select = "com.jefflee.mapper.information.TeacherMapper.selectEntityById") ),
			@Result(column = "period_num", property = "periodNum") })
	public Plan selectEntityById(Integer planId);

	@Select("select * from schd_plan where schedule_id = #{scheduleId} order by teacher_id, course_id")
	@Results({ @Result(id = true, column = "plan_id", property = "planId"),
			@Result(column = "schedule_id", property = "scheduleId"),
			@Result(column = "course_id", property = "course", one = @One(select = "com.jefflee.mapper.information.CourseMapper.selectEntityById") ),
			@Result(column = "room_id", property = "room", one = @One(select = "com.jefflee.mapper.information.RoomMapper.selectEntityById") ),
			@Result(column = "tclass_id", property = "tclass", one = @One(select = "com.jefflee.mapper.information.TclassMapper.selectEntityById") ),
			@Result(column = "teacher_id", property = "teacher", one = @One(select = "com.jefflee.mapper.information.TeacherMapper.selectEntityById") ),
			@Result(column = "period_num", property = "periodNum") })
	public List<Plan> selectEntityListByScheduleId(Integer scheduleId);

}
