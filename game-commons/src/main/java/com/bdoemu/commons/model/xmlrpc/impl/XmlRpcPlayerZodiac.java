package com.bdoemu.commons.model.xmlrpc.impl;

/**
 * @ClassName XmlRpcPlayerZodiac
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/24 20:08
 * VERSION 1.0
 */
public enum XmlRpcPlayerZodiac {
    /***/
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

    public static XmlRpcPlayerZodiac valueOf(final int id) {
        for (final XmlRpcPlayerZodiac zodiac : values()) {
            if (zodiac.ordinal() == id) {
                return zodiac;
            }
        }
        throw new IllegalArgumentException("Invalid zodiac id: " + id);
    }
}
