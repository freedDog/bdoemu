package com.bdoemu.gameserver.model.creature.player.exploration.templates;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName PlantExchangeGroupT
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 19:47
 * VERSION 1.0
 */

public class PlantExchangeGroupT {

    private int exchangeGroupKey;

    public PlantExchangeGroupT(final ResultSet rs) throws SQLException {
        this.exchangeGroupKey = rs.getInt("ExchangeKey_0");
    }

    public int getExchangeGroupKey() {
        return this.exchangeGroupKey;
    }
}
