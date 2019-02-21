package com.sams.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    @Select("select password from user where account=#{account}")
    String getPasswordByAccount(@Param("account") String account);
}
