package com.jefflee.service.shiro.impl;

import com.jefflee.entity.shiro.TbAdmin;
import com.jefflee.entity.shiro.TbAdminExample;
import com.jefflee.mapper.shiro.MainMapper;
import com.jefflee.mapper.shiro.TbAdminMapper;
import com.jefflee.service.shiro.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainServiceImpl implements MainService {
	
	@Autowired
	private TbAdminMapper tbAdminMapper;
	
	@Autowired
	private MainMapper mainMapper;

	@Override
	public List<TbAdmin> selAdminList() {
		TbAdminExample example=new TbAdminExample();
		return tbAdminMapper.selectByExample(example);
	}
	
	@Override
	public List<TbAdmin> selStudentTotal() {
		return mainMapper.selStudentTotal();
	}

	@Override
	public List<TbAdmin> selTeacherTotal() {
		return mainMapper.selTeacherTotal();
	}

	@Override
	public int selStudentCountByGender(int i) {
		TbAdminExample example=new TbAdminExample();
		TbAdminExample.Criteria criteria = example.createCriteria();
		criteria.andSexEqualTo(i+"");
		criteria.andRoleIdEqualTo((long) 5);
		List<TbAdmin> list = tbAdminMapper.selectByExample(example);
		return list.size();
	}

}
