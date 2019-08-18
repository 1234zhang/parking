package com.netplus.catpark.resolver;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

@Aspect
@Component
public class ValidatorResolver {
    @Autowired
    private Validator validator;

    /**
     *  定义拦截规则：拦截 controller
     */
    @Pointcut("execution(* com.qinfenfeng.cloudprint.controller..*(..))")
    public void controllerMethodPointcut() {
    }

    /**
     *  拦截器具体实现
     */
    @Around("controllerMethodPointcut()")
    public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        Annotation[][] argAnnotations = method.getParameterAnnotations();
        Object[] args = pjp.getArgs();
        for (int i = 0; i < args.length; i++) {
            for (Annotation annotation : argAnnotations[i]) {
                if (Validated.class.isInstance(annotation)) {
                    Validated validated = (Validated) annotation;
                    Class<?>[] groups = validated.value();
                    validAndReturnFirstErrorTips(args[i], groups);
                }
            }
        }
        return pjp.proceed(args);
    }

    /**
     * 校验参数，并返回第一个错误提示
     *
     * @param t   验证的对象
     * @param <T> 对象擦除前原类型
     * @return 第一个错误提示
     */
    private  <T> void validAndReturnFirstErrorTips(T t, Class<?>... groups) {
        Set<ConstraintViolation<T>> validate = validator.validate(t, groups);
        if (!validate.isEmpty()) {
            ConstraintViolation<T> next = validate.iterator().next();
            throw new IllegalArgumentException(next.getPropertyPath() + ": " + next.getMessage());
        }
    }
}