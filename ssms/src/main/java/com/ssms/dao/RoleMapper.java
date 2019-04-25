package com.ssms.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ssms.model.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    List<Role> selectByUserId(Integer userId);
}
