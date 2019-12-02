package com.bdoemu.gameserver.model.creature.player.exploration.templates;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName NodeEXPT
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 19:51
 * VERSION 1.0
 */

public class NodeEXPT {
    private int level;
    private int requireExp;
    private int type;

    public NodeEXPT(final ResultSet rs) throws SQLException {
        this.type = rs.getInt("Type");
        this.level = rs.getInt("Level");
        this.requireExp = rs.getInt("RequireEXP");
    }

    public int getRequireExp() {
        return this.requireExp;
    }

    public int getLevel() {
        return this.level;
    }
}
