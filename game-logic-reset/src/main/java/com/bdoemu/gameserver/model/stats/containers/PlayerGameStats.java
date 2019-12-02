package com.bdoemu.gameserver.model.stats.containers;

import com.bdoemu.gameserver.dataholders.PCData;
import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.stats.elements.BaseElement;
import com.bdoemu.gameserver.model.stats.enums.StatEnum;

/**
 * @ClassName PlayerGameStats
 * @Description   玩家游戏状态
 * @Author JiangBangMing
 * @Date 2019/7/8 21:41
 * VERSION 1.0
 */

public class PlayerGameStats extends GameStats<Player> {

    public PlayerGameStats(final Player owner) {
        super(owner);
    }

    @Override
    public BaseElement getBaseElementForStat(final StatEnum type) {
        return PCData.getInstance().getTemplate( this.owner.getClassType().getId(), this.owner.getLevel()).getGameStatsTemplate().getBaseElement(type);
    }
}
