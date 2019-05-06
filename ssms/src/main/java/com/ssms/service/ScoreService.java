package com.ssms.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface ScoreService {

    PageInfo<Map<String,Object>> listScore(Integer pageNum, Integer pageSize, Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester, String searchKey, String searchValue);

    List<Map<String,Object>> getPersonScore(Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, Integer studentId, String schoolYear, Integer semester);
}
