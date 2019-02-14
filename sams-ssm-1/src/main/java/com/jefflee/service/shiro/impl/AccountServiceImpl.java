//package com.jefflee.service.shiro.impl;
//
//import com.jefflee.entity.shiro.TbUsers;
//import com.jefflee.entity.shiro.TbUsersExample;
//import com.jefflee.mapper.shiro.TbUsersMapper;
//import com.jefflee.service.shiro.AccountService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class AccountServiceImpl implements AccountService {
//
//	@Autowired
//	private TbUsersMapper tbUsersMapper;
//
//	@Override
//	public TbUsers selUserByCodeAndStatus(String eCode, String status) {
//		TbUsersExample example=new TbUsersExample();
//		TbUsersExample.Criteria criteria = example.createCriteria();
//		criteria.andECodeEqualTo(eCode);
//		criteria.andStatusEqualTo(status);
//		List<TbUsers> users = tbUsersMapper.selectByExample(example);
//		if(users!=null&&users.size()>0){
//			return users.get(0);
//		}
//		return null;
//	}
//
//	@Override
//	public void updUserStatus(TbUsers user) {
//		tbUsersMapper.updateByPrimaryKey(user);
//	}
//
//}
