package com.jefflee.mapper.schedule;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.jefflee.entity.schedule.Adjustment;
import com.jefflee.po.schedule.AdjustmentPo;

import tk.mybatis.mapper.common.Mapper;

public interface AdjustmentMapper extends Mapper<AdjustmentPo> {

	@Select("select * from schd_adjustment where adjustment_id = #{adjustmentId}")
	@Results({ @Result(id = true, column = "adjustment_id", property = "adjustmentId"),
			@Result(column = "schedule_id", property = "scheduleId"),
			@Result(column = "start_week", property = "startWeek"), @Result(column = "duration", property = "duration"),
			@Result(column = "type", property = "type"), @Result(column = "first_id", property = "firstId"),
			@Result(column = "second_id", property = "secondId") })
	public Adjustment selectEntityById(Integer adjustmentId);

	@Select("select * from schd_adjustment where schedule_id = #{scheduleId} and duration = 0")
	@Results({ @Result(id = true, column = "adjustment_id", property = "adjustmentId"),
			@Result(column = "schedule_id", property = "scheduleId"),
			@Result(column = "start_week", property = "startWeek"), @Result(column = "duration", property = "duration"),
			@Result(column = "type", property = "type"), @Result(column = "first_id", property = "firstId"),
			@Result(column = "second_id", property = "secondId") })
	public List<Adjustment> selectTempEntityListByScheduleId(Integer scheduleId);

}
