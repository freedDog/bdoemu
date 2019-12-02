package com.bdoemu.gameserver.model.skills.enums;

/**
 * @ClassName ESkillAwakeningType
 * @Description  技能觉醒
 * @Author JiangBangMing
 * @Date 2019/7/10 11:16
 * VERSION 1.0
 */
public enum ESkillAwakeningType {
    Normal,
    /**觉醒的武器*/
    AwakeningWeapon;

    public boolean isNormal() {
        return this == ESkillAwakeningType.Normal;
    }

    public boolean isAwakeningWeapon() {
        return this == ESkillAwakeningType.AwakeningWeapon;
    }
}
