

package com.bdoemu.gameserver.model.skills.enums;

/**
 * 技能持有者类型
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
