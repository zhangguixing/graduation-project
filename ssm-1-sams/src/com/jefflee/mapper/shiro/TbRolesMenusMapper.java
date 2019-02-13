package com.jefflee.mapper.shiro;

import com.jefflee.entity.shiro.TbRolesMenusExample;
import com.jefflee.entity.shiro.TbRolesMenusKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbRolesMenusMapper {
    int countByExample(TbRolesMenusExample example);

    int deleteByExample(TbRolesMenusExample example);

    int deleteByPrimaryKey(TbRolesMenusKey key);

    int insert(TbRolesMenusKey record);

    int insertSelective(TbRolesMenusKey record);

    List<TbRolesMenusKey> selectByExample(TbRolesMenusExample example);

    int updateByExampleSelective(@Param("record") TbRolesMenusKey record, @Param("example") TbRolesMenusExample example);

    int updateByExample(@Param("record") TbRolesMenusKey record, @Param("example") TbRolesMenusExample example);
}