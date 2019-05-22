package com.ssms.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ssms.model.CourseTimeTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CourseTimeTableMapper extends BaseMapper<CourseTimeTable> {

    List<Map<String,Object>> listTimeTable(@Param("gradeId") Integer gradeId, @Param("collegeId") Integer collegeId, @Param("subjectId") Integer subjectId, @Param("classId") Integer classId, @Param("schoolYear") String schoolYear, @Param("semester") Integer semester,@Param("weekNum") Integer weekNum);

    CourseTimeTable getCourseInfo(Map<String, Object> map);
}
