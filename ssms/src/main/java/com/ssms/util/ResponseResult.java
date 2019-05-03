package com.ssms.util;

import java.io.Serializable;

import lombok.Data;

/**
 * 返回结果集对象
 */
@Data
public class ResponseResult implements Serializable {
    private Integer code;
    private String msg;
    private Object data;

    public static ResponseResult ok(Object data){
        return ok(200,null,data);
    }
    public static ResponseResult ok(Integer code,String msg,Object data){
        ResponseResult responseResult = new ResponseResult();
        responseResult.code = code;
        responseResult.msg =msg;
        responseResult.data = data;
        return responseResult;
    }

    public static ResponseResult error(String msg){
        return ok(500,msg,null);
    }
    public static ResponseResult error(Integer code,String msg,Object data){
        ResponseResult responseResult = new ResponseResult();
        responseResult.code = code;
        responseResult.msg =msg;
        responseResult.data = data;
        return responseResult;
    }

}
