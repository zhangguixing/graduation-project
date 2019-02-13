
package com.jefflee.service.relation;

import java.util.List;

import com.jefflee.po.relation.GroupCoursePo;

public interface GroupCourseService {

	public Integer insert(GroupCoursePo groupCoursePo);
	
	public List<GroupCoursePo> selectById( Integer groupId);
	
	public void deleteById(Integer groupId);
	
}

