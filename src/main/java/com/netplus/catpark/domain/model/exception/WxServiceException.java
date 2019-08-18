package com.netplus.catpark.domain.model.exception;

public class WxServiceException extends RuntimeException {

    private static final long serialVersionUID = -1297546222874428958L;

    private int code;

    private String message;

    public WxServiceException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
