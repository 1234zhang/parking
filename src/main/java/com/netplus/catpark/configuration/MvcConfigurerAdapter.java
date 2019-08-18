package com.netplus.catpark.configuration;

import com.netplus.catpark.intercptor.LoginInterceptor;
import com.netplus.catpark.resolver.ApiExceptionResolver;
import com.netplus.catpark.resolver.CustomObjectMapper;
import com.netplus.catpark.resolver.JacksonHttpMessageConverter;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brandon.
 * @date 2019/8/17.
 * @time 12:32.
 */

public class MvcConfigurerAdapter implements WebMvcConfigurer {
    //添加拦截器
    @Autowired
    public LoginInterceptor loginInterceptor;

    /**
     * 配置请求拦截器
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> interceptorSet = new ArrayList<>();
        interceptorSet.add("/api/**");
        List<String> passSet = new ArrayList<>();
        passSet.add("/api/client/min/login");
        passSet.add("/api/client/h5/login");
        passSet.add("/api/client/android/login");
        passSet.add("/api/client/phone/login");
        passSet.add("/api/document/test");
        passSet.add("/api/document/testDown");
        passSet.add("/api/manager/**");
        //微信回调
        passSet.add("/api/wxpay/min/reback");
        passSet.add("/api/merchant/*");
        passSet.add("/api/order/orderTest");
        passSet.add("/api/client/sendAuthCode");
        passSet.add("/api/client/phone/register");

        List<String> merChantList = new ArrayList<>();
        merChantList.add("/manager/sendAuthCode");
        merChantList.add("/manager/register");
        merChantList.add("/manager/login");

        //拦截所有请求
        registry.addInterceptor(loginInterceptor).addPathPatterns(interceptorSet).excludePathPatterns(passSet);
    }


    /**
     * 异常处理
     *
     * @return
     */
    @Bean
    public ApiExceptionResolver apiExceptionResolver() {
        return new ApiExceptionResolver();
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(apiExceptionResolver());
    }

    @Bean
    public JacksonHttpMessageConverter jacksonHttpMessageConverter() {
        // 定制jackson处理规则
        CustomObjectMapper customObjectMapper = new CustomObjectMapper();

        return new JacksonHttpMessageConverter(customObjectMapper);
    }

    /**
     * validator处理器
     *
     * @return
     */
    @Bean
    @Primary
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setProviderClass(HibernateValidator.class);
        return validatorFactoryBean;
    }

    @Override
    public Validator getValidator() {
        return validator();
    }

    /**
     * 方法级别的单个参数验证开启
     *
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

}
