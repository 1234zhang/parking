package com.netplus.catpark.annotation;

import java.lang.annotation.*;

/**
 * @author Brandon
 * 用于方式用户短时间重复点击url
 */
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatClickUrl {
}
