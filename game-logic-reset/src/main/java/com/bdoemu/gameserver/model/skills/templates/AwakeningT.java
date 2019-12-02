package com.bdoemu.gameserver.model.skills.templates;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName AwakeningT
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 11:20
 * VERSION 1.0
 */

public class AwakeningT {

    private int skillId;
    private List<AwakeningAbilityT> abilityList;

    public AwakeningT(final ResultSet rs, final List<AwakeningAbilityT> abilityList) throws SQLException {
        this.skillId = rs.getInt("SkillNo");
        this.abilityList = abilityList;
    }

    public List<AwakeningAbilityT> getAbilityList() {
        return this.abilityList;
    }

    public int getSkillId() {
        return this.skillId;
    }
}
