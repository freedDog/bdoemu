package com.bdoemu.commons.rmi.model;

import java.io.Serializable;

/**
 * @ClassName GameAccountInfo
 * @Description 游戏账户信息
 * @Author JiangBangMing
 * @Date 2019/6/21 20:39
 * VERSION 1.0
 */

public class GameAccountInfo implements Serializable {

    private int charactersCount;
    private int charactersForDelete;

    public GameAccountInfo(int charactersCount, int charactersForDelete) {
        this.charactersCount = charactersCount;
        this.charactersForDelete = charactersForDelete;
    }

    public void setCharactersCount(int charactersCount) {
        this.charactersCount = charactersCount;
    }
    public void setCharactersForDelete(int charactersForDelete) {
        this.charactersForDelete = charactersForDelete;
    }
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof GameAccountInfo)) {
            return false;
        }
        GameAccountInfo other = (GameAccountInfo)o;
        return !other.canEqual(this) ? false
                : ((getCharactersCount() != other.getCharactersCount()) ? false :
                (!(getCharactersForDelete() != other.getCharactersForDelete())));
    }
    protected boolean canEqual(Object other) {
        return other instanceof GameAccountInfo;
    }

    @Override
    public int hashCode() {
        int PRIME = 59,result = 1;
        result = result * 59 + getCharactersCount();
        return result * 59 + getCharactersForDelete();
    }

    @Override
    public String toString() {
        return "GameAccountInfo(charactersCount=" + getCharactersCount() + ", charactersForDelete=" + getCharactersForDelete() + ")";
    }

    public int getCharactersCount() {
        return this.charactersCount;
    }
    public int getCharactersForDelete() {
        return this.charactersForDelete;
    }


}
