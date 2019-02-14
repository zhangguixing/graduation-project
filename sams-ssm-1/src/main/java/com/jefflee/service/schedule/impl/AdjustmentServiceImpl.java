package com.jefflee.service.schedule.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jefflee.entity.schedule.Adjustment;
import com.jefflee.mapper.schedule.AdjustmentMapper;
import com.jefflee.po.schedule.AdjustmentPo;
import com.jefflee.service.schedule.AdjustmentService;

@Service("adjustmentService")
public class AdjustmentServiceImpl implements AdjustmentService {

	@Resource(name = "adjustmentMapper")
	private AdjustmentMapper adjustmentMapper;

	@Override
	public Integer insertPo(AdjustmentPo adjustmentPo) {
		adjustmentMapper.insert(adjustmentPo);
		return adjustmentPo.getAdjustmentId();
	}

	@Override
	public AdjustmentPo selectPoById(Integer adjustmentId) {
		return adjustmentMapper.selectByPrimaryKey(adjustmentId);
	}

	@Override
	public List<AdjustmentPo> selectPoList() {
		return adjustmentMapper.selectAll();
	}

	@Override
	public int updatePoById(AdjustmentPo adjustmentPo) {
		return adjustmentMapper.updateByPrimaryKey(adjustmentPo);
	}

	@Override
	public int deletePoById(Integer adjustmentId) {
		return adjustmentMapper.deleteByPrimaryKey(adjustmentId);
	}

	@Override
	public List<AdjustmentPo> selectPoListByScheduleId(Integer scheduleId) {
		AdjustmentPo adjustmentPo = new AdjustmentPo();
		adjustmentPo.setScheduleId(scheduleId);
		return adjustmentMapper.select(adjustmentPo);
	}

	@Override
	public List<Adjustment> selectTempListByScheduleId(Integer scheduleId) {
		return adjustmentMapper.selectTempEntityListByScheduleId(scheduleId);
	}

	@Override
	public Adjustment selectAdjustmentById(Integer adjustmentId) {
		return adjustmentMapper.selectEntityById(adjustmentId);
	}

	@Override
	public Integer selectLatestId(Integer scheduleId) {
		// 待改进，可只查询最后一个
		List<Adjustment> adjustmentList = adjustmentMapper.selectTempEntityListByScheduleId(scheduleId);
		Adjustment adjustment = adjustmentList.get(adjustmentList.size() - 1);
		return adjustment.getAdjustmentId();
	}

}
