package com.jefflee.entity.schedule;

import com.jefflee.po.schedule.GroupPo;

public class Group {
	private Integer groupId;
	private Integer year;
	private Integer semester;
	private Integer level;
	private Integer grade;
	private String startDate;

	public Group() {
	}

	public Group(Integer groupId) {
		this.groupId = groupId;
	}

	public Group(GroupPo groupPo) {
		groupId = groupPo.getGroupId();
		year = groupPo.getYear();
		semester = groupPo.getSemester();
		level = groupPo.getLevel();
		grade = groupPo.getGrade();
		startDate = groupPo.getStartDate();
	}

	public GroupPo toPo() {
		GroupPo groupPo = new GroupPo();
		groupPo.setGroupId(groupId);
		groupPo.setYear(year);
		groupPo.setSemester(semester);
		groupPo.setLevel(level);
		groupPo.setGrade(grade);
		groupPo.setStartDate(startDate);
		return groupPo;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getSemester() {
		return semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
}
