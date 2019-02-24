package com.sams.service;

import com.sams.dao.CourseDao;
import com.sams.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseDao courseDao;

    public List<Course> findByGradeid(int gradeid) {
        Example example = new Example(Course.class);
        example.createCriteria().andEqualTo("gradeid",gradeid);
        return courseDao.selectByExample(example);
    }
}
