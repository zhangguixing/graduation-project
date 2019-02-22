package com.sams.controller;

import com.sams.constant.Constants;
import com.sams.entity.SystemInfo;
import com.sams.entity.User;
import com.sams.response.JsonResult;
import com.sams.service.LoginService;
import com.sams.service.SysteminfoService;
import com.sams.util.StringUtils;
import com.sams.util.VCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private SysteminfoService systeminfoService;

    @PostMapping("login")
    public JsonResult login(User user, String vcode, HttpServletRequest request){
        String sessionVCode = (String) request.getSession().getAttribute(Constants.SESSION_VCODE);
        if(sessionVCode==null || !sessionVCode.equals(vcode)){
            return JsonResult.error("验证码错误");
        }
        String account = user.getAccount();
        if(StringUtils.isNotBlank(account)){
            User resultUser = loginService.getPasswordByAccontAndType(account,user.getType());
            if(StringUtils.isBlank(resultUser.getPassword())){
                return JsonResult.error("账户名不存在");
            }else if(resultUser.getPassword().equals(user.getPassword())) {
                //登录成功
                resultUser.setPassword(null);
                request.getSession().setAttribute(Constants.SESSION_USER,resultUser);
                if(resultUser.getType()==1){
                    return JsonResult.generateResult("admin");
                }else if(resultUser.getType()==2) {
                    return JsonResult.generateResult("student");
                }else if(resultUser.getType()==3) {
                    return JsonResult.generateResult("teacher");
                }
                SystemInfo systemInfo = (SystemInfo) request.getServletContext().getAttribute(Constants.SYSTEM_INFO);
                if(systemInfo == null){
                    systemInfo = systeminfoService.getSystemInfo();
                    request.getServletContext().setAttribute(Constants.SYSTEM_INFO,systemInfo);
                }
            }else {
                return JsonResult.error("密码错误");
            }
        }
        return JsonResult.error("用户名不可为空");
    }

    @GetMapping("getVCode")
    public void getVCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //创建验证码生成器对象
        VCodeGenerator vcGenerator = new VCodeGenerator();
        //生成验证码
        String vcode = vcGenerator.generatorVCode();
        //将验证码保存在session域中,以便判断验证码是否正确
        request.getSession().setAttribute(Constants.SESSION_VCODE, vcode);
        //生成验证码图片
        BufferedImage vImg = vcGenerator.generatorRotateVCodeImage(vcode, true);
        //输出图像
        ImageIO.write(vImg, "gif", response.getOutputStream());
    }
}