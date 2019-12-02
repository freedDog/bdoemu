package com.bdoemu.gameserver.model.manufactures.templates;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName ManufactureConditionT
 * @Description 制造条件模板
 * @Author JiangBangMing
 * @Date 2019/7/11 14:08
 * VERSION 1.0
 */

public class ManufactureConditionT {
    private String actionName;

    public ManufactureConditionT(final ResultSet rs) throws SQLException {
        this.actionName = rs.getString("ActionName");
    }

    public String getActionName() {
        return this.actionName;
    }
}
