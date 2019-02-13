package com.jefflee.service.information;

import java.util.List;

import com.jefflee.entity.information.Room;
import com.jefflee.po.information.RoomPo;

public interface RoomService {

	Integer insert(RoomPo roomPo);

	List<RoomPo> selectAll();

	RoomPo selectById(Integer roomId);

	Integer updateById(RoomPo roomPo);

	Integer deleteById(Integer roomId);

	List<Room> selectRoomList();

}
