package com.ssms.service;

import com.github.pagehelper.PageInfo;
import com.ssms.model.Course;

import java.util.List;
import java.util.Map;

public interface CourseService {
    Map<String, Object> listByCollege(Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester);

    List<List<String>> listTimeTable(Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester, Integer weekNum);

    List<Map<String, Object>> listCourseIdAndName(Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester);

    PageInfo<Map<String, Object>> all(Integer pageNum, Integer pageSize, Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester, String searchKey, String searchValue);

    boolean add(Course course);

    boolean update(Course course);

    boolean delete(Integer id);
}
