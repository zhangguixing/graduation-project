package com.ssms.service;

import com.github.pagehelper.PageInfo;
import com.ssms.model.LoginRecord;

public interface LoginRecordService {

    boolean add(LoginRecord loginRecord);

    PageInfo<LoginRecord> list(int pageNum, int pageSize, String startDate, String endDate, String account);
}
