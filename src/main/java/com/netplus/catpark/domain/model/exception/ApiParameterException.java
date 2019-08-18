package com.netplus.catpark.domain.model.exception;

import org.springframework.validation.Errors;

public class ApiParameterException extends ApiRuntimeException {

    private final Errors errors;

    public Errors getErrors() {
        return errors;
    }

    public ApiParameterException(ErrorCode errorCode, Errors errors) {
        super(ApiErrorCode.NORMAL, errorCode);
        this.errorCode = errorCode;
        this.errors = errors;
    }

    @Override
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
