package com.ssms.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ssms.model.Score;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ScoreMapper extends BaseMapper<Score> {

    List<Map<String, Object>> listScore(@Param("gradeId") Integer gradeId,@Param("collegeId") Integer collegeId, @Param("subjectId") Integer subjectId, @Param("classId") Integer classId, @Param("schoolYear") String schoolYear,@Param("semester") Integer semester, @Param("searchKey") String searchKey, @Param("searchValue") String searchValue);

    List<Map<String, Object>> getPersonScore(@Param("gradeId") Integer gradeId,@Param("collegeId") Integer collegeId, @Param("subjectId") Integer subjectId, @Param("classId") Integer classId, @Param("studentId") Integer studentId, @Param("schoolYear") String schoolYear, @Param("semester") Integer semester);
}
