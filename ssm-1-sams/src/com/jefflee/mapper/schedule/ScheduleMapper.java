package com.jefflee.mapper.schedule;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.jefflee.entity.schedule.Schedule;
import com.jefflee.po.schedule.SchedulePo;

import tk.mybatis.mapper.common.Mapper;

public interface ScheduleMapper extends Mapper<SchedulePo> {

	@Select("select * from schd_schedule where schedule_id = #{scheduleId}")
	@Results({ @Result(id = true, column = "schedule_id", property = "scheduleId"),
			@Result(column = "group_id", property = "group", one = @One(select = "com.jefflee.mapper.schedule.GroupMapper.selectEntityById") ),
			@Result(column = "forenoon", property = "forenoon"), @Result(column = "afternoon", property = "afternoon"),
			@Result(column = "evening", property = "evening"), @Result(column = "days", property = "days"),
			@Result(column = "start_week", property = "startWeek") })
	public Schedule selectEntityById(Integer scheduleId);

}
