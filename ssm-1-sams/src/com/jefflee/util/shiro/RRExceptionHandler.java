package com.jefflee.util.shiro;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @Desc: 异常处理器
 * @author Mr Du
 * @createTime 2018年2月25日
 * @version: v1.0
 */
@RestController
public class RRExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 自定义异常
	 */
	@ExceptionHandler(RRException.class)
	public ResultUtil handleRRException(RRException e){
		ResultUtil r = new ResultUtil();
		r.setCode(e.getCode());
		r.setMsg(e.getMessage());
		return r;
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public ResultUtil handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return ResultUtil.error("数据库中已存在该记录");
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResultUtil handleAuthorizationException(AuthorizationException e){
		logger.error(e.getMessage(), e);
		return ResultUtil.error("没有权限，请联系管理员授权");
	}

	@ExceptionHandler(Exception.class)
	public ResultUtil handleException(Exception e){
		logger.error(e.getMessage(), e);
		return ResultUtil.error();
	}
}
