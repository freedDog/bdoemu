package com.bdoemu.gameserver.model.skills.buffs.enums;

/**
 * @ClassName ApplyBuffType
 * @Description  buff 应用类型
 * @Author JiangBangMing
 * @Date 2019/7/6 12:23
 * VERSION 1.0
 */
public enum ApplyBuffType {
    /**正常*/
    Normal,
    /***/
    ReApply,
    /**进入世界*/
    EnterWorld;

    public boolean isReApply() {
        return this == ApplyBuffType.ReApply;
    }

    public boolean isEnterWorld() {
        return this == ApplyBuffType.EnterWorld;
    }
}
