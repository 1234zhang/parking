package com.netplus.catpark.service.util;


import com.netplus.catpark.domain.model.Response;
import com.netplus.catpark.domain.model.exception.ErrorCode;

public class ResponseUtil {
    public static <T> Response<T> makeSuccess(T data) {
        return new Response<>(Response.CODE_SUCCEED, "SUCCESS", data);
    }

    public static <T> Response<T> makeSuccess(T data, String message) {
        return new Response<>(Response.CODE_SUCCEED, message, data);
    }

    public static <T> Response<T> makeFail(String message) {
        return new Response<>(Response.CODE_FAILED, message, null);
    }

    public static <T> Response<T> makeFail(String message, T data) {
        return new Response<>(Response.CODE_FAILED, message, data);
    }

    public static <T> Response<T> notLogin() {
        return new Response(4000, "没有登录", "");
    }

    public static <T> Response<T> makeFail(ErrorCode errorCode) {
        return new Response<>(errorCode.getCode(), errorCode.getMessage());
    }

    public static boolean isSucceed(Response response) {
        //&& response.getSuccess();
        return response != null ;
    }

    public static boolean isFailed(Response response) {
        return !isSucceed(response);
    }

}
