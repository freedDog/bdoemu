package com.bdoemu.gameserver.model.skills.templates;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName VehicleSkillT
 * @Description 车辆的技能
 * @Author JiangBangMing
 * @Date 2019/7/10 11:26
 * VERSION 1.0
 */

public class VehicleSkillT {
    private int skillId;
    private int maxExp;
    private int addExpForCheck;
    private int addMatingRate;
    private long basePrice;
    private long priceRate0;
    private long priceRate1;
    private long priceRate2;
    private boolean isLearnFromItem;

    public VehicleSkillT(final ResultSet rs) throws SQLException {
        this.skillId = rs.getInt("Index");
        this.maxExp = rs.getInt("MaxExp");
        this.basePrice = rs.getLong("BasePrice");
        this.priceRate0 = rs.getLong("priceRate_0");
        this.priceRate1 = rs.getLong("priceRate_1");
        this.priceRate2 = rs.getLong("priceRate_2");
        this.addExpForCheck = rs.getInt("AddExpForCheck");
        this.addMatingRate = rs.getInt("AddMatingRate");
        this.isLearnFromItem = (rs.getByte("IsLearnFromItem") == 1);
    }

    public boolean isLearnFromItem() {
        return this.isLearnFromItem;
    }

    public int getSkillId() {
        return this.skillId;
    }

    public int getMaxExp() {
        return this.maxExp;
    }

    public long getBasePrice() {
        return this.basePrice;
    }

    public int getAddExpForCheck() {
        return this.addExpForCheck;
    }
}
