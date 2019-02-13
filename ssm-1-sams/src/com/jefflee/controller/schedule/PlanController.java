package com.jefflee.controller.schedule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jefflee.po.schedule.PlanPo;
import com.jefflee.service.schedule.PlanService;

@RestController
@RequestMapping(value = "/plan")
public class PlanController {

	@Resource(name = "planService")
	PlanService planService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Map<String, String> create(@RequestBody PlanPo planPo) {
		Map<String, String> result = new HashMap<String, String>();
		Integer planId = planService.insert(planPo);
		if (planId != null) {
			result.put("planId", planId.toString());
		}
		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<PlanPo> listAll() {
		return planService.selectAll();
	}

	@RequestMapping(value = "/find/{planId}", method = RequestMethod.GET)
	public PlanPo findById(@PathVariable("planId") Integer planId) {
		return planService.selectById(planId);
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public Map<String, String> modify(@RequestBody PlanPo planPo) {
		Map<String, String> result = new HashMap<String, String>();
		Integer planId = planService.updateById(planPo);
		if (planId != null) {
			result.put("planId", planId.toString());
		}
		return result;
	}

	@RequestMapping(value = "/delete/{planId}", method = RequestMethod.DELETE)
	public Map<String, String> delete(@PathVariable("planId") Integer planId) {
		Map<String, String> result = new HashMap<String, String>();
		planId = planService.deleteById(planId);
		if (planId != null) {
			result.put("planId", planId.toString());
		}
		return result;
	}

}
