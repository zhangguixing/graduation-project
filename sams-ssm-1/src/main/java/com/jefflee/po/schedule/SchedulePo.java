package com.jefflee.po.schedule;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Table(name = "schd_schedule")
public class SchedulePo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer scheduleId;
	private Integer groupId;
	private Integer forenoon;
	private Integer afternoon;
	private Integer evening;
	private Integer days;
	@OrderBy
	private Integer startWeek;

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getForenoon() {
		return forenoon;
	}

	public void setForenoon(Integer forenoon) {
		this.forenoon = forenoon;
	}

	public Integer getAfternoon() {
		return afternoon;
	}

	public void setAfternoon(Integer afternoon) {
		this.afternoon = afternoon;
	}

	public Integer getEvening() {
		return evening;
	}

	public void setEvening(Integer evening) {
		this.evening = evening;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getStartWeek() {
		return startWeek;
	}

	public void setStartWeek(Integer startWeek) {
		this.startWeek = startWeek;
	}

}
