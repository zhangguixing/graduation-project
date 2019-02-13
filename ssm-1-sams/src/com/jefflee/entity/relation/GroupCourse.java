package com.jefflee.entity.relation;

import com.jefflee.po.relation.GroupCoursePo;



public class GroupCourse {
	
	public Integer groupCourseId;
	public Integer groupId;
	public Integer courseId;
	
	public GroupCourse(){
		
	}
	
	public GroupCourse(Integer groupCourseId){
		this.groupCourseId=groupCourseId;
	}

	public GroupCourse(GroupCoursePo groupCoursePo){
		groupCourseId = groupCoursePo.getGroupCourseId();
		groupId = groupCoursePo.getGroupId();
		courseId = groupCoursePo.getCourseId();
	}
	
	public GroupCoursePo toPo(){
		GroupCoursePo groupCoursePo = new GroupCoursePo();
		groupCoursePo.setGroupCourseId(groupCourseId);
		groupCoursePo.setGroupId(groupId);
		groupCoursePo.setCourseId(courseId);
		return groupCoursePo;
	}
}
