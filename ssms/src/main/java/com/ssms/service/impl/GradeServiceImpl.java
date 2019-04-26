package com.ssms.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ssms.dao.GradeMapper;
import com.ssms.model.Grade;
import com.ssms.service.GradeService;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {

}
