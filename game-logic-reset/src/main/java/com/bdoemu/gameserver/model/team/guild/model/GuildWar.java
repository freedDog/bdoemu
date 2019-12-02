package com.bdoemu.gameserver.model.team.guild.model;

import com.bdoemu.commons.database.mongo.JSONable;
import com.bdoemu.gameserver.model.team.guild.Guild;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

/**
 * @ClassName GuildWar
 * @Description  工会战
 * @Author JiangBangMing
 * @Date 2019/7/10 17:27
 * VERSION 1.0
 */

public class GuildWar extends JSONable {

    private final long _objectId;
    private final int _registerTime;
    private int _kills;
    private int _deaths;
    private boolean _isWarStarter;

    public GuildWar(Guild dstGuild) {
        _objectId = dstGuild.getObjectId();
        _registerTime = (int) (System.currentTimeMillis() / 1000);
        _kills = 0;
        _deaths = 0;
    }

    public GuildWar(final BasicDBObject dbObject) {
        _objectId = dbObject.getLong("objectId");
        _registerTime = dbObject.getInt("registerDate");
        _isWarStarter = dbObject.getBoolean("warStarter", false);
        _kills = dbObject.getInt("kills");
        _deaths = dbObject.getInt("deaths");
    }

    @Override
    public long getObjectId() {
        return _objectId;
    }

    public int getRegisterTime() {
        return _registerTime;
    }

    public boolean isWarStarter() {
        return _isWarStarter;
    }

    public boolean canStop() {
        return System.currentTimeMillis() / 1000 > _registerTime + 3600;
    }

    public void addKill() {
        ++_kills;
    }

    public int getKills() {
        return _kills;
    }

    public void addDeath() {
        ++_deaths;
    }

    public int getDeaths() {
        return _deaths;
    }
    @Override
    public DBObject toDBObject() {
        final BasicDBObjectBuilder builder = new BasicDBObjectBuilder();
        builder.append("objectId", _objectId);
        builder.append("registerDate", _registerTime);
        builder.append("warStarter", _isWarStarter);
        builder.append("kills", _kills);
        builder.append("deaths", _deaths);
        return builder.get();
    }
}
