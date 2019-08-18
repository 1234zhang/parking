package com.netplus.catpark.domain.model.exception;

public enum ApiErrorCode {

    /**
     * URL签名验证失败
     */
    SIGN_VERIFY_FAILED(403, "SIGN_VERIFY_FAILED"),

    /**
     * 授权失败
     */
    AUTHORIZATION_FAILED(401, "AUTHORIZATION_FAILED"),

    /**
     * 服务级错误
     */
    SERVICE_ERROR(500, "SERVICE_ERROR"),

    /**
     * 参数错误
     */
    PARAMETER_ERROR(400, "PARAMETER_ERROR"),

    NORMAL(200, "系统错误");

    private int httpStatusCode;

    private String errorCode;

    ApiErrorCode(int httpStatusCode, String errorCode) {
        this.httpStatusCode = httpStatusCode;
        this.errorCode = errorCode;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
