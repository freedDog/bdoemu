package com.bdoemu.gameserver.model.creature.player.enums;

/**
 * @ClassName ERenderType
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/9 19:59
 * VERSION 1.0
 */

public enum  ERenderType {
    HELM(1),
    HELM_IN_BATTLE(2),
    UNDERWEAR(4),
    SHOW_NAME_WHEN_CAMOUFLAGE(8),
    CLOAK(16);

    private int mask;

    private ERenderType(final int mask) {
        this.mask = mask;
    }

    public int getMask() {
        return this.mask;
    }
}
