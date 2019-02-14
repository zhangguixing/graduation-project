package com.jefflee.service.shiro;

import com.jefflee.entity.shiro.TbLog;
import com.jefflee.entity.shiro.UserSearch;
import com.jefflee.util.shiro.ResultUtil;

import java.util.Date;

public interface LogService {
	//添加日志
	public void insLog(TbLog log);
	
	//获取日志列表
	ResultUtil selLogList(Integer page, Integer limit, UserSearch search);

	//删除指定日期以前的日志
	public int delLogsByDate(Date date);
}
