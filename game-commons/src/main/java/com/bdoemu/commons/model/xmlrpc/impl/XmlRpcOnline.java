package com.bdoemu.commons.model.xmlrpc.impl;

import com.bdoemu.commons.model.xmlrpc.XmlRpcMessage;

/**
 * @ClassName XmlRpcOnline
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/24 20:09
 * VERSION 1.0
 */

public class XmlRpcOnline extends XmlRpcMessage {
    private int livePlayers;
    private int twinksPlayers;

    public int getLivePlayers() {
        return this.livePlayers;
    }

    public int getTwinksPlayers() {
        return this.twinksPlayers;
    }

    public void setLivePlayers(final int livePlayers) {
        this.livePlayers = livePlayers;
    }

    public void setTwinksPlayers(final int twinksPlayers) {
        this.twinksPlayers = twinksPlayers;
    }

    @Override
    public String toString() {
        return "XmlRpcOnline(livePlayers=" + this.getLivePlayers() + ", twinksPlayers=" + this.getTwinksPlayers() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof XmlRpcOnline)) {
            return false;
        }
        final XmlRpcOnline other = (XmlRpcOnline)o;
        return other.canEqual(this) && this.getLivePlayers() == other.getLivePlayers() && this.getTwinksPlayers() == other.getTwinksPlayers();
    }

    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof XmlRpcOnline;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getLivePlayers();
        result = result * 59 + this.getTwinksPlayers();
        return result;
    }
}
