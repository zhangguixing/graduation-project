package com.jefflee.service.information;

import java.util.List;

import com.jefflee.entity.information.Period;
import com.jefflee.po.information.PeriodPo;

public interface PeriodService {

	Integer insert(PeriodPo periodPo);

	List<PeriodPo> selectAll();

	PeriodPo selectById(Integer periodId);

	Integer updateById(PeriodPo periodPo);

	Integer deleteById(Integer periodId);

	List<PeriodPo> select(PeriodPo queryPeriodPo);

	Period selectPeriodByOrder(Integer dayOfWeek, Integer orderOfDay);

	List<Period> selectPeriodListByScope(Integer daysPerWeek, Integer periodsPerDay);

}
