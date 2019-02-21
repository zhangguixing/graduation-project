package com.sams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 权限管理
 **/
@Controller
@RequestMapping("/system/authorities")
public class AuthoritiesController {
//    @Autowired
//    private AuthoritiesService authoritiesService;
//    @Autowired
//    private RoleService roleService;
//
//    @RequiresPermissions("authorities:view")
//    @RequestMapping()
//    public String authorities(Model model) {
//        List<Role> roles = roleService.list(false);
//        model.addAttribute("roles", roles);
//        return "system/authorities.html";
//    }
//
//    @RequestMapping("editForm")
//    public String editForm(Model model) {
//        List<Authorities> authorities = authoritiesService.listMenu();
//        model.addAttribute("authorities", authorities);
//        return "system/authoritiesForm.html";
//    }
//
//    /**
//     * 查询所有权限
//     **/
//    @RequiresPermissions("authorities:view")
//    @ResponseBody
//    @RequestMapping("/list")
//    public PageResult<Map<String, Object>> list(Integer roleId) {
//        List<Map<String, Object>> maps = new ArrayList<>();
//        List<Authorities> authorities = authoritiesService.list();
//        List<Authorities> roleAuths = authoritiesService.listByRoleId(roleId);
//        for (Authorities one : authorities) {
//            Map<String, Object> map = ReflectUtil.objectToMap(one);
//            map.put("checked", 0);
//            for (Authorities roleAuth : roleAuths) {
//                if (one.getAuthorityId().equals(roleAuth.getAuthorityId())) {
//                    map.put("checked", 1);
//                    break;
//                }
//            }
//            maps.add(map);
//        }
//        return new PageResult<>(maps);
//    }
//
//    /**
//     * 添加权限
//     */
//    @RequiresPermissions("authorities:add")
//    @ResponseBody
//    @RequestMapping("/add")
//    public JsonResult add(Authorities authorities) {
//        if (authoritiesService.add(authorities)) {
//            return JsonResult.ok("添加成功");
//        }
//        return JsonResult.error("添加失败");
//    }
//
//    /**
//     * 修改权限
//     */
//    @RequiresPermissions("authorities:edit")
//    @ResponseBody
//    @RequestMapping("/update")
//    public JsonResult update(Authorities authorities) {
//        if (authoritiesService.update(authorities)) {
//            return JsonResult.ok("修改成功");
//        }
//        return JsonResult.error("修改失败");
//    }
//
//    /**
//     * 删除权限
//     */
//    @RequiresPermissions("authorities:delete")
//    @ResponseBody
//    @RequestMapping("/delete")
//    public JsonResult delete(Integer authorityId) {
//        if (authoritiesService.delete(authorityId)) {
//            return JsonResult.ok("删除成功");
//        }
//        return JsonResult.error("删除失败");
//    }

}
