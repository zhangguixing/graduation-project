package com.sams.dao;

import com.sams.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface UserDao extends Mapper<User> {

    @Select("select password from user where account=#{account}")
    String getPasswordByAccount(@Param("account") String account);
}
