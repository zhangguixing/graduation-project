package com.jefflee.controller.shiro;

import com.jefflee.entity.shiro.TbAdmin;
import com.jefflee.service.shiro.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("main/")
public class MainController {
	
	@Autowired
	private MainService mainServiceImpl;
	
	@RequestMapping("getAdminTotal")
	@ResponseBody
	public List<TbAdmin> getAdminTotal(){
		return mainServiceImpl.selAdminList();
	}


	@RequestMapping("getStudentTotal")
	@ResponseBody
	public List<TbAdmin> getStudentTotal(){
		return mainServiceImpl.selStudentTotal();
	}

	@RequestMapping("getTeacherTotal")
	@ResponseBody
	public List<TbAdmin> getTeacherTotal(){
		return mainServiceImpl.selTeacherTotal();
	}
	
	@RequestMapping("/dataAccessGender")
	@ResponseBody
	public Map<String, Object> dataAccessGender() {
	    Map<String, Object> j=new HashMap<>();
	    String[] categories = {"女", "男", "保密"};
	    j.put("categories", categories);
	    Map<String, Object> json=null;
	    List<Map<String, Object>> list=new ArrayList<>();
	    for(int i=0;i<categories.length;i++){
	    	json = new HashMap<>();
		    json.put("value", mainServiceImpl.selStudentCountByGender(i));
		    json.put("name",categories[i]);
		    list.add(json);
	    }
	    j.put("values", list);
	    return j;
	}
}
