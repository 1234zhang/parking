package com.netplus.catpark.domain.model.exception;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public final class ApiError<T extends ErrorDetail> {

    private String api;
    private String version;
    private String errorCode;
    private String serviceErrorCode;
    private String defaultMessage;
    private List<T> errorDetails = Lists.newLinkedList();
    private Map<String, Object> data = Maps.newHashMap();

    public String getServiceErrorCode() {
        return serviceErrorCode;
    }

    public void setServiceErrorCode(String serviceErrorCode) {
        this.serviceErrorCode = serviceErrorCode;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public List<T> getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(List<T> errorDetails) {
        this.errorDetails = errorDetails;
    }
}
