package com.ssms.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ssms.dao.CourseMapper;
import com.ssms.model.Course;
import com.ssms.service.CourseService;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

}
