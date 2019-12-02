package com.bdoemu.commons.model.xmlrpc.impl;

import com.bdoemu.commons.model.xmlrpc.XmlRpcMessage;

import java.util.ArrayList;

/**
 * @ClassName XmlRpcGuild
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/24 20:01
 * VERSION 1.0
 */

public class XmlRpcGuild extends XmlRpcMessage {
    private String name;
    private int level;
    private long exp;
    private int tendency;
    private long objectId;
    private long createdDate;
    private ArrayList<XmlRpcGuildMember> guildMembers;

    public String getName() {
        return this.name;
    }

    public int getLevel() {
        return this.level;
    }

    public long getExp() {
        return this.exp;
    }

    public int getTendency() {
        return this.tendency;
    }

    public long getObjectId() {
        return this.objectId;
    }

    public long getCreatedDate() {
        return this.createdDate;
    }

    public ArrayList<XmlRpcGuildMember> getGuildMembers() {
        return this.guildMembers;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setLevel(final int level) {
        this.level = level;
    }

    public void setExp(final long exp) {
        this.exp = exp;
    }

    public void setTendency(final int tendency) {
        this.tendency = tendency;
    }

    public void setObjectId(final long objectId) {
        this.objectId = objectId;
    }

    public void setCreatedDate(final long createdDate) {
        this.createdDate = createdDate;
    }

    public void setGuildMembers(final ArrayList<XmlRpcGuildMember> guildMembers) {
        this.guildMembers = guildMembers;
    }

    @Override
    public String toString() {
        return "XmlRpcGuild(name=" + this.getName() + ", level=" + this.getLevel() + ", exp=" + this.getExp() + ", tendency=" + this.getTendency() + ", objectId=" + this.getObjectId() + ", createdDate=" + this.getCreatedDate() + ", guildMembers=" + this.getGuildMembers() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof XmlRpcGuild)) {
            return false;
        }
        final XmlRpcGuild other = (XmlRpcGuild)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        Label_0065: {
            if (this$name == null) {
                if (other$name == null) {
                    break Label_0065;
                }
            }
            else if (this$name.equals(other$name)) {
                break Label_0065;
            }
            return false;
        }
        if (this.getLevel() != other.getLevel()) {
            return false;
        }
        if (this.getExp() != other.getExp()) {
            return false;
        }
        if (this.getTendency() != other.getTendency()) {
            return false;
        }
        if (this.getObjectId() != other.getObjectId()) {
            return false;
        }
        if (this.getCreatedDate() != other.getCreatedDate()) {
            return false;
        }
        final Object this$guildMembers = this.getGuildMembers();
        final Object other$guildMembers = other.getGuildMembers();
        if (this$guildMembers == null) {
            if (other$guildMembers == null) {
                return true;
            }
        }
        else if (this$guildMembers.equals(other$guildMembers)) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof XmlRpcGuild;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        result = result * 59 + this.getLevel();
        final long $exp = this.getExp();
        result = result * 59 + (int)($exp >>> 32 ^ $exp);
        result = result * 59 + this.getTendency();
        final long $objectId = this.getObjectId();
        result = result * 59 + (int)($objectId >>> 32 ^ $objectId);
        final long $createdDate = this.getCreatedDate();
        result = result * 59 + (int)($createdDate >>> 32 ^ $createdDate);
        final Object $guildMembers = this.getGuildMembers();
        result = result * 59 + (($guildMembers == null) ? 43 : $guildMembers.hashCode());
        return result;
    }
}
