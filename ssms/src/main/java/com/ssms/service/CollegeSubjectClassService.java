package com.ssms.service;

import com.ssms.model.CollegeSubjectClass;

import java.util.List;

public interface CollegeSubjectClassService{

    List<CollegeSubjectClass> getCollegeSubjectClassByParentId(Integer parentId);
}
