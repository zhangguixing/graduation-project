package com.sams.controller;

import com.sams.entity.User;
import com.sams.service.LoginService;
import com.sams.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    public String login(User user){
        String account = user.getAccount();
        if(StringUtils.isNotBlank(account)){
            String password = loginService.getPasswordByAccont(account);
            if(StringUtils.isEmpty(password)){
                //TODO 账户名不存在
            }else if(password.equals(user.getPassword())) {
                //TODO 登录成功
            }else {
                //TODO 密码错误
            }
        }
        return null;
    }
}
