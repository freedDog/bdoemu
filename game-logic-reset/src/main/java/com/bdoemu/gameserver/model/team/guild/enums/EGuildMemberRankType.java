package com.bdoemu.gameserver.model.team.guild.enums;

/**
 * @ClassName EGuildMemberRankType
 * @Description  工会成员排名类型
 * @Author JiangBangMing
 * @Date 2019/7/10 11:33
 * VERSION 1.0
 */

public enum EGuildMemberRankType {
    Master,
    Officer,
    General,
    Quartermaster,
    None;

    public boolean isMaster() {
        return this == EGuildMemberRankType.Master;
    }

    public boolean isOfficer() {
        return this == EGuildMemberRankType.Officer;
    }
}
