package com.ssms.controller;

import com.ssms.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户管理
 */
@Controller
@RequestMapping("/other")
public class OtherController extends BaseController {

	@RequestMapping("/console")
	public String console() {
		return "other/console.html";
	}

	@RequestMapping("/formAdvance")
	public String formAdvance() {
		return "other/form-advance.html";
	}

	@RequestMapping("/formBasic")
	public String formBasic() {
		return "other/form-basic.html";
	}
	
	@RequestMapping("/formStep")
	public String formStep() {
		return "other/form-step.html";
	}


	@RequestMapping("/formStep2")
	public String formStep2() {
		return "other/form-step2.html";
	}

	@RequestMapping("/tableAdvance")
	public String tableAdvance() {
		return "other/table-advance.html";
	}

	@RequestMapping("/tableBasic")
	public String tableBasic() {
		return "other/table-basic.html";
	}

	@RequestMapping("/console2")
	public String console2() {
		return "other/console2.html";
	}

	@RequestMapping("/tableTree")
	public String tableTree() {
		return "other/table-tree.html";
	}

	@RequestMapping("/welcome")
	public String welcome() {
		return "other/welcome.html";
	}

	@RequestMapping("/dialog")
	public String dialog() {
		return "other/dialog.html";
	}

	@RequestMapping("/dropdown")
	public String dropdown() {
		return "other/dropdown.html";
	}

	@RequestMapping("/notice")
	public String notice() {
		return "other/notice.html";
	}
}
