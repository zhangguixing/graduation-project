package com.jefflee.po.relation;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Table(name = "rlat_group_teacher")
public class GroupTeacherPo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer groupTeacherId;
	private Integer groupId;
	private Integer teacherId;
	
	
	public Integer getGroupTeacherId() {
		return groupTeacherId;
	}
	public void setGroupTeacherId(Integer groupTeacherId) {
		this.groupTeacherId = groupTeacherId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
	

}
