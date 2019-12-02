package com.bdoemu.gameserver.model.creature.player.fitness.enums;

/**
 * @ClassName EFitnessType
 * @Description 健身型
 * @Author JiangBangMing
 * @Date 2019/7/10 15:16
 * VERSION 1.0
 */

public enum  EFitnessType {
    Stamina,
    Strength,
    Health;

    public static EFitnessType valueOf(final int reqType) {
        if (reqType < 0 || reqType > values().length - 1) {
            throw new IllegalArgumentException("Invalid EFitnessType id: " + reqType);
        }
        return values()[reqType];
    }
}
