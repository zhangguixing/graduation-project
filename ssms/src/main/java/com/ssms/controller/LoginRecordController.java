package com.ssms.controller;

import com.ssms.common.CommonResponse;
import com.ssms.common.PageResult;
import com.ssms.common.ResponseUtil;
import com.ssms.common.util.StringUtil;
import com.ssms.model.LoginRecord;
import com.ssms.service.LoginRecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登录日志管理
 **/
@Controller
@RequestMapping("/system/loginRecord")
public class LoginRecordController {
    @Autowired
    private LoginRecordService loginRecordService;

    @RequiresPermissions("loginRecord:view")
    @RequestMapping()
    public String loginRecord() {
        return "system/loginRecord.html";
    }

    /**
     * 查询所有登录日志
     **/
    @RequiresPermissions("loginRecord:view")
    @ResponseBody
    @RequestMapping("/list")
    public CommonResponse list(Integer page, Integer limit, String startDate, String endDate, String account) {
        if (StringUtil.isBlank(startDate)) {
            startDate = null;
        } else {
            startDate += " 00:00:00";
        }
        if (StringUtil.isBlank(endDate)) {
            endDate = null;
        } else {
            endDate += " 23:59:59";
        }
        if (StringUtil.isBlank(account)) {
            account = null;
        }
        return ResponseUtil.generateResponse(loginRecordService.list(page, limit, startDate, endDate, account));
    }

}
