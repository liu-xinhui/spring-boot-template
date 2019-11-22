package com.step.template.main.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Jackson工具类,切勿使用Gson
 */
@Component
public class JacksonUtil {
    /**
     * 获取spring管理的实例,切勿自己new,否则无法获默认配置
     */
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * 对象转化为json字符串
     */
    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对象转化为json字符串，美化
     */
    public static String toJsonPretty(Object obj) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json字符串转化为对象
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        if (!StringUtils.isBlank(json)) {
            try {
                return mapper.readValue(json, clazz);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new NullPointerException("json不能为null或者空字符串");
        }
    }

    /**
     * json字符串转为list
     */
    public static <T> List<T> json2List(final String json, Class<T> clazz) {
        if (StringUtils.isBlank(json)) {
            throw new NullPointerException("json不能为null或者空字符串");
        } else {
            try {
                JavaType javaType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
                return mapper.readValue(json, javaType);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static <T> T object2Bean(Object obj, Class<T> clazz) {
        return mapper.convertValue(obj, clazz);
    }

    @Autowired
    public void setMapper(ObjectMapper mapper) {
        JacksonUtil.mapper = mapper;
    }
}
