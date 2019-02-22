package com.sams.controller;

import com.sams.constant.Constants;
import com.sams.entity.SystemInfo;
import com.sams.response.JsonResult;
import com.sams.service.SysteminfoService;
import com.sams.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private SysteminfoService systeminfoService;
    @Autowired
    private UserService userService;

    @PostMapping("editSystemInfo")
    public JsonResult editSystemInfo(String name, String value, HttpServletRequest request){
        SystemInfo systemInfo = systeminfoService.updateSystemInfo(name,value);
        request.getServletContext().setAttribute(Constants.SYSTEM_INFO,systemInfo);
        return JsonResult.ok();
    }

    @GetMapping("loginOut")
    public void loginOut(HttpSession session, HttpServletResponse response) throws IOException {
        session.invalidate();
        response.sendRedirect(Constants.STATIC_PROJECT_URL+"index.jsp");
    }

    @PostMapping("editPasswod")
    public JsonResult editPasswod(String account,String password){
        userService.updatePasswordByAccount(account,password);
        return JsonResult.ok();
    }
}
