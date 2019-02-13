

package com.jefflee.po.relation;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Table(name = "rlat_group_course")
public class GroupCoursePo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer groupCourseId;
	private Integer groupId;
	private Integer courseId;
	
	
	
	public Integer getGroupCourseId() {
		return groupCourseId;
	}
	public void setGroupCourseId(Integer groupCourseId) {
		this.groupCourseId = groupCourseId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	
	

}
