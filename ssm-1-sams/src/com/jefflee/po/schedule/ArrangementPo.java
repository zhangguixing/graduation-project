package com.jefflee.po.schedule;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "schd_arrangement")
public class ArrangementPo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer arrangementId;
	private Integer scheduleId;
	private Integer periodId;
	private Integer courseId;
	private Integer roomId;
	private Integer tclassId;
	private Integer teacherId;
	private Integer arranged;
	private Integer priority;

	public Integer getArrangementId() {
		return arrangementId;
	}

	public void setArrangementId(Integer arrangementId) {
		this.arrangementId = arrangementId;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Integer getPeriodId() {
		return periodId;
	}

	public void setPeriodId(Integer periodId) {
		this.periodId = periodId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getTclassId() {
		return tclassId;
	}

	public void setTclassId(Integer tclassId) {
		this.tclassId = tclassId;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public Integer getArranged() {
		return arranged;
	}

	public void setArranged(Integer arranged) {
		this.arranged = arranged;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

}
