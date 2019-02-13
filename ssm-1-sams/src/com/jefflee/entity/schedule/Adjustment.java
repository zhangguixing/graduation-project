package com.jefflee.entity.schedule;

public class Adjustment {
	private Integer adjustmentId;
	private Integer scheduleId;
	private Integer startWeek;
	private Integer duration;
	private Integer type;
	private String firstId;
	private String secondId;

	public Integer getAdjustmentId() {
		return adjustmentId;
	}

	public void setAdjustmentId(Integer adjustmentId) {
		this.adjustmentId = adjustmentId;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Integer getStartWeek() {
		return startWeek;
	}

	public void setStartWeek(Integer startWeek) {
		this.startWeek = startWeek;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getFirstId() {
		return firstId;
	}

	public void setFirstId(String firstId) {
		this.firstId = firstId;
	}

	public String getSecondId() {
		return secondId;
	}

	public void setSecondId(String secondId) {
		this.secondId = secondId;
	}

}
