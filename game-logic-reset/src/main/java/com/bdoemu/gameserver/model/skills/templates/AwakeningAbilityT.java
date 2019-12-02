package com.bdoemu.gameserver.model.skills.templates;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName AwakeningAbilityT
 * @Description  觉醒的能力
 * @Author JiangBangMing
 * @Date 2019/7/10 11:20
 * VERSION 1.0
 */

public class AwakeningAbilityT {
    private int abilityId;

    public AwakeningAbilityT(final ResultSet rs) throws SQLException {
        this.abilityId = rs.getInt("Index");
    }

    public int getAbilityId() {
        return this.abilityId;
    }
}
