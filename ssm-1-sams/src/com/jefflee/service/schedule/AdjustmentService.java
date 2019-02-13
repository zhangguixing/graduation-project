package com.jefflee.service.schedule;

import java.util.List;

import com.jefflee.entity.schedule.Adjustment;
import com.jefflee.po.schedule.AdjustmentPo;

public interface AdjustmentService {

	Integer insertPo(AdjustmentPo adjustmentPo);

	AdjustmentPo selectPoById(Integer adjustmentId);

	List<AdjustmentPo> selectPoList();

	int updatePoById(AdjustmentPo adjustmentPo);

	int deletePoById(Integer adjustmentId);

	List<AdjustmentPo> selectPoListByScheduleId(Integer scheduleId);

	List<Adjustment> selectTempListByScheduleId(Integer scheduleId);

	Adjustment selectAdjustmentById(Integer adjustmentId);

	Integer selectLatestId(Integer scheduleId);

}
