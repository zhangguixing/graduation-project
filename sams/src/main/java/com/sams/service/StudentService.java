package com.sams.service;

import com.sams.dao.StudentDao;
import com.sams.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;

    public List<Student> findByGradeid(int gradeid) {
        Example example = new Example(Student.class);
        example.createCriteria().andEqualTo("gradeid",gradeid);
        return studentDao.selectByExample(example);
    }

    public List<Student> findByClazzid(int clazzid) {
        Example example = new Example(Student.class);
        example.createCriteria().andEqualTo("clazzid",clazzid);
        return studentDao.selectByExample(example);
    }
}
