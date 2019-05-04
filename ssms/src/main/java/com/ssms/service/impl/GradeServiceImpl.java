package com.ssms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ssms.dao.GradeMapper;
import com.ssms.model.Grade;
import com.ssms.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public List<Grade> list() {
        return gradeMapper.selectList(new EntityWrapper<Grade>().orderBy("name",true));
    }
}
