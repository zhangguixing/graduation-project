package com.jefflee.service.information;

import java.util.List;

import com.jefflee.entity.information.Teacher;
import com.jefflee.po.information.TeacherPo;

public interface TeacherService {

	Integer insert(TeacherPo teacherPo);

	List<TeacherPo> selectAll();

	TeacherPo selectById(Integer teacherId);

	Integer updateById(TeacherPo teacherPo);

	Integer deleteById(Integer teacherId);

	List<Teacher> selectTeacherList();

}
