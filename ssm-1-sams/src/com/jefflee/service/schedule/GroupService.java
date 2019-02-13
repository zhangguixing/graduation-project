package com.jefflee.service.schedule;

import java.util.List;

import com.jefflee.entity.schedule.Group;
import com.jefflee.po.schedule.GroupPo;

public interface GroupService {

	public Integer insert(GroupPo groupPo);

	public List<GroupPo> selectAll();

	public GroupPo selectById(Integer groupId);

	public Integer updateById(GroupPo groupPo);

	public Integer deleteById(Integer groupId);

	public String gnrGradeName(Group group);
}
