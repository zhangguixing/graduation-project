package com.sams.dao;

import com.sams.entity.Clazz;
import com.sams.entity.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author Guixing
 * @Date 2019/2/21 13:17
 * @Description
 */
public interface ClazzDao extends Mapper<Clazz> {

    @Select("SELECT c.id cid, c.name cname, g.id gid, g.name gname FROM clazz c, grade g WHERE c.gradeid = g.id " +
            "<if test='gradeid != null'> and gradeid=#{gradeid}</if>")
    List<Map<String,Object>> clazzDetailList(Integer gradeid);

    @Delete("DELETE FROM clazz_course_teacher WHERE clazzid=#{clazzid}")
    void deleteClazzCourseTeacherByClazzid(Integer clazzid);
}
