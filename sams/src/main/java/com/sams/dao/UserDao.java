package com.sams.dao;

import com.sams.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface UserDao extends Mapper<User> {

    @Select("select * from user where account=#{account} and type=#{type} and state=0")
    User getPasswordByAccontAndType(@Param("account") String account,@Param("type") int type);

    @Update("update user set password=#{password} where account=#{account}")
    void updatePasswordByAccount(@Param("account") String account,@Param("password") String password);

    @Update("UPDATE user SET name=#{name} WHERE account=#{number}")
    void updateNameByAccount(@Param("name") String name, @Param("number") String number);
}
