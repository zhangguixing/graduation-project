package com.jefflee.mapper.shiro;

import com.jefflee.entity.shiro.TbLog;
import com.jefflee.entity.shiro.TbLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbLogMapper {
    int countByExample(TbLogExample example);

    int deleteByExample(TbLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbLog record);

    int insertSelective(TbLog record);

    List<TbLog> selectByExample(TbLogExample example);

    TbLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbLog record, @Param("example") TbLogExample example);

    int updateByExample(@Param("record") TbLog record, @Param("example") TbLogExample example);

    int updateByPrimaryKeySelective(TbLog record);

    int updateByPrimaryKey(TbLog record);
}