package com.jefflee.view;

import java.util.List;

import com.jefflee.entity.schedule.Arrangement;

public class PeriodView {
	private String periodViewId;
	private List<String> jumpViewIdList;
	private List<Arrangement> arrangementList;

	public String getPeriodViewId() {
		return periodViewId;
	}

	public void setPeriodViewId(String periodViewId) {
		this.periodViewId = periodViewId;
	}

	public List<String> getJumpViewIdList() {
		return jumpViewIdList;
	}

	public void setJumpViewIdList(List<String> jumpViewIdList) {
		this.jumpViewIdList = jumpViewIdList;
	}

	public List<Arrangement> getArrangementList() {
		return arrangementList;
	}

	public void setArrangementList(List<Arrangement> arrangementList) {
		this.arrangementList = arrangementList;
	}

}
