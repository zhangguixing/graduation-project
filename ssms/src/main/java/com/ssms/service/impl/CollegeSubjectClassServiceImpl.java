package com.ssms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ssms.dao.CollegeSubjectClassMapper;
import com.ssms.exception.BusinessException;
import com.ssms.model.CollegeSubjectClass;
import com.ssms.service.CollegeSubjectClassService;

@Service
public class CollegeSubjectClassServiceImpl implements CollegeSubjectClassService {

    @Autowired
    private CollegeSubjectClassMapper collegeSubjectClassMapper;

    @Override
    public List<CollegeSubjectClass> getCollegeSubjectClassByParentId(Integer parentId) {
        return collegeSubjectClassMapper.findByParentId(parentId);
    }

    @Override
    public List<CollegeSubjectClass> listCollegeAndSubject() {
        return collegeSubjectClassMapper.listCollegeAndSubject();
    }

    @Override
    public List<CollegeSubjectClass> list() {
        return collegeSubjectClassMapper.selectList(new EntityWrapper<CollegeSubjectClass>().orderBy("order_number", true));
    }

    @Override
    public boolean add(CollegeSubjectClass collegeSubjectClass) {
        collegeSubjectClass.setCreateTime(new Date());
        return collegeSubjectClassMapper.insert(collegeSubjectClass) > 0;
    }

    @Override
    public boolean update(CollegeSubjectClass collegeSubjectClass) {
        return collegeSubjectClassMapper.updateById(collegeSubjectClass) > 0;
    }

    @Override
    public boolean delete(Integer collegeId) {
        List<CollegeSubjectClass> childs = collegeSubjectClassMapper.selectList(new EntityWrapper<CollegeSubjectClass>().eq("parent_id", collegeId));
        if (childs != null && childs.size() > 0) {
            throw new BusinessException("请先删除子节点");
        }
        return true;
    }
}
