package com.ssms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 业务异常
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class BusinessException extends IException {
    private static final long serialVersionUID = 5450935008012318697L;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Integer code, String message) {
        super(code, message);
    }

    @Override
    public Integer getCode() {
        Integer code = super.getCode();
        if (code == null) {
            code = 500;
        }
        return code;
    }

    @Override
    public String getMessage() {
        String message = super.getMessage();
        if (message == null) {
            message = "系统繁忙";
        }
        return message;
    }
}
