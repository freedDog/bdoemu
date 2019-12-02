// 
// Decompiled by Procyon v0.5.30
// 

package com.bdoemu.gameserver.model.skills.enums;

/**
 * 技能类型
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
