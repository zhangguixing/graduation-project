package com.ssms.service;

import com.ssms.model.CollegeSubjectClass;

import java.util.List;

public interface CollegeSubjectClassService{

    List<CollegeSubjectClass> getCollegeSubjectClassByParentId(Integer parentId);

    List<CollegeSubjectClass> listCollegeAndSubject();

    List<CollegeSubjectClass> list();

    boolean add(CollegeSubjectClass collegeSubjectClass);

    boolean update(CollegeSubjectClass collegeSubjectClass);

    boolean delete(Integer collegeId);
}
