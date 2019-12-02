package com.bdoemu.gameserver.model.creature.enums;

/**
 * @ClassName ERemoveActorType
 * @Description   移除演员类型
 * @Author JiangBangMing
 * @Date 2019/7/5 17:11
 * VERSION 1.0
 */
public enum ERemoveActorType {
    /**没有*/
    None(-2),
    /**采集*/
    Collect(-1),
    /**注销*/
    Logout(0),
    /**销毁尸体*/
    DespawnDeadBody(2),
    /**销毁怪物*/
    DespawnMonster(3),
    /**远距离传送*/
    Teleport(4),
    /**销毁护送*/
    DespawnEscort(5),
    /**销毁帐篷*/
    DespawnTent(7),
    /**超出范围*/
    OutOfRange(9),
    /**密封的仆人*/
    SealServant(10),
    /**销毁召唤*/
    DespawnSummon(11),
    /**驯服的仆人*/
    DespawnTamedServant(14),
    /**退出的玩家*/
    QuitPlayer(15);

    private byte id;

    ERemoveActorType(final int id) {
        this.id = (byte) id;
    }

    public byte getId() {
        return this.id;
    }

    public boolean isNone() {
        return this == ERemoveActorType.None;
    }

    public boolean isCollect() {
        return this == ERemoveActorType.Collect;
    }
}
