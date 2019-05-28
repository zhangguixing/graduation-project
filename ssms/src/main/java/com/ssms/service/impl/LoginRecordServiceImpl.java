
package com.ssms.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssms.common.PageResult;
import com.ssms.dao.LoginRecordMapper;
import com.ssms.model.LoginRecord;
import com.ssms.service.LoginRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LoginRecordServiceImpl implements LoginRecordService {
    @Autowired
    private LoginRecordMapper loginRecordMapper;

    @Override
    public boolean add(LoginRecord loginRecord) {
        loginRecord.setCreateTime(new Date());
        return loginRecordMapper.insert(loginRecord) > 0;
    }

    @Override
    public PageInfo<LoginRecord> list(int pageNum, int pageSize,
                                      String startDate, String endDate, String account) {
        PageHelper.startPage(pageNum,pageSize);
        List<LoginRecord> records = loginRecordMapper.listFull(startDate,endDate, account);
        return PageInfo.of(records);
    }
}