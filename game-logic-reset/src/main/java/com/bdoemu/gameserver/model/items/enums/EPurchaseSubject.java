package com.bdoemu.gameserver.model.items.enums;

/**
 * @ClassName EPurchaseSubject
 * @Description 购买主题
 * @Author JiangBangMing
 * @Date 2019/7/10 14:20
 * VERSION 1.0
 */

public enum EPurchaseSubject {
    None,
    Character,
    Account;

    public boolean isCharacter() {
        return this == EPurchaseSubject.Character;
    }

    public boolean isAccount() {
        return this == EPurchaseSubject.Account;
    }
}
