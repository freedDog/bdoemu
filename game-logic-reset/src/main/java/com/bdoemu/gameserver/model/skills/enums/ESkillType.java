package com.bdoemu.gameserver.model.skills.enums;

/**
 * @ClassName ESkillType
 * @Description   技能类型
 * @Author JiangBangMing
 * @Date 2019/7/10 11:16
 * VERSION 1.0
 */
public enum ESkillType {

    /**装备技能*/
    Equip,
    /**主动技能*/
    Active,
    /**被动技能*/
    Passive;

    public boolean isActive() {
        return this == ESkillType.Active;
    }

    public boolean isPassive() {
        return this == ESkillType.Passive;
    }

    public boolean isEquip() {
        return this == ESkillType.Equip;
    }

}
