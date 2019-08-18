package com.netplus.catpark.domain.model.exception;

public class ServiceRuntimeException extends RuntimeException {

    private ApiErrorCode apiErrorCode;
    private ErrorCode errorCode;

    public ServiceRuntimeException(ErrorCode errorCode) {
        this(ApiErrorCode.NORMAL, errorCode);
    }

    public ServiceRuntimeException(ApiErrorCode apiErrorCode, ErrorCode errorCode) {
        this.apiErrorCode = apiErrorCode;
        this.errorCode = errorCode;
    }

    public ApiErrorCode getApiErrorCode() {
        return apiErrorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
