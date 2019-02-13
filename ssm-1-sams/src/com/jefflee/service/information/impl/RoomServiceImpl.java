package com.jefflee.service.information.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jefflee.entity.information.Room;
import com.jefflee.mapper.information.RoomMapper;
import com.jefflee.po.information.RoomPo;
import com.jefflee.service.information.RoomService;

@Service("roomService")
public class RoomServiceImpl implements RoomService {

	@Resource(name = "roomMapper")
	private RoomMapper roomMapper;

	@Override
	public Integer insert(RoomPo roomPo) {
		if (roomMapper.insert(roomPo) == 1) {
			return roomPo.getRoomId();
		} else {
			return null;
		}
	}

	@Override
	public List<RoomPo> selectAll() {
		return roomMapper.selectAll();
	}

	@Override
	public RoomPo selectById(Integer roomId) {
		return roomMapper.selectByPrimaryKey(roomId);
	}

	@Override
	public Integer updateById(RoomPo roomPo) {
		if (roomMapper.updateByPrimaryKey(roomPo) == 1) {
			return roomPo.getRoomId();
		} else {
			return null;
		}
	}

	@Override
	public Integer deleteById(Integer roomId) {
		if (roomMapper.deleteByPrimaryKey(roomId) == 1) {
			return roomId;
		} else {
			return null;
		}
	}

	@Override
	public List<Room> selectRoomList() {
		return roomMapper.selectEntityList();
	}
}
