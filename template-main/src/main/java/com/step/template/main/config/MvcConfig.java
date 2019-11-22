package com.step.template.main.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.step.template.main.util.MyDateUtil.*;


/**
 * WebMVC配置
 */
@Slf4j
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * ajax跨域请求
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }


    /**
     * get方法接收日期类型的参数
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setDateTimeFormatter(PATTERN_DATE_TIME);
        registrar.setDateFormatter(PATTERN_DATE);
        registrar.setTimeFormatter(PATTERN_TIME);
        registrar.registerFormatters(registry);
    }

    /**
     * 更改jackson默认配置
     */
    @Bean
    public ObjectMapper ObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // 对于空的对象转json的时候不抛出错误
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 禁用遇到未知属性抛出异常
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 序列化BigDecimal时不使用科学计数法输出
        objectMapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
        // 日期和时间格式化
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(PATTERN_DATE_TIME));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(PATTERN_DATE));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(PATTERN_TIME));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(PATTERN_DATE_TIME));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(PATTERN_DATE));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(PATTERN_TIME));
        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }

    /**
     * 定时任务线程池更改，防止多个任务并行
     */
    @Bean
    public TaskScheduler taskScheduler() {
        final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(5);
        return scheduler;
    }
}
