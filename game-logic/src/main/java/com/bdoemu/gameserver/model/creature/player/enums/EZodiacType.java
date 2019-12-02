package com.bdoemu.gameserver.model.creature.player.enums;

/**
 * 星座类型
 */
public enum EZodiacType {
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
