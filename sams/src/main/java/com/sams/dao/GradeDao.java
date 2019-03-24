package com.sams.dao;

import com.sams.entity.Grade;
import com.sams.entity.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author Guixing
 * @Date 2019/2/21 13:17
 * @Description
 */
public interface GradeDao extends Mapper<Grade> {

    @Delete("DELETE FROM clazz_course_teacher WHERE gradeid=#{gradeid}")
    void deleteClassCourseTeacherByGradeid(Integer gradeid);

    @Delete("DELETE FROM grade_course WHERE gradeid=#{gradeid}")
    void deleteGradeCourseByGradeid(Integer gradeid);

    @Insert("insert into grade(name) values(#{name})")
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    Integer insertGradeReturnId(String name);

    @Insert("<script>" +
            "INSERT INTO grade_course(gradeid, courseid) values " +
            "<foreach cellection='clazzids' item='clazzid' open='(' separator=',' close=')'>#{gradeid},#{clazzid}</foreach>" +
            "</script>")
    void insertGradeCourse(@Param("gradeid") Integer gradeid,@Param("clazzids") Integer[] clazzid);

}
