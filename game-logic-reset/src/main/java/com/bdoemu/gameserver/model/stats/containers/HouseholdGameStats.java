package com.bdoemu.gameserver.model.stats.containers;

import com.bdoemu.gameserver.model.houses.HouseHold;

/**
 * @ClassName HouseholdGameStats
 * @Description 房子
 * @Author JiangBangMing
 * @Date 2019/7/10 15:39
 * VERSION 1.0
 */

public class HouseholdGameStats extends GameStats<HouseHold> {
    public HouseholdGameStats(final HouseHold owner) {
        super(owner);
    }
}
