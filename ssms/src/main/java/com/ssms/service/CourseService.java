package com.ssms.service;

import java.util.List;
import java.util.Map;

public interface CourseService{
    Map<String,Object> listByCollege(Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester);

    List<List<String>> listTimeTable(Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester, Integer weekNum);

    List<Map<String,Object>> listCourseIdAndName(Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester);
}
