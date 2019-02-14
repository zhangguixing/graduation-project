package com.jefflee.entity.information;

import com.jefflee.po.information.CoursePo;

public class Course {
	private Integer courseId;
	private String courseNo;
	private String name;
	private String shortName;
	private Integer type;

	public Course() {
	}

	public Course(Integer courseId) {
		this.courseId = courseId;
	}

	public Course(CoursePo coursePo) {
		courseId = coursePo.getCourseId();
		courseNo = coursePo.getCourseNo();
		name = coursePo.getName();
		shortName = coursePo.getName();
		type = coursePo.getType();
	}

	public CoursePo toPo() {
		CoursePo coursePo = new CoursePo();
		coursePo.setCourseId(courseId);
		coursePo.setCourseNo(courseNo);
		coursePo.setName(name);
		coursePo.setShortName(shortName);
		coursePo.setType(type);
		return coursePo;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseNo() {
		return courseNo;
	}

	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
