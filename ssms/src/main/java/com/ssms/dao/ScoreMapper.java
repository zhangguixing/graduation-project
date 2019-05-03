package com.ssms.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ssms.model.Score;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ScoreMapper extends BaseMapper<Score> {

    List<Map<String, Object>> listScore(@Param("collegeId") Integer collegeId, @Param("subjectId") Integer subjectId, @Param("classId") Integer classId,@Param("schoolYear") Integer schoolYear,@Param("semester") Integer semester);

    List<Map<String, Object>> listDefaultScore(@Param("collegeId") Integer collegeId, @Param("subjectId") Integer subjectId, @Param("classId") Integer classId);
}
