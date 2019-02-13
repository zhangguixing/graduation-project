package com.jefflee.service.shiro;


import com.jefflee.entity.shiro.TbAdmin;

import java.util.List;

public interface MainService {

	public List<TbAdmin> selAdminList();

	public List<TbAdmin> selStudentTotal();

	public List<TbAdmin> selTeacherTotal();

	public int selStudentCountByGender(int i);
}
