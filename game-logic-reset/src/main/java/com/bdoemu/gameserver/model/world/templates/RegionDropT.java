package com.bdoemu.gameserver.model.world.templates;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName RegionDropT
 * @Description  地图掉落
 * @Author JiangBangMing
 * @Date 2019/7/11 12:10
 * VERSION 1.0
 */

public class RegionDropT {
    private Color color;
    private int dropId;

    public RegionDropT(final ResultSet rs) throws SQLException {
        this.color = new Color(rs.getInt("R"), rs.getInt("G"), rs.getInt("B"));
        this.dropId = rs.getInt("DropID");
    }

    public Color getColor() {
        return this.color;
    }

    public int getDropId() {
        return this.dropId;
    }
}
