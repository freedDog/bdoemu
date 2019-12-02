package com.bdoemu.gameserver.model.houses.templates;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName HouseTransferT
 * @Description  房子转让模板
 * @Author JiangBangMing
 * @Date 2019/7/9 18:10
 * VERSION 1.0
 */

public class HouseTransferT {

    private int transferKey;
    private int level;
    private int time;
    private Integer itemKey;
    private Long count;

    public HouseTransferT(final ResultSet rs) throws SQLException {
        this.transferKey = rs.getInt("TransferKey");
        this.level = rs.getInt("Level");
        this.time = rs.getInt("Time");
        if (rs.getString("ItemKey1") != null) {
            this.itemKey = rs.getInt("ItemKey1");
            this.count = rs.getLong("ItemCount1");
        }
    }

    public Integer getItemKey() {
        return this.itemKey;
    }

    public Long getCount() {
        return this.count;
    }

    public int getTransferKey() {
        return this.transferKey;
    }

    public int getTime() {
        return this.time;
    }

    public int getLevel() {
        return this.level;
    }
}
