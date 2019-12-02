package com.bdoemu.gameserver.model.chat.enums;

/**
 * @ClassName EChatResponseType
 * @Description 聊天响应类型
 * @Author JiangBangMing
 * @Date 2019/7/11 15:00
 * VERSION 1.0
 */
public enum EChatResponseType {
    Rejected,
    Accepted;

    public boolean isAccepted() {
        return this.equals(EChatResponseType.Accepted);
    }
}
