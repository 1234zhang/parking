package com.netplus.catpark.domain.model.exception;

public class ErrorDetail {

    private String errorMessage;

    public ErrorDetail() {
    }

    public ErrorDetail(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
