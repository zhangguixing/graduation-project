package com.ssms.service;

import com.github.pagehelper.PageInfo;
import com.ssms.model.Grade;

import java.util.List;

public interface GradeService{

    List<Grade> list();

    String findNameById(Integer gradeId);

    PageInfo<Grade> all(Integer pageNum, Integer pageSize);

    boolean delete(Integer id);

    boolean update(Grade grade);

    boolean add(Grade grade);
}
