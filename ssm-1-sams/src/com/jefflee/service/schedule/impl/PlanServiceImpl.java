package com.jefflee.service.schedule.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jefflee.entity.schedule.Plan;
import com.jefflee.mapper.schedule.PlanMapper;
import com.jefflee.po.schedule.PlanPo;
import com.jefflee.service.schedule.PlanService;

@Service("planService")
public class PlanServiceImpl implements PlanService {

	@Resource(name = "planMapper")
	private PlanMapper planMapper;

	@Override
	public Integer insert(PlanPo planPo) {
		if (planMapper.insert(planPo) == 1) {
			return planPo.getPlanId();
		} else {
			return null;
		}
	}

	@Override
	public List<PlanPo> selectAll() {
		return planMapper.selectAll();
	}

	@Override
	public PlanPo selectById(Integer planId) {
		return planMapper.selectByPrimaryKey(planId);
	}

	@Override
	public Integer updateById(PlanPo planPo) {
		if (planMapper.updateByPrimaryKey(planPo) == 1) {
			return planPo.getPlanId();
		} else {
			return null;
		}
	}

	@Override
	public Integer deleteById(Integer planId) {
		if (planMapper.deleteByPrimaryKey(planId) == 1) {
			return planId;
		} else {
			return null;
		}
	}

	// TODO
	@Override
	public List<PlanPo> selectByScheduleId(Integer scheduleId) {
		PlanPo selectPlanPo = new PlanPo();
		selectPlanPo.setScheduleId(scheduleId);
		return planMapper.select(selectPlanPo);
	}

	@Override
	public List<PlanPo> selectByTclassId(List<PlanPo> planPoList, Integer tclassId) {
		List<PlanPo> selectedPlanPoList = new ArrayList<PlanPo>();
		for (PlanPo planPo : planPoList) {
			if (planPo.getTclassId() == tclassId) {
				selectedPlanPoList.add(planPo);
			}
		}
		return selectedPlanPoList;
	}

	@Override
	public List<PlanPo> selectByCourseId(List<PlanPo> planPoList, Integer courseId) {
		List<PlanPo> selectedPlanPoList = new ArrayList<PlanPo>();
		for (PlanPo planPo : planPoList) {
			if (planPo.getCourseId() == courseId) {
				selectedPlanPoList.add(planPo);
			}
		}
		return selectedPlanPoList;
	}

	@Override
	public List<Plan> selectPlanListByScheduleId(Integer scheduleId) {
		return planMapper.selectEntityListByScheduleId(scheduleId);
	}

}
