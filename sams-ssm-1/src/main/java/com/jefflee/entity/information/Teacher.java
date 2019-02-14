package com.jefflee.entity.information;

import com.jefflee.po.information.TeacherPo;

public class Teacher {
	private Integer teacherId;
	private String teacherNo;
	private String name;
	private Integer type;

	public Teacher() {
	}

	public Teacher(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public Teacher(TeacherPo teacherPo) {
		teacherId = teacherPo.getTeacherId();
		teacherNo = teacherPo.getTeacherNo();
		name = teacherPo.getName();
		type = teacherPo.getType();
	}

	public TeacherPo toPo() {
		TeacherPo teacherPo = new TeacherPo();
		teacherPo.setTeacherId(teacherId);
		teacherPo.setTeacherNo(teacherNo);
		teacherPo.setName(name);
		teacherPo.setType(type);
		return teacherPo;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherNo() {
		return teacherNo;
	}

	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
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
