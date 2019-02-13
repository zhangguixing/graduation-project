package com.jefflee.controller.schedule;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jefflee.po.schedule.AdjustmentPo;
import com.jefflee.service.schedule.AdjustmentService;
import com.jefflee.util.Response;

@RestController
@RequestMapping(value = "/adjustments")
public class AdjustmentController {

	@Resource(name = "adjustmentService")
	AdjustmentService adjustmentService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Response insert(@RequestBody AdjustmentPo adjustmentPo) {
		return new Response().success(adjustmentService.insertPo(adjustmentPo));
	}

	@RequestMapping(value = "/{adjustmentId}", method = RequestMethod.GET)
	public Response selectById(@PathVariable("adjustmentId") Integer adjustmentId) {
		return new Response().success(adjustmentService.selectPoById(adjustmentId));
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Response selectList() {
		return new Response().success(adjustmentService.selectPoList());
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public Response update(@RequestBody AdjustmentPo adjustmentPo) {
		if (adjustmentService.updatePoById(adjustmentPo) == 1) {
			return new Response().success();
		}
		return new Response().fail();
	}

	@RequestMapping(value = "/{adjustmentId}", method = RequestMethod.DELETE)
	public Response delete(@PathVariable("adjustmentId") Integer adjustmentId) {
		if (adjustmentService.deletePoById(adjustmentId) == 1) {
			return new Response().success();
		}
		return new Response().fail();
	}

	@RequestMapping(value = "", method = RequestMethod.GET, params = "scheduleId")
	public Response selectByScheduleId(@RequestParam("scheduleId") Integer scheduleId) {
		return new Response().success(adjustmentService.selectPoListByScheduleId(scheduleId));
	}

	@RequestMapping(value = "/latest", method = RequestMethod.GET)
	public Response selectLatest(@RequestParam("scheduleId") Integer scheduleId) {
		return new Response().success(adjustmentService.selectLatestId(scheduleId));
	}

}
