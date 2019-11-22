package com.step.template.main.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class MyDateUtil {
    /**
     * 标准长日期时间字符串格式
     */
    public static final DateTimeFormatter PATTERN_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /**
     * 标准短日期字符串格式
     */
    public static final DateTimeFormatter PATTERN_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /**
     * 标准时间字符串格式
     */
    public static final DateTimeFormatter PATTERN_TIME = DateTimeFormatter.ofPattern("HH:mm:ss");

    /**
     * 获取n天/年/月...后的日期时间,可以使用负数获取之前的日期时间
     */
    public static LocalDateTime plus(LocalDateTime beginDateTime, long amountToAdd, ChronoUnit unit) {
        return beginDateTime.plus(amountToAdd, unit);
    }

    /**
     * 获取n天/年/月...后的日期时间,可以使用负数获取之前的日期时间
     */
    public static LocalDateTime plus(long amountToAdd, ChronoUnit unit) {
        return plus(LocalDateTime.now(), amountToAdd, unit);
    }

    /**
     * 两个日期之间的间隔,传单位区分时间类型
     */
    public static long between(LocalDateTime beginDateTime, LocalDateTime endDateTime, ChronoUnit unit) {
        return unit.between(beginDateTime, endDateTime);
    }

    /**
     * LocalDateTime转时间戳
     */
    public static long toTimestamp(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 字符串转LocalDateTime
     *
     * @param dateStr 格式必须为{@link MyDateUtil#PATTERN_DATE_TIME}
     */
    public static LocalDateTime toLocalDateTime(String dateStr) {
        return LocalDateTime.parse(dateStr, PATTERN_DATE_TIME);
    }

    /**
     * 获取指定时间的指定格式
     * 如传入LocalDateTime.now(),{@link MyDateUtil#PATTERN_DATE_TIME}
     * 得到2018-02-03 13:42:40
     */
    public static String formatLocalDateTime(LocalDateTime time) {
        return time.format(PATTERN_DATE_TIME);
    }

    /**
     * 获取指定时间的指定格式
     * 如传入LocalDateTime.now(),{@link MyDateUtil#PATTERN_DATE_TIME}
     * 得到2018-02-03 13:42:40
     */
    public static String formatLocalDateTime(LocalDateTime time, String PATTERN) {
        return time.format(DateTimeFormatter.ofPattern(PATTERN));
    }

    /**
     * 获取指定时间的指定格式
     * 如传入LocalDate.now(),{@link MyDateUtil#PATTERN_DATE}
     * 得到2018-02-03
     */
    public static String formatLocalDate(LocalDate time) {
        return time.format(PATTERN_DATE);
    }

    /**
     * 标准北京时间，时区为东八区，自1970年1月1日 0点0分0秒以来的秒数。注意：部分系统取到的值为毫秒级，需要转换成秒(10位数字)。
     */
    public static Long getSecondTimestamp() {
        return System.currentTimeMillis() / 1000;
    }
}
