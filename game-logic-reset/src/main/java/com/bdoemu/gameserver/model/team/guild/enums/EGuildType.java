package com.bdoemu.gameserver.model.team.guild.enums;

/**
 * @ClassName EGuildType
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 17:29
 * VERSION 1.0
 */

public enum EGuildType {

    Clan,
    Guild;

    public boolean isClan() {
        return this == EGuildType.Clan;
    }

    public boolean isGuild() {
        return this == EGuildType.Guild;
    }
}
