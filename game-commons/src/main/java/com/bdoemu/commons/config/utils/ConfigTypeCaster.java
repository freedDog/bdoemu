package com.bdoemu.commons.config.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

/**
 * @ClassName ConfigTypeCaster
 * @Description 配置类型
 * @Author JiangBangMing
 * @Date 2019/6/20 12:12
 * VERSION 1.0
 */

public class ConfigTypeCaster {
    private static final Logger log;
    private static final Class[] _allowedTypes;

    public static void cast(final Object object, final Field field, final String value) throws IllegalAccessException {
        if (!isCastable(field)) {
            ConfigTypeCaster.log.error("Unsupported type [{}] for field [{}]", (Object)field.getType().getName(), (Object)field.getName());
            return;
        }
        final Class type = field.getType();
        final boolean oldAccess = field.isAccessible();
        field.setAccessible(true);
        if (type.isEnum()) {
            field.set(object, Enum.valueOf(type, value));
        }
        else if (type == Integer.class || type == Integer.TYPE) {
            field.set(object, Integer.decode(value));
        }
        else if (type == Short.class || type == Short.TYPE) {
            field.set(object, Short.decode(value));
        }
        else if (type == Float.class || type == Float.TYPE) {
            field.set(object, Float.parseFloat(value));
        }
        else if (type == Double.class || type == Double.TYPE) {
            field.set(object, Double.parseDouble(value));
        }
        else if (type == Long.class || type == Long.TYPE) {
            field.set(object, Long.decode(value));
        }
        else if (type == Boolean.class || type == Boolean.TYPE) {
            field.set(object, Boolean.parseBoolean(value));
        }
        else if (type == String.class) {
            field.set(object, value);
        }
        else if (type == Character.class || type == Character.TYPE) {
            field.set(object, value.charAt(0));
        }
        else if (type == Byte.class || type == Byte.TYPE) {
            field.set(object, Byte.parseByte(value));
        }
        else if (type == AtomicInteger.class) {
            field.set(object, new AtomicInteger(Integer.decode(value)));
        }
        else if (type == AtomicBoolean.class) {
            field.set(object, new AtomicBoolean(Boolean.parseBoolean(value)));
        }
        else if (type == AtomicLong.class) {
            field.set(object, new AtomicLong(Long.decode(value)));
        }
        else if (type == BigInteger.class) {
            field.set(object, new BigInteger(value));
        }
        else if (type == BigDecimal.class) {
            field.set(object, new BigDecimal(value));
        }
        else if (type == Path.class) {
            field.set(object, Paths.get(value, new String[0]));
        }
        else if (type == Pattern.class) {
            field.set(object, Pattern.compile(value));
        }
        else if (type == Duration.class) {
            field.set(object, Duration.parse(value));
        }
        else {
            field.setAccessible(oldAccess);
            ConfigTypeCaster.log.error("Unsupported type [{}] for field [{}]", (Object)field.getType().getName(), (Object)field.getName());
        }
        field.setAccessible(oldAccess);
    }

    public static <T> T cast(final Class type, final String value) throws IllegalAccessException {
        if (!isCastable(type)) {
            ConfigTypeCaster.log.error("Unsupported type [{}]", (Object)type.getName());
            return null;
        }
        if (type.isEnum()) {
            return  (T) Enum.valueOf(type, value);
        }
        if (type == Integer.class || type == Integer.TYPE) {
            return (T)Integer.class.cast(Integer.decode(value));
        }
        if (type == Short.class || type == Short.TYPE) {
            return (T)Short.class.cast(Short.decode(value));
        }
        if (type == Float.class || type == Float.TYPE) {
            return (T)Float.class.cast(Float.parseFloat(value));
        }
        if (type == Double.class || type == Double.TYPE) {
            return (T)Double.class.cast(Double.parseDouble(value));
        }
        if (type == Long.class || type == Long.TYPE) {
            return (T)Long.class.cast(Long.decode(value));
        }
        if (type == Boolean.class || type == Boolean.TYPE) {
            return (T)Boolean.class.cast(Boolean.parseBoolean(value));
        }
        if (type == String.class) {
            return (T)value;
        }
        if (type == Character.class || type == Character.TYPE) {
            return (T)Character.valueOf(value.charAt(0));
        }
        if (type == Byte.class || type == Byte.TYPE) {
            return (T)Byte.class.cast(Byte.decode(value));
        }
        if (type == AtomicInteger.class) {
            return (T)new AtomicInteger(Integer.decode(value));
        }
        if (type == AtomicBoolean.class) {
            return (T)new AtomicBoolean(Boolean.parseBoolean(value));
        }
        if (type == AtomicLong.class) {
            return (T)new AtomicLong(Long.decode(value));
        }
        if (type == BigInteger.class) {
            return (T)new BigInteger(value);
        }
        if (type == BigDecimal.class) {
            return (T)new BigDecimal(value);
        }
        if (type == Path.class) {
            return (T)Paths.get(value, new String[0]);
        }
        if (type == Pattern.class) {
            return (T)Pattern.compile(value);
        }
        if (type == Duration.class) {
            return (T)Duration.parse(value);
        }
        ConfigTypeCaster.log.error("Unsupported type [{}]", (Object)type.getName());
        return null;
    }

    public static boolean isCastable(final Class type) {
        if (type.isEnum()) {
            return true;
        }
        for (final Class t : ConfigTypeCaster._allowedTypes) {
            if (t == type) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCastable(final Object object) {
        return isCastable(object.getClass());
    }

    public static boolean isCastable(final Field field) {
        return isCastable(field.getType());
    }

    static {
        log = LoggerFactory.getLogger((Class)ConfigTypeCaster.class);
        _allowedTypes = new Class[] { Integer.class, Integer.TYPE, Short.class, Short.TYPE, Float.class, Float.TYPE, Double.class,
                Double.TYPE, Long.class, Long.TYPE, Boolean.class, Boolean.TYPE, String.class, Character.class, Character.TYPE,
                Byte.class, Byte.TYPE, AtomicInteger.class, AtomicBoolean.class, AtomicLong.class, BigInteger.class, BigDecimal.class, Path.class,
                Pattern.class, Duration.class };
    }
}
