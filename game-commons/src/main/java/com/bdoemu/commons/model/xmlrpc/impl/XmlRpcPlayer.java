package com.bdoemu.commons.model.xmlrpc.impl;

import com.bdoemu.commons.model.xmlrpc.XmlRpcMessage;

/**
 * @ClassName XmlRpcPlayer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/24 20:07
 * VERSION 1.0
 */

public class XmlRpcPlayer extends XmlRpcMessage {
    private String name;
    private XmlRpcPlayerClassId classId;
    private XmlRpcPlayerZodiac zodiac;
    private int level;
    private long creationDate;
    private int tendency;
    private int wp;
    private int maxWp;
    private long objectId;
    private long guildId;

    public String getName() {
        return this.name;
    }

    public XmlRpcPlayerClassId getClassId() {
        return this.classId;
    }

    public XmlRpcPlayerZodiac getZodiac() {
        return this.zodiac;
    }

    public int getLevel() {
        return this.level;
    }

    public long getCreationDate() {
        return this.creationDate;
    }

    public int getTendency() {
        return this.tendency;
    }

    public int getWp() {
        return this.wp;
    }

    public int getMaxWp() {
        return this.maxWp;
    }

    public long getObjectId() {
        return this.objectId;
    }

    public long getGuildId() {
        return this.guildId;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setClassId(final XmlRpcPlayerClassId classId) {
        this.classId = classId;
    }

    public void setZodiac(final XmlRpcPlayerZodiac zodiac) {
        this.zodiac = zodiac;
    }

    public void setLevel(final int level) {
        this.level = level;
    }

    public void setCreationDate(final long creationDate) {
        this.creationDate = creationDate;
    }

    public void setTendency(final int tendency) {
        this.tendency = tendency;
    }

    public void setWp(final int wp) {
        this.wp = wp;
    }

    public void setMaxWp(final int maxWp) {
        this.maxWp = maxWp;
    }

    public void setObjectId(final long objectId) {
        this.objectId = objectId;
    }

    public void setGuildId(final long guildId) {
        this.guildId = guildId;
    }

    @Override
    public String toString() {
        return "XmlRpcPlayer(name=" + this.getName() + ", classId=" + this.getClassId() + ", zodiac=" + this.getZodiac() + ", level=" + this.getLevel() + ", creationDate=" + this.getCreationDate() + ", tendency=" + this.getTendency() + ", wp=" + this.getWp() + ", maxWp=" + this.getMaxWp() + ", objectId=" + this.getObjectId() + ", guildId=" + this.getGuildId() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof XmlRpcPlayer)) {
            return false;
        }
        final XmlRpcPlayer other = (XmlRpcPlayer)o;
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
        final Object this$classId = this.getClassId();
        final Object other$classId = other.getClassId();
        Label_0102: {
            if (this$classId == null) {
                if (other$classId == null) {
                    break Label_0102;
                }
            }
            else if (this$classId.equals(other$classId)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$zodiac = this.getZodiac();
        final Object other$zodiac = other.getZodiac();
        if (this$zodiac == null) {
            if (other$zodiac == null) {
                return this.getLevel() == other.getLevel() && this.getCreationDate() == other.getCreationDate() && this.getTendency() == other.getTendency() && this.getWp() == other.getWp() && this.getMaxWp() == other.getMaxWp() && this.getObjectId() == other.getObjectId() && this.getGuildId() == other.getGuildId();
            }
        }
        else if (this$zodiac.equals(other$zodiac)) {
            return this.getLevel() == other.getLevel() && this.getCreationDate() == other.getCreationDate() && this.getTendency() == other.getTendency() && this.getWp() == other.getWp() && this.getMaxWp() == other.getMaxWp() && this.getObjectId() == other.getObjectId() && this.getGuildId() == other.getGuildId();
        }
        return false;
    }

    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof XmlRpcPlayer;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        final Object $classId = this.getClassId();
        result = result * 59 + (($classId == null) ? 43 : $classId.hashCode());
        final Object $zodiac = this.getZodiac();
        result = result * 59 + (($zodiac == null) ? 43 : $zodiac.hashCode());
        result = result * 59 + this.getLevel();
        final long $creationDate = this.getCreationDate();
        result = result * 59 + (int)($creationDate >>> 32 ^ $creationDate);
        result = result * 59 + this.getTendency();
        result = result * 59 + this.getWp();
        result = result * 59 + this.getMaxWp();
        final long $objectId = this.getObjectId();
        result = result * 59 + (int)($objectId >>> 32 ^ $objectId);
        final long $guildId = this.getGuildId();
        result = result * 59 + (int)($guildId >>> 32 ^ $guildId);
        return result;
    }
}
