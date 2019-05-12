package com.ssms.service;

import java.util.Map;

public interface CourseService{
    Map<String,Object> listByCollege(Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester);
}
