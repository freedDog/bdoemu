package com.bdoemu.gameserver.model.team.guild.templates;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName GuildSkillPointEXPT
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 17:24
 * VERSION 1.0
 */

public class GuildSkillPointEXPT {

    private int level;
    private int requireExp;
    private int requireSkillExpLimit;
    private int aquiredPoint;

    public GuildSkillPointEXPT(final ResultSet rs) throws SQLException {
        this.level = rs.getInt("Index");
        this.requireExp = rs.getInt("RequireEXP");
        this.requireSkillExpLimit = rs.getInt("RequireSkillExpLimit");
        this.aquiredPoint = rs.getInt("AquiredPoint");
    }

    public int getLevel() {
        return this.level;
    }

    public int getRequireExp() {
        return this.requireExp;
    }

    public int getRequireSkillExpLimit() {
        return this.requireSkillExpLimit;
    }

    public int getAquiredPoint() {
        return this.aquiredPoint;
    }
}
