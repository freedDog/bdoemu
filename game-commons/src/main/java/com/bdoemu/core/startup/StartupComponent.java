package com.bdoemu.core.startup;


import org.atteo.classindex.IndexAnnotated;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启动组件
 */
@IndexAnnotated
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface StartupComponent {

    String value();

    Class<?>[] dependency() default {};
}
