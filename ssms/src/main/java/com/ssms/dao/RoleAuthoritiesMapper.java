package com.ssms.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ssms.model.RoleAuthorities;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleAuthoritiesMapper extends BaseMapper<RoleAuthorities> {

    int insertRoleAuths(@Param("roleId") Integer roleId, @Param("authIds") List<Integer> authIds);

}
