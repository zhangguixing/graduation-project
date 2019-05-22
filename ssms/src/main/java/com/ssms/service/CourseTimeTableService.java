package com.ssms.service;

import com.ssms.model.CourseTimeTable;

import java.util.Map;

public interface CourseTimeTableService {

    boolean updateTimeTable(CourseTimeTable courseTimeTable);

    boolean addTimeTable(CourseTimeTable courseTimeTable);

    CourseTimeTable getCourseInfo(Map<String, Object> map);

    boolean deleteTimeTable(Integer id);
}
