package com.bdoemu.gameserver.model.items.templates;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName DyeingItemT
 * @Description  染色道具模板
 * @Author JiangBangMing
 * @Date 2019/7/9 17:12
 * VERSION 1.0
 */

public class DyeingItemT {
    private int itemId;
    private int paletteType;
    private int paletteIndex;

    public DyeingItemT(final ResultSet rs) throws SQLException {
        this.itemId = rs.getInt("ItemKey");
        this.paletteType = rs.getInt("PaletteType");
        this.paletteIndex = rs.getInt("PaletteIndex");
    }

    public int getItemId() {
        return this.itemId;
    }

    public int getPaletteType() {
        return this.paletteType;
    }

    public int getPaletteIndex() {
        return this.paletteIndex;
    }
}
