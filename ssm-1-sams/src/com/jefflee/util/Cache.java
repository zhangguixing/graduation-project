package com.jefflee.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.jefflee.entity.schedule.Arrangement;
import com.jefflee.entity.schedule.Plan;
import com.jefflee.mapper.schedule.ArrangementMapper;
import com.jefflee.mapper.schedule.PlanMapper;
import com.jefflee.service.information.TclassService;
import com.jefflee.service.schedule.AdjustmentService;

@Component("cache")
public class Cache {

	private List<Arrangement> scheduleArrangementList;
	private List<Arrangement> semesterArrangementList;
	private List<Plan> schedulePlanList;
	private Map<String, Map<String, Integer>> backgroundMap;

	@Resource(name = "arrangementMapper")
	private ArrangementMapper arrangementMapper;
	@Resource(name = "planMapper")
	private PlanMapper planMapper;

	@Resource(name = "tclassService")
	private TclassService tclassService;

	@Resource(name = "adjustmentService")
	private AdjustmentService adjustmentService;

	private Cache() {
	}

	public List<Arrangement> getScheduleArrangementList() {
		return scheduleArrangementList;
	}

	public List<Arrangement> getSemesterArrangementList() {
		return semesterArrangementList;
	}

	public List<Plan> getSchedulePlanList() {
		return schedulePlanList;
	}

	public Map<String, Map<String, Integer>> getBackgroundMap() {
		return backgroundMap;
	}

	public void initial(Integer scheduleId) {
		scheduleArrangementList = arrangementMapper.selectEntityListByScheduleId(scheduleId);

		semesterArrangementList = scheduleArrangementList;
		List<Integer> semesterScheduleIdList = getSemesterScheduleIdList(scheduleId);
		for (Integer semesterScheduleId : semesterScheduleIdList) {
			semesterArrangementList.addAll(arrangementMapper.selectEntityListByScheduleId(semesterScheduleId));
		}
		for (Arrangement arrangement : semesterArrangementList) {
			if (arrangement.getTclass().getTclassId() != null) {
				arrangement.getTclass().setName(tclassService.gnrName(arrangement.getTclass()));
				arrangement.getTclass().setShortName(tclassService.gnrShortName(arrangement.getTclass()));
			}
		}

		schedulePlanList = planMapper.selectEntityListByScheduleId(scheduleId);
		for (Plan plan : schedulePlanList) {
			if (plan.getTclass().getTclassId() != null) {
				plan.getTclass().setName(tclassService.gnrName(plan.getTclass()));
				plan.getTclass().setShortName(tclassService.gnrShortName(plan.getTclass()));
			}
		}

		backgroundMap = new HashMap<String, Map<String, Integer>>();
		backgroundMap.put("conflicting", new HashMap<String, Integer>());
		backgroundMap.put("adjusted", new HashMap<String, Integer>());
		backgroundMap.put("selected", new HashMap<String, Integer>());
	}

	// TODO 待补充
	private List<Integer> getSemesterScheduleIdList(Integer scheduleId) {
		return new ArrayList<Integer>();
	}

}
