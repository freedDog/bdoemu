package com.bdoemu.commons.database.mongo;


import org.atteo.classindex.IndexAnnotated;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IndexAnnotated
@Retention(RetentionPolicy.RUNTIME)
public @interface DatabaseCollection {
}
