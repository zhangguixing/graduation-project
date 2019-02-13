package com.jefflee.entity.relation;

import com.jefflee.po.relation.GroupTeacherPo;


public class GroupTeacher {
	public Integer groupTeacherId;
	public Integer groupId;
	public Integer teacherId;
	
    public GroupTeacher(){
		
	}
	
	public GroupTeacher(Integer groupTeacherId){
		this.groupTeacherId=groupTeacherId;
	}

	public GroupTeacher(GroupTeacherPo groupTeacherPo){
		groupTeacherId = groupTeacherPo.getGroupTeacherId();
		groupId = groupTeacherPo.getGroupId();
		teacherId = groupTeacherPo.getTeacherId();
	}
	
	public GroupTeacherPo toPo(){
		GroupTeacherPo groupTeacherPo = new GroupTeacherPo();
		groupTeacherPo.setGroupTeacherId(groupTeacherId);
		groupTeacherPo.setGroupId(groupId);
		groupTeacherPo.setTeacherId(teacherId);
		return groupTeacherPo;
	}

}
