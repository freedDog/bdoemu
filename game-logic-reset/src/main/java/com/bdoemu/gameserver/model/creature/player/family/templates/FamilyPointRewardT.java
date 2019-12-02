package com.bdoemu.gameserver.model.creature.player.family.templates;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName FamilyPointRewardT
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 20:18
 * VERSION 1.0
 */

public class FamilyPointRewardT {

    private int battlePoint;
    private int lifePoint;
    private int etcPoint;

    public FamilyPointRewardT(final ResultSet rs) throws SQLException {
        this.battlePoint = rs.getInt("BattlePoint");
        this.lifePoint = rs.getInt("LifePoint");
        this.etcPoint = rs.getInt("EtcPoint");
    }

    public int getLifePoint() {
        return this.lifePoint;
    }

    public int getEtcPoint() {
        return this.etcPoint;
    }

    public int getBattlePoint() {
        return this.battlePoint;
    }
}
