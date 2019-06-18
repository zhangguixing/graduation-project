package com.ssms.service;

import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ScoreService {

    PageInfo<Map<String, Object>> listScore(Integer pageNum, Integer pageSize, Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester,String courseName, String searchKey, String searchValue);

    List<Map<String, Object>> getPersonScore(Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, Integer studentId, String schoolYear, Integer semester);

    Map<String, Object> getChartsData(Integer studentId);

    boolean delete(Map<String, Object> map);

    Map<String, Object> getCollegeInfo(Integer studentId, Integer collegeId, Integer subjectId, Integer classId, Integer gradeId, String schoolYear, Integer semester);

    boolean saveOrUpdate(Map<String, Object> map);

    Map<String, Object> export(List<Map<String, Object>> list);

    void addScores(MultipartFile file) throws IOException, Exception;

    Map<String, Object> getCollegeNameAndScore(Integer studentId, Integer collegeId, Integer subjectId, Integer classId, Integer gradeId, String schoolYear, Integer semester);

    Map<String, Object> searchPersonNum(Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester, String courseName);
}
