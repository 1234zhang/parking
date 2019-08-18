package com.netplus.catpark.domain.model;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 1998307887673028548L;

    public static final int CODE_SUCCEED = 1;
    public static final int CODE_FAILED = 0;

    //private boolean success = false;
    @ApiModelProperty("响应码")
    private int code = 1;
    @ApiModelProperty("响应信息")
    private String msg = "";
    @ApiModelProperty("响应数据")
    private T data = null;

    public Response() {
    }

    public Response(int code, String message) {
        this.code = code;
        this.msg = message;
        //this.success = code == CODE_SUCCEED;
    }

    public Response(int code, String message, T data) {
        this(code, message);
        this.data = data;
    }

//    public boolean getSuccess() {
//        return success;
//    }
//
//    public Response<T> setSuccess(boolean success) {
//        this.success = success;
//        return this;
//    }

    public int getCode() {
        return code;
    }

    public Response<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Response<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public Response<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",data);
        jsonObject.put("msg",msg);
        jsonObject.put("code",code);
        return jsonObject.toJSONString();
    }
}
