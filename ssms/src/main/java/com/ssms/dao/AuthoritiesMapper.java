package com.ssms.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ssms.model.Authorities;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthoritiesMapper extends BaseMapper<Authorities> {

    List<Authorities> listByUserId(Integer userId);

    List<Authorities> listByRoleIds(@Param("roleIds") List<Integer> roleIds);

    List<Authorities> listByRoleId(Integer roleId);
}
