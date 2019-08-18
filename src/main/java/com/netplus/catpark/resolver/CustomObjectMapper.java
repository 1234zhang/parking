package com.netplus.catpark.resolver;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class CustomObjectMapper extends ObjectMapper {
    /**
     * 默认日期时间格式
     */
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 默认日期格式
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 默认时间格式
     */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    public CustomObjectMapper() {
        super();

        disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        setDefaultPropertyInclusion(JsonInclude.Include.ALWAYS);
        setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));

        // 从JSON到java object
        // 没有匹配的属性名称时不作失败处理
        this.configure(MapperFeature.AUTO_DETECT_FIELDS, true);

        // 反序列化
        // 禁止遇到空原始类型时抛出异常，用默认值代替。
        this.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        this.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        // 禁止遇到未知（新）属性时报错，支持兼容扩展
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        this.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        // 按时间戳格式读取日期
        this.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        this.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        this.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        // 序列化
        // 禁止序列化空值
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        // 按时间戳格式生成日期
        this.configure(SerializationFeature.FLUSH_AFTER_WRITE_VALUE, true);
        // 不包含空值属性
        this.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 是否缩放排列输出，默认false，有些场合为了便于排版阅读则需要对输出做缩放排列
        this.configure(SerializationFeature.INDENT_OUTPUT, false);
        // 设置全局的时间转化
        SimpleDateFormat smt = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);
        this.setDateFormat(smt);
        // 注意：不要开启序列化和反序列化时为对象附加@class属性（坑）
        setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        // Long转String
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        registerModule(simpleModule);

        // 处理LocalDateTime的问题
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));
        registerModule(new JavaTimeModule());

    }
}
