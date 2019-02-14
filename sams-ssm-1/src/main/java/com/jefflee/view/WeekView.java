package com.jefflee.view;

import java.util.List;
import java.util.Map;

public class WeekView {
	private TitleView titleView;
	private List<DayView> dayViewList;
	private Map<String, PlanView> planViewMap;

	public List<DayView> getDayViewList() {
		return dayViewList;
	}

	public void setDayViewList(List<DayView> dayViewList) {
		this.dayViewList = dayViewList;
	}

	public TitleView getTitleView() {
		return titleView;
	}

	public void setTitleView(TitleView titleView) {
		this.titleView = titleView;
	}

	public Map<String, PlanView> getPlanViewMap() {
		return planViewMap;
	}

	public void setPlanViewMap(Map<String, PlanView> planViewMap) {
		this.planViewMap = planViewMap;
	}

}
