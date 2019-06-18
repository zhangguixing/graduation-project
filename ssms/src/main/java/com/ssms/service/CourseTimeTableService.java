package com.ssms.service;

import com.ssms.model.CourseTimeTable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CourseTimeTableService {

    boolean updateTimeTable(CourseTimeTable courseTimeTable);

    boolean addTimeTable(CourseTimeTable courseTimeTable);

    CourseTimeTable getCourseInfo(Map<String, Object> map);

    boolean deleteTimeTable(Integer id);

    List<List<String>> getMyTimeTable(String schoolYear, Integer semester, Integer teacherId);

    Boolean isConflict(CourseTimeTable courseTimeTable);

    void addTimeTables(MultipartFile file) throws IOException;
}
