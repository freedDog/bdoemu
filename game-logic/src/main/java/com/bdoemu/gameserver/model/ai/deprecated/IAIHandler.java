// 
// Decompiled by Procyon v0.5.30
// 

package com.bdoemu.gameserver.model.ai.deprecated;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
//@Deprecated
public @interface IAIHandler {
    String hash();
}
