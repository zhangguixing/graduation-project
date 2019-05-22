package com.ssms.service.impl;

import com.ssms.dao.CourseTimeTableMapper;
import com.ssms.model.CourseTimeTable;
import com.ssms.service.CourseTimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class CourseTimeTableServiceImpl implements CourseTimeTableService {

    @Autowired
    private CourseTimeTableMapper courseTimeTableMapper;

    @Override
    public boolean updateTimeTable(CourseTimeTable courseTimeTable) {
        return courseTimeTableMapper.updateById(courseTimeTable) > 0;
    }

    @Override
    public boolean addTimeTable(CourseTimeTable courseTimeTable) {
        courseTimeTable.setCreateTime(new Date());
        return courseTimeTableMapper.insert(courseTimeTable)>0;
    }

    @Override
    public CourseTimeTable getCourseInfo(Map<String, Object> map) {
        return courseTimeTableMapper.getCourseInfo(map);
    }

    @Override
    public boolean deleteTimeTable(Integer id) {
        return courseTimeTableMapper.deleteById(id)>0;
    }
}
