package com.bdoemu.commons.config.annotation;

import org.atteo.classindex.IndexAnnotated;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @ClassName ConfigFile
 * @Description 配置文件
 * @Author JiangBangMing
 * @Date 2019/6/15 17:18
 * VERSION 1.0
 */

@IndexAnnotated
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigFile {
    String name();

    String[] loadForPackages() default {};
}
