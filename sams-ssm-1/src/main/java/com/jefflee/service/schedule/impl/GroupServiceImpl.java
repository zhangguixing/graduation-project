package com.jefflee.service.schedule.impl;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jefflee.entity.schedule.Group;
import com.jefflee.mapper.schedule.GroupMapper;
import com.jefflee.po.schedule.GroupPo;
import com.jefflee.service.schedule.GroupService;

@Service("groupService")
public class GroupServiceImpl implements GroupService {

	@Resource(name = "groupMapper")
	private GroupMapper groupMapper;

	@Override
	public Integer insert(GroupPo groupPo) {
		if (groupMapper.insert(groupPo) == 1) {
			return groupPo.getGroupId();
		} else {
			return null;
		}
	}

	@Override
	public List<GroupPo> selectAll() {
		return groupMapper.selectAll();
	}

	@Override
	public GroupPo selectById(Integer groupId) {
		return groupMapper.selectByPrimaryKey(groupId);
	}

	@Override
	public Integer updateById(GroupPo groupPo) {
		if (groupMapper.updateByPrimaryKey(groupPo) == 1) {
			return groupPo.getGroupId();
		} else {
			return null;
			
		}
	}

	@Override
	public Integer deleteById(Integer groupId) {
		if (groupMapper.deleteByPrimaryKey(groupId) == 1) {
			return groupId;
		} else {
			return null;
		}
	}

	@Override
	public String gnrGradeName(Group group) {
		StringBuilder name = new StringBuilder();
		switch (group.getLevel()) {
		case 0:
			name.append("初");
			break;
		case 1:
			name.append("高");
			break;
		default:
			break;
		}
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		if (month < 9) {
			year -= 1;
		}
		switch (year - group.getYear()) {
		case 0:
			name.append("一");
			break;
		case 1:
			name.append("二");
			break;
		case 2:
			name.append("三");
			break;
		default:
			name.insert(0, group.getYear() + "级");
			name.append("中");
			break;
		}
		return name.toString();
	}
}
