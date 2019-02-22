package com.sams.dao;

import com.sams.entity.SystemInfo;
import com.sams.entity.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author Guixing
 * @Date 2019/2/21 13:17
 * @Description
 */
public interface SystemInfoDao extends Mapper<SystemInfo> {

    @Update("update system set ${name}=#{value}")
    void updateSystemInfo(@Param("name") String name,@Param("value") String value);
}
