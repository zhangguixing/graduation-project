package com.jefflee.service.schedule;

import java.util.List;

import com.jefflee.entity.schedule.Plan;
import com.jefflee.po.schedule.PlanPo;

public interface PlanService {

	public Integer insert(PlanPo planPo);

	public List<PlanPo> selectAll();

	public PlanPo selectById(Integer planId);

	public Integer updateById(PlanPo planPo);

	public Integer deleteById(Integer planId);

	public List<PlanPo> selectByScheduleId(Integer scheduleId);

	public List<PlanPo> selectByTclassId(List<PlanPo> planPoList, Integer tclassId);

	public List<PlanPo> selectByCourseId(List<PlanPo> planPoList, Integer courseId);

	public List<Plan> selectPlanListByScheduleId(Integer scheduleId);

}
