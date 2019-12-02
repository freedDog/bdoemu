package com.bdoemu.gameserver.model.skills.enums;

/**
 * @ClassName ESkillOwnerType
 * @Description 技能持有者
 * @Author JiangBangMing
 * @Date 2019/7/10 11:14
 * VERSION 1.0
 */
public enum ESkillOwnerType {

    /**角色*/
    Character,
    /**工会*/
    Guild;

    public boolean isCharacter() {
        return this == ESkillOwnerType.Character;
    }

    public boolean isGuild() {
        return this == ESkillOwnerType.Guild;
    }
}
