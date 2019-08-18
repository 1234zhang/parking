package com.netplus.catpark.domain.model.exception;

/**
 * @author: wuxinlong
 * @date: 2018/11/29
 */
public class ErrorUtil {
    /**
     * 抛出参数错误异常
     *
     * @param errorMsg
     */
    public static void throwParameterError(String errorMsg) {
        throw new RuntimeException(errorMsg);
    }

    /**
     * 抛出第三方调用异常
     *
     * @param errorMsg
     */
    public static void throwInvokeError(String errorMsg) {
        throw new RuntimeException(errorMsg);
    }

    public static void throwError(ErrorCode errorCode) {
        throw new ApiParameterException(errorCode, null);
    }

    public static void notLoginError() {
        throw new ApiParameterException(ErrorCode.USER_ACCOUNT_NO_LOGIN, null);
    }
}
