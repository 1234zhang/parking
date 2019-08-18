package com.netplus.catpark.domain.model.exception;

public class ApiRuntimeException extends RuntimeException {

    private ApiErrorCode apiErrorCode;

    ErrorCode errorCode;

    public ApiRuntimeException(ErrorCode errorCode) {
        this(ApiErrorCode.NORMAL, errorCode);
    }

    public ApiRuntimeException(ApiErrorCode apiErrorCode, ErrorCode errorCode) {
        this.apiErrorCode = apiErrorCode;
        this.errorCode = errorCode;
    }

    public ApiErrorCode getApiErrorCode() {
        return apiErrorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return "[" + errorCode.getCode() + "]" + errorCode.getMessage();
    }

}
