package com.jefflee.entity.shiro;

public class UserSearch {
	private String nickname;
	private String sex;
	private String status;
	private String createTimeStart;
	private String createTimeEnd;
	private String operation;
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateTimeStart() {
		return createTimeStart;
	}
	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}
	public String getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
}
