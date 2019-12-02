package com.bdoemu.commons.model.xmlrpc.impl;

/**
 * @ClassName XmlRpcPlayerClassId
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/24 20:07
 * VERSION 1.0
 */

public enum  XmlRpcPlayerClassId {
    /***/
    Warrior(0),
    Ranger(4),
    Sorcerer(8),
    Giant(12),
    Tamer(16),
    ShyWomen(17),
    Shy(18),
    BladeMaster(20),
    BladeMasterWomen(21),
    Temp(22),
    Valkyrie(24),
    NinjaWomen(25),
    NinjaMan(26),
    Wizard(28),
    Kunoichi(30),
    WizardWomen(31);

    private int id;

    private XmlRpcPlayerClassId(final int id) {
        this.id = id;
    }

    public static XmlRpcPlayerClassId valueOf(final int id) {
        for (final XmlRpcPlayerClassId classType : values()) {
            if (classType.id == id) {
                return classType;
            }
        }
        throw new IllegalArgumentException("Invalid class id: " + id);
    }

    public int getId() {
        return this.id;
    }
}
