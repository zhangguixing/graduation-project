package com.jefflee.mapper.shiro;

import com.jefflee.entity.shiro.TbAdmin;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface MainMapper {
	@Select("select * from tb_admin where role_id=5;")
	List<TbAdmin> selStudentTotal();

	@Select("SELECT * FROM tb_admin WHERE role_id=3;")
	List<TbAdmin> selTeacherTotal();
}
