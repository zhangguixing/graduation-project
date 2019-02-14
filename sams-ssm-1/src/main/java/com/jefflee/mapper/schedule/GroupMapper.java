package com.jefflee.mapper.schedule;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.jefflee.entity.schedule.Group;
import com.jefflee.po.schedule.GroupPo;

import tk.mybatis.mapper.common.Mapper;

public interface GroupMapper extends Mapper<GroupPo> {

	@Select("select * from schd_group where group_id = #{groupId}")
	@Results({ @Result(id = true, column = "group_id", property = "groupId"),
			@Result(column = "year", property = "year"), @Result(column = "semester", property = "semester"),
			@Result(column = "level", property = "level"), @Result(column = "grade", property = "grade"),
			@Result(column = "start_date", property = "startDate") })
	//column 是数据库表中字段，property 是对应实体的属性
	public Group selectEntityById(Integer groupId);

}
