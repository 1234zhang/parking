package com.netplus.catpark.domain.model.exception;

public class OperationException extends RuntimeException {

    public OperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
