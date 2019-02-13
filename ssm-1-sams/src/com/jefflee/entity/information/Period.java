package com.jefflee.entity.information;

import com.jefflee.po.information.PeriodPo;

public class Period {
	private Integer periodId;
	private String periodNo;
	private Integer dayOfWeek;
	private Integer orderOfDay;

	public Period() {
	}

	public Period(Integer periodId) {
		this.periodId = periodId;
	}

	public Period(PeriodPo periodPo) {
		periodId = periodPo.getPeriodId();
		periodNo = periodPo.getPeriodNo();
		dayOfWeek = periodPo.getDayOfWeek();
		orderOfDay = periodPo.getOrderOfDay();

	}

	public PeriodPo toPo() {
		PeriodPo periodPo = new PeriodPo();
		periodPo.setPeriodId(periodId);
		periodPo.setPeriodNo(periodNo);
		periodPo.setDayOfWeek(dayOfWeek);
		periodPo.setOrderOfDay(orderOfDay);
		return periodPo;
	}

	public Integer getPeriodId() {
		return periodId;
	}

	public void setPeriodId(Integer periodId) {
		this.periodId = periodId;
	}

	public String getPeriodNo() {
		return periodNo;
	}

	public void setPeriodNo(String periodNo) {
		this.periodNo = periodNo;
	}

	public Integer getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Integer getOrderOfDay() {
		return orderOfDay;
	}

	public void setOrderOfDay(Integer orderOfDay) {
		this.orderOfDay = orderOfDay;
	}
}
