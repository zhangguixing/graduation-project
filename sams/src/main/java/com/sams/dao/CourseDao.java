package com.sams.dao;

import com.sams.entity.Course;
import com.sams.entity.CourseItem;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author Guixing
 * @Date 2019/2/21 13:17
 * @Description
 */
public interface CourseDao extends Mapper<Course> {

    @Select("SELECT c.id id, c.name name FROM course c, grade_course gc WHERE c.id=gc.courseid AND gc.gradeid=#{value}")
    List<Course> columnList(int gradeid);

    @Select("SELECT c.* FROM grade_course gc,  coursec WHERE gc.gradeid=#{value} AND gc.courseid=c.id")
    List<Course> findByGradeid(int gradeid);
}
