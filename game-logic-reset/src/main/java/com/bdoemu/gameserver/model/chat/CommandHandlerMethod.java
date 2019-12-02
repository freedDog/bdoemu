package com.bdoemu.gameserver.model.chat;

import com.bdoemu.commons.model.enums.EAccessLevel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface CommandHandlerMethod {

    EAccessLevel accessLevel() default EAccessLevel.BANNED;
}
