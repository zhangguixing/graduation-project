package com.ssms.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ssms.model.CollegeSubjectClass;

import java.util.List;

public interface CollegeSubjectClassMapper extends BaseMapper<CollegeSubjectClass> {

    List<CollegeSubjectClass> findByParentId(Integer parentId);

    List<CollegeSubjectClass> listCollegeAndSubject();

    String selectNameById(Integer collegeId);
}
