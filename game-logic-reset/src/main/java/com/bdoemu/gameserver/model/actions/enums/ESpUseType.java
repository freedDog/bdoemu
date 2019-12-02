package com.bdoemu.gameserver.model.actions.enums;

/**
 * @ClassName ESpUseType
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/11 12:07
 * VERSION 1.0
 */
public enum ESpUseType {
    Once,
    Continue,
    Recover,
    Stop,
    Reset,
    None;

    public int getId() {
        return this.ordinal();
    }

    public boolean isContinue() {
        return this == ESpUseType.Continue;
    }
}
