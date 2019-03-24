package com.sams.dao;

import com.sams.entity.Teacher;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author Guixing
 * @Date 2019/2/21 13:17
 * @Description
 */
public interface TeacherDao extends Mapper<Teacher> {
    @Delete("<script>" +
            "DELETE FROM clazz_course_teacher " +
            "<where> " +
            "<foreach cllection='array' item='id' open='teacherid in(' separator=',' close=')'>#{id}</foreach>" +
            "</where></script>")
    void deleteClazzCourseTeacherByTeacheridIn(String[] ids);

    @Insert("insert into teacher(number, name, sex, qq, photo) value(#{number},#{name},#{sex},#{qq},#{photo})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insertReturnKey(Teacher teacher);

    @Insert("INSERT INTO clazz_course_teacher(clazzid, gradeid, courseid, teacherid) value(#{gradeid},#{clazzid},#{courseid},#{teacherid})")
    void insertClazzCourseTeacher(@Param("gradeid") int gradeid,@Param("clazzid") int clazzid,@Param("courseid") int courseid,@Param("teacherid") int teacherid);

    @Delete("DELETE FROM clazz_course_teacher where teacherid=#{id}")
    void deleteClazzCourseTeacherByTeacherid(int id);

    @Update("UPDATE teacher SET name=#{name}, sex=#{sex}, phone=#{phone}, qq=#{qq} WHERE number=#{number}")
    void updateTeacherByNumber(Teacher teacher);
}
