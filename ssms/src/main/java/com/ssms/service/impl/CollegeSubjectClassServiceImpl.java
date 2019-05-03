package com.ssms.service.impl;

import com.ssms.dao.CollegeSubjectClassMapper;
import com.ssms.model.CollegeSubjectClass;
import com.ssms.service.CollegeSubjectClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeSubjectClassServiceImpl implements CollegeSubjectClassService {

    @Autowired
    private CollegeSubjectClassMapper collegeSubjectClassMapper;

    @Override
    public List<CollegeSubjectClass> getCollegeSubjectClassByParentId(Integer parentId) {
        return collegeSubjectClassMapper.findByParentId(parentId);
    }
}
