package com.netplus.catpark.domain.model.exception;

public final class ParameterError extends ErrorDetail {

    private String errorField;

    private String errorValue;

    public ParameterError(String errorMessage, String errorField, String errorValue) {
        super(errorMessage);
        this.errorField = errorField;
        this.errorValue = errorValue;
    }

    public String getErrorField() {

        return errorField;
    }

    public void setErrorField(String errorField) {
        this.errorField = errorField;
    }

    public String getErrorValue() {
        return errorValue;
    }

    public void setErrorValue(String errorValue) {
        this.errorValue = errorValue;
    }
}
