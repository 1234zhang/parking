package com.netplus.catpark.resolver;


import com.netplus.catpark.domain.model.Response;
import com.netplus.catpark.service.util.ResponseUtil;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * jackson处理类，用来定制返回前端的对象。
 *
 * @author wxl
 */
public class JacksonHttpMessageConverter extends MappingJackson2HttpMessageConverter {
    public JacksonHttpMessageConverter(com.fasterxml.jackson.databind.ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    protected void writeInternal(Object obj, Type type, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        if (obj instanceof Response) {
            super.writeInternal(obj, type, outputMessage);
        } else {
            // 如果返回的是个普通对象，则将其转化为 Response，这样简化了将返回结果手动封装成 Response 的工作。
            super.writeInternal(ResponseUtil.makeSuccess(obj), type, outputMessage);
        }
    }

}
