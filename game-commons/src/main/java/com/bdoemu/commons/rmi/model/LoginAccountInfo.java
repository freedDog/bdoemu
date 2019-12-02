package com.bdoemu.commons.rmi.model;

import com.bdoemu.commons.model.enums.EAccessLevel;
import com.mongodb.BasicDBObject;

import java.io.Serializable;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName LoginAccountInfo
 * @Description 登录账号信息
 * @Author JiangBangMing
 * @Date 2019/6/21 21:21
 * VERSION 1.0
 */

public class LoginAccountInfo implements Serializable {
    private long accountId;
    private int cookie;
    private String family;
    private EAccessLevel accessLevel;
    private long cash;
    private int characterSlots;

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }
    public void setCookie(int cookie) {
        this.cookie = cookie;
    }
    public void setFamily(String family) {
        this.family = family;
    }
    public void setAccessLevel(EAccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }
    public void setCash(long cash) {
        this.cash = cash;
    }
    public void setCharacterSlots(int characterSlots) {
        this.characterSlots = characterSlots;
    }
    public void setMacroses(Macros[] macroses) {
        this.macroses = macroses;
    }
    public void setSaveGameOptions(String saveGameOptions) {
        this.saveGameOptions = saveGameOptions;
    }
    public void setUiData(String uiData) {
        this.uiData = uiData;
    }
    public void setPlayers(ConcurrentHashMap<Long, BasicDBObject> players) {
        this.players = players;
    }

    public boolean equals(Object o) {
        if (o == this){
            return true;
        }
        if (!(o instanceof LoginAccountInfo)){
            return false;
        }
        LoginAccountInfo other = (LoginAccountInfo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (getAccountId() != other.getAccountId()) {
            return false;
        }
        if (getCookie() != other.getCookie()){
            return false;
        }
        Object this$family = getFamily(), other$family = other.getFamily();
        if ((this$family == null) ? (other$family != null) : !this$family.equals(other$family)) {
            return false;
        }
        Object this$accessLevel = getAccessLevel(), other$accessLevel = other.getAccessLevel();
        if ((this$accessLevel == null) ? (other$accessLevel != null) : !this$accessLevel.equals(other$accessLevel)) {
            return false;
        }
        if (getCash() != other.getCash()) {
            return false;
        }
        if (getCharacterSlots() != other.getCharacterSlots()) {
            return false;
        }
        if (!Arrays.deepEquals(getMacroses(), other.getMacroses())) {
            return false;
        }
        Object this$saveGameOptions = getSaveGameOptions(), other$saveGameOptions = other.getSaveGameOptions();
        if ((this$saveGameOptions == null) ? (other$saveGameOptions != null) : !this$saveGameOptions.equals(other$saveGameOptions)) {
            return false;
        }
        Object this$uiData = getUiData(), other$uiData = other.getUiData();
        if ((this$uiData == null) ? (other$uiData != null) : !this$uiData.equals(other$uiData)) {
            return false;
        }
        Object this$players = getPlayers(), other$players = other.getPlayers();
        return !((this$players == null) ? (other$players != null) : !this$players.equals(other$players));
    }
    protected boolean canEqual(Object other) {
        return other instanceof LoginAccountInfo;
    }
    @Override
    public int hashCode() {
        int PRIME = 59,result = 1;
        long $accountId = getAccountId();
        result = result * 59 + (int)($accountId >>> 32 ^ $accountId);
        result = result * 59 + getCookie(); Object $family = getFamily();
        result = result * 59 + (($family == null) ? 43 : $family.hashCode());
        Object $accessLevel = getAccessLevel();
        result = result * 59 + (($accessLevel == null) ? 43 : $accessLevel.hashCode());
        long $cash = getCash(); result = result * 59 + (int)($cash >>> 32 ^ $cash);
        result = result * 59 + getCharacterSlots(); result = result * 59 + Arrays.deepHashCode(getMacroses());
        Object $saveGameOptions = getSaveGameOptions();
        result = result * 59 + (($saveGameOptions == null) ? 43 : $saveGameOptions.hashCode());
        Object $uiData = getUiData();
        result = result * 59 + (($uiData == null) ? 43 : $uiData.hashCode());
        Object $players = getPlayers();
        return result * 59 + (($players == null) ? 43 : $players.hashCode());
    }
    @Override
    public String toString() {
        return "LoginAccountInfo(accountId=" + getAccountId() + ", cookie=" + getCookie() + ", family=" + getFamily() + ", accessLevel=" + getAccessLevel() + ", cash=" + getCash() + ", characterSlots=" + getCharacterSlots() + ", macroses=" + Arrays.deepToString(getMacroses()) + ", saveGameOptions=" + getSaveGameOptions() + ", uiData=" + getUiData() + ", players=" + getPlayers() + ")";
    }

    public long getAccountId() {
        return this.accountId;
    }
    public int getCookie() {
        return this.cookie;
    }
    public String getFamily() {
        return this.family;
    }
    public EAccessLevel getAccessLevel() {
        return this.accessLevel;
    }
    public long getCash() {
        return this.cash;
    }
    public int getCharacterSlots() {
        return this.characterSlots;
    }
    private Macros[] macroses = new Macros[10];
    private String saveGameOptions;
    public Macros[] getMacroses() {
        return this.macroses;
    }
    private String uiData;
    private ConcurrentHashMap<Long, BasicDBObject> players;
    public String getSaveGameOptions() {
        return this.saveGameOptions;
    }
    public String getUiData() {
        return this.uiData;
    }
    public ConcurrentHashMap<Long, BasicDBObject> getPlayers() {
        return this.players;
    }


    public void addCharacterSlots(int add) { this.characterSlots += add; }


    public void addPlayer(BasicDBObject player) {
        if (this.players == null) {
            this.players = new ConcurrentHashMap();
        }
        this.players.put(Long.valueOf(player.getLong("_id")), player);
    }


    public BasicDBObject getPlayer(long objectId) { return (BasicDBObject)this.players.get(Long.valueOf(objectId)); }
}
