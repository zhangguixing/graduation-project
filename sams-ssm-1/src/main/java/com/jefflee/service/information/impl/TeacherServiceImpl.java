package com.jefflee.service.information.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jefflee.entity.information.Teacher;
import com.jefflee.mapper.information.TeacherMapper;
import com.jefflee.po.information.TeacherPo;
import com.jefflee.service.information.TeacherService;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

	@Resource(name = "teacherMapper")
	private TeacherMapper teacherMapper;

	@Override
	public Integer insert(TeacherPo teacherPo) {
		if (teacherMapper.insert(teacherPo) == 1) {
			return teacherPo.getTeacherId();
		} else {
			return null;
		}
	}

	@Override
	public List<TeacherPo> selectAll() {
		return teacherMapper.selectAll();
	}

	@Override
	public TeacherPo selectById(Integer teacherId) {
		return teacherMapper.selectByPrimaryKey(teacherId);
	}

	@Override
	public Integer updateById(TeacherPo teacherPo) {
		if (teacherMapper.updateByPrimaryKey(teacherPo) == 1) {
			return teacherPo.getTeacherId();
		} else {
			return null;
		}
	}

	@Override
	public Integer deleteById(Integer teacherId) {
		if (teacherMapper.deleteByPrimaryKey(teacherId) == 1) {
			return teacherId;
		} else {
			return null;
		}
	}

	@Override
	public List<Teacher> selectTeacherList() {
		return teacherMapper.selectEntityList();
	}
}
