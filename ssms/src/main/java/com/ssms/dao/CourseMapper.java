package com.ssms.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ssms.model.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CourseMapper extends BaseMapper<Course> {

    List<Map<String,Object>> listByCollege(@Param("gradeId") Integer gradeId, @Param("collegeId") Integer collegeId, @Param("subjectId") Integer subjectId, @Param("classId") Integer classId, @Param("schoolYear") String schoolYear, @Param("semester") Integer semester);
}
