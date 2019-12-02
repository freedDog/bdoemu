package com.bdoemu.commons.model.enums;

/**
 * @ClassName EAccessLevel
 * @Description 访问级别
 * @Author JiangBangMing
 * @Date 2019/6/21 21:22
 * VERSION 1.0
 */
public enum EAccessLevel {
    /***/
    BANNED(-1),
    /***/
    USER(0),
    TESTER(1),
    MODERATOR(2),
    GAMEMASTER(3),
    ADMIN(4);
    private int accessId;
    EAccessLevel(int accessId) {
        this.accessId = accessId;
    }
    public int getAccessId() {
        return this.accessId;
    }
    public boolean isGM() {
        return (this == MODERATOR || this == ADMIN);
    }
    public boolean isModerator() {
        return (this == MODERATOR);
    }
    public boolean isAdmin() {
        return (this == ADMIN);
    }
}
