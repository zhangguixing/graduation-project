package com.jefflee.mapper.shiro;

import com.jefflee.entity.shiro.TbAdmin;
import com.jefflee.entity.shiro.TbAdminExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbAdminMapper {
    int countByExample(TbAdminExample example);

    int deleteByExample(TbAdminExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbAdmin record);

    int insertSelective(TbAdmin record);

//    @Select("select * from tb_admin")
//    @Results({ @Result(id = true, column = "id", property = "id"),
//            @Result(column = "username", property = "username"),
//            @Result(column = "password", property = "password"),
//            @Result(column = "fullname", property = "fullname"),
//            @Result(column = "sex", property = "sex") })
    List<TbAdmin> selectByExample(TbAdminExample example);

    TbAdmin selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbAdmin record, @Param("example") TbAdminExample example);

    int updateByExample(@Param("record") TbAdmin record, @Param("example") TbAdminExample example);

    int updateByPrimaryKeySelective(TbAdmin record);

    int updateByPrimaryKey(TbAdmin record);
}