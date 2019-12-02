package com.bdoemu.gameserver.model.stats.containers;

import com.bdoemu.gameserver.model.creature.player.Player;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

/**
 * @ClassName PlayerLifeStats
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/9 20:19
 * VERSION 1.0
 */

public class PlayerLifeStats extends LifeStats<Player>{
    public PlayerLifeStats(final Player owner) {
        super(owner, owner.getGameStats().getHp().getIntMaxValue(), owner.getGameStats().getMp().getIntMaxValue());
        owner.getGameStats().getStamina().fill();
    }

    public PlayerLifeStats(final Player player, final BasicDBObject lifeStats) {
        super(player, lifeStats.getDouble("hp"), lifeStats.getDouble("mp"));
        this.owner.getGameStats().getStamina().fill();
        this.owner.getGameStats().getStunGauge().fill();
        this.owner.getGameStats().getHp().startHpRegenTask();
        this.owner.getGameStats().getMp().startMpRegenTask();
    }

    @Override
    public DBObject toDBObject() {
        final BasicDBObjectBuilder builder = new BasicDBObjectBuilder();
        builder.append("hp", this.owner.getGameStats().getHp().getValue());
        builder.append("mp", this.owner.getGameStats().getMp().getValue());
        return builder.get();
    }
}
