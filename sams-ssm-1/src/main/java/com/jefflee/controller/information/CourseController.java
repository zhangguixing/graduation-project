package com.jefflee.controller.information;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jefflee.po.information.CoursePo;
import com.jefflee.service.information.CourseService;

@RestController
@RequestMapping(value = "/course")
public class CourseController {

	@Resource(name = "courseService")
	CourseService courseService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Map<String, String> create(@RequestBody CoursePo coursePo) {
		Map<String, String> result = new HashMap<String, String>();
		Integer courseId = courseService.insert(coursePo);
		if (courseId != null) {
			result.put("courseId", courseId.toString());
		}
		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<CoursePo> listAll() {
		return courseService.selectAll();
	}

	@RequestMapping(value = "/find/{courseId}", method = RequestMethod.GET)
	public CoursePo findById(@PathVariable("courseId") Integer courseId) {
		return courseService.selectById(courseId);
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public Map<String, String> modify(@RequestBody CoursePo coursePo) {
		Map<String, String> result = new HashMap<String, String>();
		Integer courseId = courseService.updateById(coursePo);
		if (courseId != null) {
			result.put("courseId", courseId.toString());
		}
		return result;
	}

	@RequestMapping(value = "/delete/{courseId}", method = RequestMethod.DELETE)
	public Map<String, String> delete(@PathVariable("courseId") Integer courseId) {
		Map<String, String> result = new HashMap<String, String>();
		courseId = courseService.deleteById(courseId);
		if (courseId != null) {
			result.put("courseId", courseId.toString());
		}
		return result;
	}
}
