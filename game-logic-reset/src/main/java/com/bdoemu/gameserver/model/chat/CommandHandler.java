package com.bdoemu.gameserver.model.chat;

import com.bdoemu.commons.model.enums.EAccessLevel;
import org.atteo.classindex.IndexAnnotated;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@IndexAnnotated
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface CommandHandler {

    String prefix();

    EAccessLevel accessLevel() default EAccessLevel.USER;
}
