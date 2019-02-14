package com.jefflee.view;

import java.util.List;

public class DayView {
	private List<PeriodView> arrangedPeriodViewList;
	private List<PeriodView> highestPeriodViewList;

	public List<PeriodView> getArrangedPeriodViewList() {
		return arrangedPeriodViewList;
	}

	public void setArrangedPeriodViewList(List<PeriodView> arrangedPeriodViewList) {
		this.arrangedPeriodViewList = arrangedPeriodViewList;
	}

	public List<PeriodView> getHighestPeriodViewList() {
		return highestPeriodViewList;
	}

	public void setHighestPeriodViewList(List<PeriodView> highestPeriodViewList) {
		this.highestPeriodViewList = highestPeriodViewList;
	}

}
