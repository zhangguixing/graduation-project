package com.jefflee.entity.information;

import com.jefflee.po.information.RoomPo;

public class Room {
	private Integer roomId;
	private String roomNo;
	private String name;
	private Integer type;

	public Room() {
	}

	public Room(Integer roomId) {
		this.roomId = roomId;
	}

	public Room(RoomPo roomPo) {
		roomId = roomPo.getRoomId();
		roomNo = roomPo.getRoomNo();
		name = roomPo.getName();
		type = roomPo.getType();
	}

	public RoomPo toPo() {
		RoomPo roomPo = new RoomPo();
		roomPo.setRoomId(roomId);
		roomPo.setRoomNo(roomNo);
		roomPo.setName(name);
		roomPo.setType(type);
		return roomPo;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
