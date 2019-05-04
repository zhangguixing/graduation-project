package com.ssms.common;


import com.google.gson.Gson;
import lombok.Data;

import java.io.Serializable;

@Data
public class CommonResponse implements Serializable {

    private int code;

    private String message;

    private Object data;

    public CommonResponse setCode(ResponseCode responseCode) {
        this.code = responseCode.code;
        return this;
    }

    public CommonResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public CommonResponse setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
