package com.jefflee.entity.schedule;

import com.jefflee.po.schedule.SchedulePo;

public class Schedule {
	private Integer scheduleId;
	private Group group;
	private Integer forenoon;
	private Integer afternoon;
	private Integer evening;
	private Integer days;
	private Integer startWeek;

	public Schedule() {
		this.group = new Group();
	}

	public Schedule(Integer scheduleId) {
		this.scheduleId = scheduleId;
		this.group = new Group();
	}

	public Schedule(SchedulePo schedulePo) {
		scheduleId = schedulePo.getScheduleId();
		group = new Group(schedulePo.getGroupId());
		forenoon = schedulePo.getForenoon();
		afternoon = schedulePo.getAfternoon();
		evening = schedulePo.getEvening();
		days = schedulePo.getDays();
		startWeek = schedulePo.getStartWeek();
	}

	public SchedulePo toPo() {
		SchedulePo schedulePo = new SchedulePo();
		schedulePo.setScheduleId(scheduleId);
		schedulePo.setGroupId(group.getGroupId());
		schedulePo.setForenoon(forenoon);
		schedulePo.setAfternoon(afternoon);
		schedulePo.setEvening(evening);
		schedulePo.setDays(days);
		schedulePo.setStartWeek(startWeek);
		return schedulePo;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
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
