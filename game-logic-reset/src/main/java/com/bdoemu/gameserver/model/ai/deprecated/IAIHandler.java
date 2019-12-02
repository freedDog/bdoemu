package com.bdoemu.gameserver.model.ai.deprecated;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Ai 处理
 */

@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface IAIHandler {
    String hash();
}
