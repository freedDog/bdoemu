package com.bdoemu.gameserver.model.creature.npc.card.templates;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName ZodiacSignT
 * @Description  黄道十二宫模板
 * @Author JiangBangMing
 * @Date 2019/7/10 10:43
 * VERSION 1.0
 */

public class ZodiacSignT {

    private int zodiacSignKey;
    private Integer[] steps;

    public ZodiacSignT(final ResultSet rs) throws SQLException {
        this.steps = new Integer[20];
        this.zodiacSignKey = rs.getInt("ZodiacSignIndexKey");
        for (int index = 0; index < 20; ++index) {
            if (rs.getString("Index" + index) != null) {
                this.steps[index] = rs.getInt("Index" + index);
            }
        }
    }

    public Integer[] getSteps() {
        return this.steps;
    }

    public int getZodiacSignKey() {
        return this.zodiacSignKey;
    }
}
