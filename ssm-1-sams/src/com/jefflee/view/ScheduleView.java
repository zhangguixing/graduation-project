package com.jefflee.view;

import java.util.Map;

import com.jefflee.entity.schedule.Schedule;

public class ScheduleView {
	private Schedule schedule;
	private Map<String, WeekView> tclassWeekViewMap;
	private Map<String, WeekView> teacherWeekViewMap;

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Map<String, WeekView> getTclassWeekViewMap() {
		return tclassWeekViewMap;
	}

	public void setTclassWeekViewMap(Map<String, WeekView> tclassWeekViewMap) {
		this.tclassWeekViewMap = tclassWeekViewMap;
	}

	public Map<String, WeekView> getTeacherWeekViewMap() {
		return teacherWeekViewMap;
	}

	public void setTeacherWeekViewMap(Map<String, WeekView> teacherWeekViewMap) {
		this.teacherWeekViewMap = teacherWeekViewMap;
	}
}
