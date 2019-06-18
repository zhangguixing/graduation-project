package com.ssms.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ssms.model.Score;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ScoreMapper extends BaseMapper<Score> {

    List<Map<String, Object>> listScore(@Param("gradeId") Integer gradeId, @Param("collegeId") Integer collegeId, @Param("subjectId") Integer subjectId, @Param("classId") Integer classId, @Param("schoolYear") String schoolYear, @Param("semester") Integer semester,@Param("courseName") String courseName, @Param("searchKey") String searchKey, @Param("searchValue") String searchValue);

    List<Map<String, Object>> getPersonScore(@Param("gradeId") Integer gradeId, @Param("collegeId") Integer collegeId, @Param("subjectId") Integer subjectId, @Param("classId") Integer classId, @Param("studentId") Integer studentId, @Param("schoolYear") String schoolYear, @Param("semester") Integer semester);

    List<Map<String, Object>> getChartsData(@Param("studentId") Integer studentId);

    List<Map<String, Object>> getStudentScore(Map<String, Object> collegeInfo);

    void deleteByCollegeInfo(@Param("gradeId") Integer gradeId, @Param("collegeId") Integer collegeId, @Param("subjectId") Integer subjectId, @Param("classId") Integer classId, @Param("schoolYear") String schoolYear, @Param("semester") Integer semester, @Param("studentId") Integer studentId);

    List<BigDecimal> listScoreInfo(@Param("gradeId") Integer gradeId, @Param("collegeId") Integer collegeId, @Param("subjectId") Integer subjectId, @Param("classId") Integer classId, @Param("schoolYear") String schoolYear, @Param("semester") Integer semester, @Param("studentId") Integer studentId);

    List<String> listCourseInfo(@Param("gradeId") Integer gradeId, @Param("collegeId") Integer collegeId, @Param("subjectId") Integer subjectId, @Param("classId") Integer classId, @Param("schoolYear") String schoolYear, @Param("semester") Integer semester);

    List<Map<String,Object>> getConditionPersonNum(@Param("gradeId") Integer gradeId, @Param("collegeId") Integer collegeId, @Param("subjectId") Integer subjectId, @Param("classId") Integer classId, @Param("schoolYear") String schoolYear, @Param("semester") Integer semester, @Param("courseName") String courseName);
}
