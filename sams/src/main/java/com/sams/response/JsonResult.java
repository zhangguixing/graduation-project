package com.sams.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult {
    private int status;
    private String msg;
    private Object data;

    public static JsonResult ok(){
        return new JsonResult(0,"success",null);
    }

    public static JsonResult error(String msg){
        return new JsonResult(-1,msg,null);
    }

    public static JsonResult generateResult(Object data){
        return new JsonResult(0,"success",data);
    }
}
