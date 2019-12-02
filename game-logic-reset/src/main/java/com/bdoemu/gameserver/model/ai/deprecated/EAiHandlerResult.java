package com.bdoemu.gameserver.model.ai.deprecated;

/**
 * @ClassName EAiHandlerResult
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/12 10:30
 * VERSION 1.0
 */

public enum  EAiHandlerResult {
    /***/
    NONE,
    CHANGE_STATE,
    BYPASS;

    public boolean isBypass() {
        return this == EAiHandlerResult.BYPASS;
    }

    public boolean isChangeState() {
        return this == EAiHandlerResult.CHANGE_STATE;
    }

    public boolean isNone() {
        return this == EAiHandlerResult.NONE;
    }
}
