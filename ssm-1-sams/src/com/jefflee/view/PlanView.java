package com.jefflee.view;

import com.jefflee.entity.schedule.Plan;

public class PlanView {
	private Plan plan;
	private String jumpViewId;
	private Integer arrangedNum;
	private Integer highestNum;

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Integer getArrangedNum() {
		return arrangedNum;
	}

	public void setArrangedNum(Integer arrangedNum) {
		this.arrangedNum = arrangedNum;
	}

	public Integer getHighestNum() {
		return highestNum;
	}

	public void setHighestNum(Integer highestNum) {
		this.highestNum = highestNum;
	}

	public String getJumpViewId() {
		return jumpViewId;
	}

	public void setJumpViewId(String jumpViewId) {
		this.jumpViewId = jumpViewId;
	}

}
