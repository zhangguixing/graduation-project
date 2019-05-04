package com.ssms.controller;

import com.ssms.common.BaseController;
import com.ssms.common.CommonResponse;
import com.ssms.common.ResponseUtil;
import com.ssms.model.CollegeSubjectClass;
import com.ssms.service.CollegeSubjectClassService;
import com.ssms.common.JsonResult;
import com.ssms.common.PageResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 维护学院信息
 */
@Controller
@RequestMapping("system/college")
public class CollegeSubjectClassController extends BaseController {

    @Autowired
    private CollegeSubjectClassService collegeSubjectClassService;

    @RequiresPermissions("college:view")
    @GetMapping
    public String college(Model model){
        return "system/college.html";
    }

    @ResponseBody
    @GetMapping("getCollegeSubjectClassByParentId/{parentId}")
    public CommonResponse getCollegeSubjectClassByParentId(@PathVariable Integer parentId){
        return ResponseUtil.generateResponse(collegeSubjectClassService.getCollegeSubjectClassByParentId(parentId));
    }

    @GetMapping("editForm")
    public String editForm(Model model) {
        List<CollegeSubjectClass> collegeSubjectClasses = collegeSubjectClassService.listCollegeAndSubject();
        model.addAttribute("collegeSubjectClasses", collegeSubjectClasses);
        return "system/collegeForm.html";
    }

    /**
     * 查询所有学院信息
     **/
    @RequiresPermissions("college:view")
    @ResponseBody
    @GetMapping("/list")
    public PageResult<CollegeSubjectClass> list(Integer roleId) {
        List<CollegeSubjectClass> collegeSubjectClasses = collegeSubjectClassService.list();
        return new PageResult<CollegeSubjectClass>(collegeSubjectClasses);
    }

    /**
     * 添加学院信息
     */
    @RequiresPermissions("college:add")
    @ResponseBody
    @PostMapping("/add")
    public JsonResult add(CollegeSubjectClass collegeSubjectClass) {
        if (collegeSubjectClassService.add(collegeSubjectClass)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 修改学院信息
     */
    @RequiresPermissions("college:edit")
    @ResponseBody
    @PostMapping("/update")
    public JsonResult update(CollegeSubjectClass collegeSubjectClass) {
        if (collegeSubjectClassService.update(collegeSubjectClass)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 删除学院信息
     */
    @RequiresPermissions("college:delete")
    @ResponseBody
    @PostMapping("/delete")
    public JsonResult delete(Integer collegeId) {
        if (collegeSubjectClassService.delete(collegeId)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }
}
