package com.bdoemu.commons.reload;

import org.atteo.classindex.IndexAnnotated;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 重载
 */
@IndexAnnotated
@Retention(RetentionPolicy.RUNTIME)
public @interface Reloadable {

    String name();

    String group();
}
