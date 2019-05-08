package com.ssms.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ssms.model.LoginRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoginRecordMapper extends BaseMapper<LoginRecord> {

    List<LoginRecord> listFull(Page<LoginRecord> page, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("account") String account);

}
