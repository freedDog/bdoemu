package com.bdoemu.webserver.model;

import java.util.Map;

/**
 * @ClassName BoardGameRequest
 * @Description 棋盘游戏请求
 * @Author JiangBangMing
 * @Date 2019/7/11 15:49
 * VERSION 1.0
 */

public class BoardGameRequest {
    private byte serverNo;
    private int userId;
    private int characterNo;
    private String characterName;
    private String blackSoul;
    private String mailTitle;
    private String mailContent;
    private String nationCode;

    public BoardGameRequest(final Map<String, String[]> paramMap) {
    }

    public byte getServerNo() {
        return this.serverNo;
    }

    public int getUserId() {
        return this.userId;
    }

    public int getCharacterNo() {
        return this.characterNo;
    }

    public String getCharacterName() {
        return this.characterName;
    }

    public String getBlackSoul() {
        return this.blackSoul;
    }

    public String getMailTitle() {
        return this.mailTitle;
    }

    public String getMailContent() {
        return this.mailContent;
    }

    public String getNationCode() {
        return this.nationCode;
    }
}
