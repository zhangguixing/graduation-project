package com.ssms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssms.dao.GradeMapper;
import com.ssms.model.Grade;
import com.ssms.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public List<Grade> list() {
        return gradeMapper.selectList(new EntityWrapper<Grade>().setSqlSelect("id,name").eq("status",1).orderBy("name",true).last("limit 5"));
    }

    @Override
    public String findNameById(Integer gradeId) {
        Grade grade = gradeMapper.selectById(gradeId);
        if(grade != null){
            return grade.getName();
        }
        return null;
    }

    @Override
    public PageInfo<Grade> all(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return PageInfo.of(gradeMapper.selectList(new EntityWrapper<Grade>().eq("status",1).orderBy("name",false)));
    }

    @Override
    public boolean delete(Integer id) {
        Grade grade = new Grade();
        grade.setId(id);
        grade.setStatus(0);
        return gradeMapper.updateById(grade)>0;
    }

    @Override
    public boolean update(Grade grade) {
        return gradeMapper.updateById(grade)>0;
    }

    @Override
    public boolean add(Grade grade) {
        grade.setCreateTime(new Date());
        return gradeMapper.insert(grade)>0;
    }
}
