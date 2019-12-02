package com.bdoemu.commons.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName ConfigProperty
 * @Description 配置属性
 * @Author JiangBangMing
 * @Date 2019/6/15 17:22
 * VERSION 1.0
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigProperty {

    String name();

    String value() default "";

    String[] values() default {""};

    String splitter() default ",";

    boolean isMap() default false;
}
