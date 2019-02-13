package com.jefflee.mapper.information;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.jefflee.entity.information.Room;
import com.jefflee.po.information.RoomPo;

import tk.mybatis.mapper.common.Mapper;

@Repository("roomMapper")
public interface RoomMapper extends Mapper<RoomPo> {

	@Select("select * from info_room where room_id = #{roomId}")
	@Results({ @Result(id = true, column = "room_id", property = "roomId"),
			@Result(column = "room_no", property = "roomNo"), @Result(column = "name", property = "name"),
			@Result(column = "type", property = "type") })
	public Room selectEntityById(Integer roomId);

	@Select("select * from info_room")
	@Results({ @Result(id = true, column = "room_id", property = "roomId"),
			@Result(column = "room_no", property = "roomNo"), @Result(column = "name", property = "name"),
			@Result(column = "type", property = "type") })
	public List<Room> selectEntityList();

}
