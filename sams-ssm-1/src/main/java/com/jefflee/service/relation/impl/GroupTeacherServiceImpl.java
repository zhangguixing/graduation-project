package com.jefflee.service.relation.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import com.jefflee.mapper.relation.GroupTeacherMapper;
import com.jefflee.po.relation.GroupTeacherPo;
import com.jefflee.service.relation.GroupTeacherService;


@Service("groupTeacherService")
public class GroupTeacherServiceImpl implements GroupTeacherService{
	@Resource(name = "groupTeacherMapper")
	private GroupTeacherMapper groupTeacherMapper;
	
	@Override
	public Integer insert(GroupTeacherPo groupTeacherPo) {
		if (groupTeacherMapper.insert(groupTeacherPo) == 1) {
			return groupTeacherPo.getGroupId();
		} else {
			return null;
		}
	}
	
	@Override
	public List<GroupTeacherPo> selectById(Integer groupId) {
 
         Example example =new Example(GroupTeacherPo.class);
         example.createCriteria().andEqualTo("groupId",groupId.toString());
          
        return  groupTeacherMapper.selectByExample(example);
		
		
	}

	@Override	
	public void deleteById(Integer groupId){
		Example example =new Example(GroupTeacherPo.class);
        example.createCriteria().andEqualTo("groupId",groupId.toString());
         
        groupTeacherMapper.deleteByExample(example);
	}
}


