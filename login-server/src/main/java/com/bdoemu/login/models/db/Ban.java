package com.bdoemu.login.models.db;

import com.bdoemu.commons.database.mongo.JSONable;
import com.bdoemu.commons.model.enums.EBanType;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

/**
 * @ClassName Ban
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/26 19:37
 * VERSION 1.0
 */

public class Ban extends JSONable {
    private long objectId;
    private int serverId;
    private EBanType banType;
    private String playerId;
    private String accountId;
    private String playerName;
    private String ip;
    private String hwid;
    private String banInitiator;
    private String comment;
    private long banStart;
    private long banEnd;

    public Ban() {
    }

    public Ban(final BasicDBObject basicDBObject) {
        this.objectId = basicDBObject.getLong("");
        this.serverId = basicDBObject.getInt("");
        this.banType = EBanType.valueOf(basicDBObject.getString(""));
        this.playerId = basicDBObject.getString("");
        this.accountId = basicDBObject.getString("");
        this.playerName = basicDBObject.getString("");
        this.ip = basicDBObject.getString("");
        this.banInitiator = basicDBObject.getString("");
        this.comment = basicDBObject.getString("");
        this.banStart = basicDBObject.getLong("");
        this.banEnd = basicDBObject.getLong("");
    }

    @Override
    public DBObject toDBObject() {
        final BasicDBObjectBuilder basicDBObjectBuilder = new BasicDBObjectBuilder();
        basicDBObjectBuilder.append("", this.objectId);
        basicDBObjectBuilder.append("", this.serverId);
        basicDBObjectBuilder.append("", this.banType.toString());
        basicDBObjectBuilder.append("", this.playerId);
        basicDBObjectBuilder.append("", this.accountId);
        basicDBObjectBuilder.append("", this.playerName);
        basicDBObjectBuilder.append("", this.ip);
        basicDBObjectBuilder.append("", this.banInitiator);
        basicDBObjectBuilder.append("", this.comment);
        basicDBObjectBuilder.append("", this.banStart);
        basicDBObjectBuilder.append("", this.banEnd);
        return basicDBObjectBuilder.get();
    }

    @Override
    public long getObjectId() {
        return this.objectId;
    }

    public int getServerId() {
        return this.serverId;
    }

    public EBanType getBanType() {
        return this.banType;
    }

    public String getPlayerId() {
        return this.playerId;
    }

    public String getAccountId() {
        return this.accountId;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public String getIp() {
        return this.ip;
    }

    public String getHwid() {
        return this.hwid;
    }

    public String getBanInitiator() {
        return this.banInitiator;
    }

    public String getComment() {
        return this.comment;
    }

    public long getBanStart() {
        return this.banStart;
    }

    public long getBanEnd() {
        return this.banEnd;
    }

    public void setObjectId(final long objectId) {
        this.objectId = objectId;
    }

    public void setServerId(final int serverId) {
        this.serverId = serverId;
    }

    public void setBanType(final EBanType banType) {
        this.banType = banType;
    }

    public void setPlayerId(final String playerId) {
        this.playerId = playerId;
    }

    public void setAccountId(final String accountId) {
        this.accountId = accountId;
    }

    public void setPlayerName(final String playerName) {
        this.playerName = playerName;
    }

    public void setIp(final String ip) {
        this.ip = ip;
    }

    public void setHwid(final String hwid) {
        this.hwid = hwid;
    }

    public void setBanInitiator(final String banInitiator) {
        this.banInitiator = banInitiator;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public void setBanStart(final long banStart) {
        this.banStart = banStart;
    }

    public void setBanEnd(final long banEnd) {
        this.banEnd = banEnd;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Ban)) {
            return false;
        }
        final Ban ban = (Ban)o;
        if (!ban.canEqual(this)) {
            return false;
        }
        if (this.getObjectId() != ban.getObjectId()) {
            return false;
        }
        if (this.getServerId() != ban.getServerId()) {
            return false;
        }
        final String comment = this.getComment();
        final String comment2 = ban.getComment();
        if (comment == null) {
            if (comment2 == null) {
                return this.getBanStart() == ban.getBanStart() && this.getBanEnd() == ban.getBanEnd();
            }
        }
        else if (comment.equals(comment2)) {
            return this.getBanStart() == ban.getBanStart() && this.getBanEnd() == ban.getBanEnd();
        }
        return false;
    }

    protected boolean canEqual(final Object o) {
        return o instanceof Ban;
    }
    @Override
    public int hashCode() {
        final int n = 1;
        final long objectId = this.getObjectId();
        final int n2 = (n * 59 + (int)(objectId >>> 32 ^ objectId)) * 59 + this.getServerId();
        final EBanType banType = this.getBanType();
        final int n3 = n2 * 59 + ((banType == null) ? 43 : banType.hashCode());
        final String playerId = this.getPlayerId();
        final int n4 = n3 * 59 + ((playerId == null) ? 43 : playerId.hashCode());
        final String accountId = this.getAccountId();
        final int n5 = n4 * 59 + ((accountId == null) ? 43 : accountId.hashCode());
        final String playerName = this.getPlayerName();
        final int n6 = n5 * 59 + ((playerName == null) ? 43 : playerName.hashCode());
        final String ip = this.getIp();
        final int n7 = n6 * 59 + ((ip == null) ? 43 : ip.hashCode());
        final String hwid = this.getHwid();
        final int n8 = n7 * 59 + ((hwid == null) ? 43 : hwid.hashCode());
        final String banInitiator = this.getBanInitiator();
        final int n9 = n8 * 59 + ((banInitiator == null) ? 43 : banInitiator.hashCode());
        final String comment = this.getComment();
        final int n10 = n9 * 59 + ((comment == null) ? 43 : comment.hashCode());
        final long banStart = this.getBanStart();
        final int n11 = n10 * 59 + (int)(banStart >>> 32 ^ banStart);
        final long banEnd = this.getBanEnd();
        return n11 * 59 + (int)(banEnd >>> 32 ^ banEnd);
    }
}
