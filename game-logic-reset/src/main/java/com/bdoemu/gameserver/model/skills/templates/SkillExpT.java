package com.bdoemu.gameserver.model.skills.templates;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName SkillExpT
 * @Description  技能经验
 * @Author JiangBangMing
 * @Date 2019/7/10 11:22
 * VERSION 1.0
 */

public class SkillExpT {

    private final int skillExpLevel;
    private final int requireExp;
    private final int requireSkillExpLimit;
    private final int aquiredPoint;
    private final int questRequireExpLimit;

    public SkillExpT(final ResultSet rs) throws SQLException {
        this.skillExpLevel = rs.getInt("Index");
        this.requireExp = rs.getInt("RequireEXP");
        this.requireSkillExpLimit = rs.getInt("RequireSkillExpLimit");
        this.aquiredPoint = rs.getInt("AquiredPoint");
        this.questRequireExpLimit = rs.getInt("QuestRequireExpLimit");
    }

    public int getAquiredPoint() {
        return this.aquiredPoint;
    }

    public int getSkillExpLevel() {
        return this.skillExpLevel;
    }

    public int getQuestRequireExpLimit() {
        return this.questRequireExpLimit;
    }

    public int getRequireExp() {
        return this.requireExp;
    }

    public int getRequireSkillExpLimit() {
        return this.requireSkillExpLimit;
    }
}
