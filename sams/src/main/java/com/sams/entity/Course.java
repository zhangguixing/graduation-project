package com.sams.entity;

import java.io.Serializable;

/**
 * 课程类
 *
 */
public class Course implements Serializable {
	
	private int id; //ID
	
	private String name; //名称

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
