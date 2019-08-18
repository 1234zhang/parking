package com.netplus.catpark.resolver;

import com.netplus.catpark.domain.model.Response;
import com.netplus.catpark.domain.model.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ApiExceptionResolver implements HandlerExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionResolver.class);

    private static final String CONTENT_TYPE = "application/json; charset=utf-8";

    private CustomObjectMapper objectMapper = new CustomObjectMapper();

    private void printJson(HttpServletResponse response, Object value) {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(CONTENT_TYPE);
        try {
            PrintWriter writer = response.getWriter();
            writer.print(objectMapper.writeValueAsString(value));
            writer.flush();
        } catch (IOException e) {
            LOGGER.warn("Print JSON error.", e);
        }
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Response<ApiError<ParameterError>> resultMode = new Response<>();
        ApiError<ParameterError> error = new ApiError<>();
        // 防止打太多无意义的日志
        logError(LOGGER, ex, 100, "Resolving exception: ");

        if (ex instanceof ApiRuntimeException) {
            ApiRuntimeException apiEx = (ApiRuntimeException) ex;
            resultMode.setCode(apiEx.getErrorCode().getCode());
            resultMode.setMsg(apiEx.getErrorCode().getMessage());
            error.setErrorCode(ApiErrorCode.NORMAL.getErrorCode());
            response.setStatus(apiEx.getApiErrorCode().getHttpStatusCode());
            if (ex instanceof ApiParameterException) {
                ApiParameterException apiParameterException = (ApiParameterException) apiEx;
                error.setErrorCode(ApiErrorCode.SERVICE_ERROR.getErrorCode());

                if (apiParameterException.getErrors() != null) {
                    for (ObjectError objError : apiParameterException.getErrors().getAllErrors()) {
                        if (objError instanceof FieldError) {
                            FieldError e = (FieldError) objError;
                            error.getErrorDetails().add(new ParameterError(
                                e.getDefaultMessage(), e.getField(),
                                e.getRejectedValue() == null ? null : e.getRejectedValue().toString()));
                        }
                    }
                }
            }
        } else if (ex instanceof MultipartException) {
            error.setErrorCode(ApiErrorCode.PARAMETER_ERROR.getErrorCode());
            resultMode.setMsg(ErrorCode.SYSTEM_PARAM_ERROR.getMessage());
            resultMode.setCode(ErrorCode.SYSTEM_PARAM_ERROR.getCode());
        } else if (ex instanceof AuthorizationException) {
            error.setErrorCode(ApiErrorCode.AUTHORIZATION_FAILED.getErrorCode());
            resultMode.setCode(ErrorCode.USER_ACCOUNT_NO_LOGIN.getCode());
            resultMode.setMsg(ErrorCode.USER_ACCOUNT_NO_LOGIN.getMessage());
        } else if (ex instanceof ServiceRuntimeException) {
            error.setErrorCode(ApiErrorCode.SERVICE_ERROR.getErrorCode());
            ServiceRuntimeException serviceEx = (ServiceRuntimeException) ex;
            resultMode.setCode(serviceEx.getErrorCode().getCode());
            resultMode.setMsg(serviceEx.getErrorCode().getMessage());
            response.setStatus(serviceEx.getApiErrorCode().getHttpStatusCode());
        } else {
            error.setErrorCode(ApiErrorCode.SERVICE_ERROR.getErrorCode());
            resultMode.setMsg(ex.getMessage());
            resultMode.setCode(ErrorCode.SYSTEM_ERROR.getCode());
        }
        resultMode.setData(error);
        printJson(response, resultMode);
        return new ModelAndView();
    }

    private void logError(Logger logger, Exception ex, int maxDepth, String prefixMsg) {
        if (ex instanceof ApiParameterException) {
            ErrorCode errorCode = ((ApiParameterException) ex).getErrorCode();
            // 某些语义较明确的业务，不需要打印出错误栈
            if (errorCode == ErrorCode.USER_ACCOUNT_VERIFY_CODE_ERROR) {
                logger.error(ex.getMessage());
                return;
            }
        }
        Exception error = new Exception(ex.getMessage());
        StackTraceElement[] stackTrace = ex.getStackTrace();
        int lastIndex = 0;
        for (int i = stackTrace.length - 1; i >= 0; i--) {
            StackTraceElement element = stackTrace[i];
            if (element.getClassName().startsWith("com.qinfenfeng.cloudprint")) {
                lastIndex = i;
                break;
            }
        }
        if (lastIndex > maxDepth) {
            lastIndex = maxDepth;
        }
        StackTraceElement[] stackTraceToPrint = Arrays.copyOf(stackTrace, lastIndex + 1);
        error.setStackTrace(stackTraceToPrint);
        logger.error(prefixMsg, error);
    }
}

