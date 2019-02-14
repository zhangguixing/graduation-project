package com.jefflee.service.relation;

import java.util.List;

import com.jefflee.po.relation.GroupTeacherPo;

public interface GroupTeacherService {

	public Integer insert(GroupTeacherPo groupTeacherPo);
	
	public List<GroupTeacherPo> selectById( Integer groupId);
	
	public void deleteById(Integer groupId);
	
}

