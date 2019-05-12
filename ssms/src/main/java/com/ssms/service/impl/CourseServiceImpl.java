package com.ssms.service.impl;

import com.ssms.dao.CourseMapper;
import com.ssms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Map<String, Object> listByCollege(Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester) {
        List<Map<String,Object>> scoreList = courseMapper.listByCollege(gradeId,collegeId,subjectId,classId,schoolYear,semester);
        Map<String,Object> result = new HashMap<>();
        result.put("scoreList",scoreList);
        return result;
    }
}
