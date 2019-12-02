package com.bdoemu.gameserver.model.creature.player.enums;

/**
 * @ClassName EZodiacType
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/9 19:57
 * VERSION 1.0
 */

public enum  EZodiacType {

    Unknown,
    Hammer,
    Boat,
    Shield,
    Golem,
    Camel,
    Dragon,
    Owl,
    Elephant,
    Key,
    Cart,
    Cube,
    Injun;

    public int getId() {
        return this.ordinal();
    }
}
