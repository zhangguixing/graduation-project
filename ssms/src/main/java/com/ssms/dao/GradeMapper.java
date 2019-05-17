package com.ssms.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ssms.model.Grade;

public interface GradeMapper extends BaseMapper<Grade> {

    String selectNameById(Integer gradeId);
}
