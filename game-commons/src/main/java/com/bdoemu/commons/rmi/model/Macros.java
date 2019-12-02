package com.bdoemu.commons.rmi.model;

import com.mongodb.BasicDBObject;
import java.beans.ConstructorProperties;
import java.io.Serializable;

/**
 * @ClassName Macros
 * @Description 宏命令
 * @Author JiangBangMing
 * @Date 2019/6/21 21:25
 * VERSION 1.0
 */

public class Macros implements Serializable {

    private String macrosData;
    private byte chatType;
    private int index;

    public void setMacrosData(String macrosData) {
        this.macrosData = macrosData;
    }
    public void setChatType(byte chatType) {
        this.chatType = chatType;
    }
    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this){
            return true;
        }
        if (!(o instanceof Macros)) {
            return false;
        }
        Macros other = (Macros)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Object this$macrosData = getMacrosData(), other$macrosData = other.getMacrosData();
        return ((this$macrosData == null) ? (other$macrosData != null) : !this$macrosData.equals(other$macrosData)) ? false
                : ((getChatType() != other.getChatType()) ? false : (!(getIndex() != other.getIndex())));
    }
    protected boolean canEqual(Object other) {
        return other instanceof Macros;
    }

    @Override
    public int hashCode() {
        int PRIME = 59, result = 1;
        Object $macrosData = getMacrosData();
        result = result * 59 + (($macrosData == null) ? 43 : $macrosData.hashCode());
        result = result * 59 + getChatType();
        return result * 59 + getIndex();
    }

    @Override
    public String toString() {
        return "Macros(macrosData=" + getMacrosData() + ", chatType=" + getChatType() + ", index=" + getIndex() + ")";
    }

    @ConstructorProperties({"macrosData", "chatType", "index"})
    public Macros(String macrosData, byte chatType, int index) {
        this.macrosData = macrosData; this.chatType = chatType; this.index = index;
    }

    public String getMacrosData() {
        return this.macrosData;
    }

    public byte getChatType() {
        return this.chatType;
    }

    public int getIndex() {
        return this.index;
    }

    public Macros(BasicDBObject object) {
        this.macrosData = object.getString("macrosData");
        this.chatType = (byte)object.getInt("type");
        this.index = object.getInt("index");
    }
}
