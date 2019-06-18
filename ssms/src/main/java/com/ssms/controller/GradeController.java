package com.ssms.controller;

import com.ssms.common.BaseController;
import com.ssms.common.CommonResponse;
import com.ssms.common.ResponseUtil;
import com.ssms.model.Grade;
import com.ssms.model.User;
import com.ssms.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("grade")
public class GradeController extends BaseController {

    @Autowired
    private GradeService gradeService;

    @ResponseBody
    @GetMapping("list")
    public CommonResponse list() {
        return ResponseUtil.generateResponse(gradeService.list());
    }

    @ResponseBody
    @GetMapping("personGrade")
    public CommonResponse personGrade() {
        User loginUser = this.getLoginUser();
        Integer gradeId = loginUser.getGradeId();
        if (gradeId != null) {
            return ResponseUtil.generateResponse(gradeService.findNameById(gradeId));
        }
        return ResponseUtil.generateResponse(false);
    }

    @GetMapping("editForm")
    public String editForm() {
        return "/system/gradeForm.html";
    }

    @GetMapping("manage")
    public String manage() {
        return "/system/grade.html";
    }

    /**
     * 查询年级列表
     */
//    @RequiresPermissions("grade:view")
    @ResponseBody
    @RequestMapping("/all")
    public CommonResponse list(Integer pageNum, Integer pageSize) {
        return ResponseUtil.generateResponse(gradeService.all(pageNum, pageSize));
    }

    @ResponseBody
    @PostMapping("delete")
    public CommonResponse delete(Integer id) {
        return ResponseUtil.generateResponse(gradeService.delete(id));
    }

    @ResponseBody
    @PostMapping("add")
    public CommonResponse add(Grade grade) {
        return ResponseUtil.generateResponse(gradeService.add(grade));
    }

    @ResponseBody
    @PostMapping("update")
    public CommonResponse update(Grade grade) {
        return ResponseUtil.generateResponse(gradeService.update(grade));
    }

}
