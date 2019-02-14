package com.jefflee.service.relation.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.jefflee.mapper.relation.GroupCourseMapper;
import com.jefflee.po.relation.GroupCoursePo;
import com.jefflee.service.relation.GroupCourseService;


@Service("groupCourseService")
public class GroupCourseServiceImpl implements GroupCourseService{
	@Resource(name = "groupCourseMapper")
	private GroupCourseMapper groupCourseMapper;
	
	@Override
	public Integer insert(GroupCoursePo groupCoursePo) {
		if (groupCourseMapper.insert(groupCoursePo) == 1) {
			return groupCoursePo.getGroupId();
		} else {
			return null;
		}
	}
	
	@Override
	public List<GroupCoursePo> selectById(Integer groupId) {
 
         Example example =new Example(GroupCoursePo.class);
         example.createCriteria().andEqualTo("groupId",groupId.toString());
          
        return  groupCourseMapper.selectByExample(example);
		
		
	}

	@Override	
	public void deleteById(Integer groupId){
		Example example =new Example(GroupCoursePo.class);
        example.createCriteria().andEqualTo("groupId",groupId.toString());
         
        groupCourseMapper.deleteByExample(example);
	}
}


