package com.sams.dao;

import com.sams.entity.Exam;
import com.sams.entity.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author Guixing
 * @Date 2019/2/21 13:17
 * @Description
 */
public interface ExamDao extends Mapper<Exam> {

    @Select("SELECT * FROM exam WHERE (gradeid=#{gradeid} AND type=1) OR (clazzid=#{clazzid} AND type=2)")
    List<Exam> findByGradeidAndClazzid(@Param("gradeid") int gradeid,@Param("clazzid") int clazzid);
}
