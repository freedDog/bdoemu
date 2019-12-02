package com.bdoemu.gameserver.model.actions.enums;

/**
 * @ClassName EBattleAimedActionType
 * @Description 战斗定向动作类型
 * @Author JiangBangMing
 * @Date 2019/7/11 14:41
 * VERSION 1.0
 */
public enum EBattleAimedActionType {
    Normal,
    Crouch,
    Creep;

    public static EBattleAimedActionType valueof(final int id) {
        if (id < 0 || id > values().length - 1) {
            throw new IllegalArgumentException("Found unknown ETargetBattleAimedActionType with id= " + id);
        }
        return values()[id];
    }
}
