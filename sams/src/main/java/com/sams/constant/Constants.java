package com.sams.constant;

import org.springframework.beans.factory.annotation.Value;

/**
 * 常量类
 */
public class Constants {
    /*用户信息*/
    public static final String SESSION_USER = "sessionUser";
    /*验证码*/
    public static final String SESSION_VCODE = "sessionVCode";
    /*系统信息*/
    public static final String SYSTEM_INFO = "systemInfo";
    @Value("${static-project-url}")
    public static String STATIC_PROJECT_URL;
    /*用户详细信息*/
    public static final String USER_DETAIL = "userDetail";
}
