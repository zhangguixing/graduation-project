package com.ssms.controller;

import com.ssms.common.BaseController;
import com.ssms.common.exception.ParameterException;
import com.ssms.model.Role;
import com.ssms.model.User;
import com.ssms.service.RoleService;
import com.ssms.service.UserService;
import com.ssms.common.conifg.shiro.EndecryptUtil;
import com.ssms.common.JsonResult;
import com.ssms.common.PageResult;
import com.ssms.common.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequiresPermissions("user:view")
    @RequestMapping
    public String user(Model model) {
        List<Role> roles = roleService.list(false);
        model.addAttribute("roles", roles);
        return "system/user.html";
    }

    @RequestMapping("/editForm")
    public String addUser(Model model) {
        List<Role> roles = roleService.list(false);
        model.addAttribute("roles", roles);
        return "system/userForm.html";
    }

    /**
     * 查询用户列表
     */
    @RequiresPermissions("user:view")
    @ResponseBody
    @RequestMapping("/list")
    public PageResult<User> list(Integer page, Integer limit, String searchKey, String searchValue) {
        if (page == null) {
            page = 0;
            limit = 0;
        }
        if (StringUtil.isBlank(searchValue)) {
            searchKey = null;
        }
        return userService.list(page, limit, true, searchKey, searchValue);
    }

    /**
     * 添加用户
     **/
    @RequiresPermissions("user:add")
    @ResponseBody
    @RequestMapping("/add")
    public JsonResult add(User user, String roleId) {
        user.setRoles(getRoles(roleId));
        user.setPassword("123456");
        if (userService.add(user)) {
            return JsonResult.ok("添加成功");
        } else {
            return JsonResult.error("添加失败");
        }
    }

    /**
     * 删除用户
     **/
    @RequiresPermissions("user:delete")
    @ResponseBody
    @RequestMapping("/delete")
    public JsonResult delete(Integer userId) {
        if ( userService.deleteUserRoleByUserId(userId) && userService.delete(userId)) {
            return JsonResult.ok("删除成功");
        } else {
            return JsonResult.error("删除失败");
        }
    }

    /**
     * 修改用户
     **/
    @RequiresPermissions("user:edit")
    @ResponseBody
    @RequestMapping("/update")
    public JsonResult update(User user, String roleId) {
        user.setRoles(getRoles(roleId));
        if (userService.update(user)) {
            return JsonResult.ok("修改成功");
        } else {
            return JsonResult.error("修改失败");
        }
    }

    private List<Role> getRoles(String roleStr) {
        List<Role> roles = new ArrayList<>();
        String[] split = roleStr.split(",");
        for (String t : split) {
            if (t.equals("1")) {
                throw new ParameterException("不能添加超级管理员");
            }
            roles.add(new Role(Integer.parseInt(t)));
        }
        return roles;
    }

    /**
     * 修改用户状态
     **/
    @RequiresPermissions("user:delete")
    @ResponseBody
    @RequestMapping("/updateState")
    public JsonResult updateState(Integer userId, Integer state) {
        if (userService.updateState(userId, state)) {
            return JsonResult.ok();
        } else {
            return JsonResult.error();
        }
    }

    /**
     * 修改自己密码
     **/
    @ResponseBody
    @RequestMapping("/updatePsw")
    public JsonResult updatePsw(String oldPsw, String newPsw) {
        if ("admin".equals(getLoginUser().getUsername())) {
            return JsonResult.error("演示账号admin关闭该功能");
        }
        String finalSecret = EndecryptUtil.encrytMd5(oldPsw, getLoginUserName(), 3);
        if (!finalSecret.equals(getLoginUser().getPassword())) {
            return JsonResult.error("原密码输入不正确");
        }
        if (userService.updatePsw(getLoginUserId(), getLoginUserName(), newPsw)) {
            return JsonResult.ok("修改成功");
        } else {
            return JsonResult.error("修改失败");
        }
    }

    /**
     * 重置密码
     **/
    @RequiresPermissions("user:edit")
    @ResponseBody
    @RequestMapping("/restPsw")
    public JsonResult resetPsw(Integer userId) {
        User byId = userService.getById(userId);
        if (userService.updatePsw(userId, byId.getUsername(), "123456")) {
            return JsonResult.ok("重置成功");
        } else {
            return JsonResult.error("重置失败");
        }
    }
}
