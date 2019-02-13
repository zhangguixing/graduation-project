package com.jefflee.controller.relation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jefflee.po.relation.GroupTeacherPo;
import com.jefflee.service.relation.GroupTeacherService;

@RestController
@RequestMapping(value = "/relat")
public class GroupTeacherController {
	@Resource(name = "groupTeacherService")
	GroupTeacherService groupTeacherService;

	@RequestMapping(value = "/choose", method = RequestMethod.POST)
	public Map<String, String> choose(@RequestParam("groupId") Integer groupid,
			@RequestParam("IdArray") List<Integer> array) {
		Map<String, String> result = new HashMap<String, String>();
		Integer j = null, flag = 0;
		System.out.println(groupid);

		groupTeacherService.deleteById(groupid);// 删除数据库rlat_group_teacher表原有选课数据

		for (int i = 0; i < array.size(); i++) {

			System.out.println(array.get(i));
			GroupTeacherPo groupTeacherPo = new GroupTeacherPo();
			groupTeacherPo.setTeacherId(array.get(i));
			groupTeacherPo.setGroupId(groupid);
			j = groupTeacherService.insert(groupTeacherPo);
			if (j == null) {
				flag = 1;
				break;
			}
		}
		if (flag == 0) {
			result.put("groupId", j.toString());
		} else {
			result.put("groupId", j.toString());
		}
		return result;
	}

	@RequestMapping(value = "/find/{groupId}", method = RequestMethod.GET)
	public List<GroupTeacherPo> findById(@PathVariable("groupId") Integer groupId) {
		return groupTeacherService.selectById(groupId);
	}

}
