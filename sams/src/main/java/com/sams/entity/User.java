package com.sams.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统用户类
 */
@Data
public class User implements Serializable {
	
	/**
	 * 管理员类型用户
	 */
	public static final int USER_ADMIN = 1;
	
	/**
	 * 学生类型用户
	 */
	public static final int USER_STUDENT = 2;
	
	/**
	 * 教师类型用户
	 */
	public static final int USER_TEACHER = 3;
	
	private int id; //ID
	
	private String account; //账户
	
	private String password = "111111"; //密码：默认'111111'
	
	private String name; //用户姓名
	
	private int type = USER_STUDENT; // 账户类型：默认2为学生；1为管理员，2为学生，3为教师

	private int state;

}
