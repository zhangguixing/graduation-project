package com.ssms.service;

import com.github.pagehelper.PageInfo;
import com.ssms.model.Course;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CourseService {
    Map<String, Object> listByCollege(Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester, Integer studentId);

    List<List<String>> listTimeTable(Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester, Integer weekNum);

    List<Map<String, Object>> listCourseIdAndName(Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester);

    List<Map<String, Object>> listCourseName(Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester);

    PageInfo<Map<String, Object>> all(Integer pageNum, Integer pageSize, Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester, String searchKey, String searchValue);

    boolean add(Course course);

    boolean update(Course course);

    boolean delete(Integer id);

    void addCourses(MultipartFile file) throws IOException;

    List<Map<String, String>> listCourseNameAAndB(Integer gradeIdA, Integer collegeIdA, Integer subjectIdA, Integer classIdA, Integer gradeIdB, Integer collegeIdB, Integer subjectIdB, Integer classIdB);
}
