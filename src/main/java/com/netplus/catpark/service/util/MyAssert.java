package com.netplus.catpark.service.util;

import com.netplus.catpark.domain.model.exception.ApiRuntimeException;
import com.netplus.catpark.domain.model.exception.ErrorCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

public final class MyAssert {

    private MyAssert() {
    }

    public static void notNull(Object team, Object object) {
        if (object == null) {
            throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR_IS_NULL);
        }
    }

    public static void notNull(Object object, String errorMsg) {
        if (object == null) {
            throw new RuntimeException(errorMsg);
        }
    }

    public static void notNull(Object object, ErrorCode errorCode) {
        if (object == null) {
            throw new ApiRuntimeException(errorCode);
        }
    }

    public static void notNull(Object... object) {
        for (Object obj : object) {
            if (obj == null) {
                throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR_IS_NULL);
            }
        }
    }

    public static void isNull(Object object, ErrorCode errorCode) {
        if (object != null) {
            throw new ApiRuntimeException(errorCode);
        }
    }

    public static void isNull(Object object, String msg) {
        if (object != null) {
            throw new RuntimeException(msg);
        }
    }

    public static void notEmpty(Collection col) {
        if (CollectionUtils.isEmpty(col)) {
            throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR);
        }
    }

    public static void notEmpty(Collection col, String errorMsg) {
        if (CollectionUtils.isEmpty(col)) {
            throw new RuntimeException(errorMsg);
        }
    }

    public static void isPositive(Long number) {
        if (number == null || number <= 0) {
            throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR);
        }
    }

    public static void isPositive(Long number, ErrorCode errorCode) {
        if (number == null || number <= 0) {
            throw new ApiRuntimeException(errorCode);
        }
    }

    public static void isPositive(Long... number) {
        for (Long num : number) {
            if (num == null || num <= 0) {
                throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR);
            }
        }
    }

    public static void isNatual(Long number) {
        if (number == null || number < 0) {
            throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR);
        }
    }

    public static void isNatual(Long... number) {
        for (Long num : number) {
            if (num == null || num < 0) {
                throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR);
            }
        }
    }

    public static void isZero(Integer number, ErrorCode errorCode) {
        if (number == null || number != 0) {
            throw new ApiRuntimeException(errorCode);
        }
    }

    public static void isPositive(Integer number, String errorMsg) {
        if (number == null || number <= 0) {
            throw new RuntimeException(errorMsg);
        }
    }

    public static void isPositive(Integer number) {
        if (number == null || number <= 0) {
            throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR);
        }
    }

    public static void isPositive(Integer number, ErrorCode errorCode) {
        if (number == null || number <= 0) {
            throw new ApiRuntimeException(errorCode);
        }
    }

    public static void isPositive(Integer... number) {
        for (Integer num : number) {
            if (num == null || num <= 0) {
                throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR);
            }
        }
    }

    public static void isNatual(Integer number) {
        if (number == null || number < 0) {
            throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR);
        }
    }

    public static void isNatual(Integer... number) {
        for (Integer num : number) {
            if (num == null || num < 0) {
                throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR);
            }
        }
    }

    public static void nullOrNatual(Integer number) {
        if (number != null && number < 0) {
            throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR);
        }
    }

    public static void nullOrNatual(Integer... number) {
        for (Integer num : number) {
            if (num != null && num < 0) {
                throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR);
            }
        }
    }

    public static void notEmpty(String str, ErrorCode errorCode) {
        if (StringUtils.isEmpty(str)) {
            throw new ApiRuntimeException(errorCode);
        }
    }

    public static void notEmpty(String str, String errorMsg) {
        if (StringUtils.isEmpty(str)) {
            throw new RuntimeException(errorMsg);
        }
    }

    public static void notBlank(String str, String errorMsg) {
        if (StringUtils.isBlank(str)) {
            throw new RuntimeException(errorMsg);
        }
    }

    public static void notBlank(String str) {
        if (StringUtils.isBlank(str)) {
            throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR);
        }
    }

    public static void notBlank(String... str) {
        for (String s : str) {
            if (StringUtils.isBlank(s)) {
                throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR);
            }
        }
    }

    public static void notBlank(String str, ErrorCode errorCode) {
        if (StringUtils.isBlank(str)) {
            throw new ApiRuntimeException(errorCode);
        }
    }

    public static void notEmpty(Collection<?> col, ErrorCode errorCode) {
        if (CollectionUtils.isEmpty(col)) {
            throw new ApiRuntimeException(errorCode);
        }
    }

    public static void isFalse(boolean condition, ErrorCode errorCode) {
        if (condition) {
            throw new ApiRuntimeException(errorCode);
        }
    }

    public static void isTrue(boolean condition, ErrorCode errorCode) {
        if (!condition) {
            throw new ApiRuntimeException(errorCode);
        }
    }

    public static void isFalse(boolean condition, String errorMsg) {
        if (condition) {
            throw new RuntimeException(errorMsg);
        }
    }

    public static void isTrue(boolean condition, String errorMsg) {
        if (!condition) {
            throw new RuntimeException(errorMsg);
        }
    }
}
