package com.bdoemu.gameserver.model.actions.enums;

/**
 * @ClassName ENaviType
 * @Description 操纵类型
 * @Author JiangBangMing
 * @Date 2019/7/6 14:02
 * VERSION 1.0
 */
public enum ENaviType {
    /**无*/
    none(0),
    /**空气*/
    air(1),
    /**墙壁*/
    wall(2),
    /**栅栏*/
    fence(4),
    /**地面*/
    ground(16),
    /**在地下*/
    under_ground(32),
    /**水*/
    water(64),
    /**在水里*/
    under_water(128),
    /**梯*/
    ladder(256),
    /***/
    all(ENaviType.ground.id | ENaviType.water.id | ENaviType.under_ground.id | ENaviType.under_water.id | ENaviType.air.id | ENaviType.wall.id | ENaviType.fence.id | ENaviType.ladder.id),
    /**在水的地面*/
    under_water_ground(ENaviType.under_water.id | ENaviType.ground.id),
    under_waterground(ENaviType.under_water.id | ENaviType.ground.id),
    /**水在水里*/
    water_under_water(ENaviType.water.id | ENaviType.under_water.id);

    private int id;

    ENaviType(final int id) {
        this.id = id;
    }

    public static boolean hasAir(final int navigationTypes) {
        return (navigationTypes & ENaviType.air.getId()) != 0x0;
    }

    public int getId() {
        return this.id;
    }

    public boolean isAir() {
        return this == ENaviType.air;
    }
}
