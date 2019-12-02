package com.bdoemu.commons.xml;

import org.atteo.classindex.IndexAnnotated;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 数据持有者根
 */

@IndexAnnotated
@Retention(RetentionPolicy.RUNTIME)
public @interface XmlDataHolderRoot {
    String value();
}
