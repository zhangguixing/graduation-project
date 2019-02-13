package com.jefflee.po.information;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Table(name = "info_period")
public class PeriodPo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer periodId;
	@OrderBy
	private String periodNo;
	private Integer orderOfDay;
	private Integer dayOfWeek;

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

	public Integer getOrderOfDay() {
		return orderOfDay;
	}

	public void setOrderOfDay(Integer orderOfDay) {
		this.orderOfDay = orderOfDay;
	}

	public Integer getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
}
