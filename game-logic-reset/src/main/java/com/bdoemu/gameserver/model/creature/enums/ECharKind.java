package com.bdoemu.gameserver.model.creature.enums;


import com.bdoemu.gameserver.model.creature.DeadBody;
import com.bdoemu.gameserver.model.creature.Gate;
import com.bdoemu.gameserver.model.creature.collect.Collect;
import com.bdoemu.gameserver.model.creature.monster.Monster;
import com.bdoemu.gameserver.model.creature.npc.Npc;
import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.creature.servant.Servant;
import com.bdoemu.gameserver.model.houses.HouseHold;

/**
 * @ClassName ECharKind
 * @Description
 * @Author JiangBangMing
 * @Date 2019/7/5 17:08
 * VERSION 1.0
 */
public enum ECharKind {
    /**w玩家*/
    Player(0, ERemoveActorType.OutOfRange, Player.class),
    /**怪物*/
    Monster(1, ERemoveActorType.OutOfRange, Monster.class),
    /**NPC*/
    Npc(2, ERemoveActorType.OutOfRange,Npc.class),
    /**运载工具*/
    Vehicle(3, ERemoveActorType.OutOfRange, Servant.class),
    /**收集*/
    Collect(4, ERemoveActorType.None, Collect.class),
    /***/
    Alterego(5, ERemoveActorType.None, Gate.class),
    /**门*/
    Gate(6, ERemoveActorType.None, null),
    /**房子*/
    Household(7, ERemoveActorType.None, HouseHold.class),
    /**装置*/
    Installation(8, ERemoveActorType.None, null),
    /**尸体*/
    Deadbody(9, ERemoveActorType.None, DeadBody.class);

    private byte id;
    //超出范围删除Actor类型
    private ERemoveActorType outOfRangeRemoveActorType;
    private Class actorClass;

    ECharKind(final int id, final ERemoveActorType outOfRangeActorType, final Class actorClass) {
        this.id = (byte) id;
        this.outOfRangeRemoveActorType = outOfRangeActorType;
        this.actorClass = actorClass;
    }

    public static ECharKind valueOf(final int id) {
        if (id < 0 || id > values().length - 1) {
            throw new IllegalArgumentException("Invalid ECharKind id: " + id);
        }
        return values()[id];
    }

    public static ECharKind forClass(final Class actorClass) {
        for (final ECharKind charKind : values()) {
            if (charKind.actorClass != null && charKind.actorClass.getSimpleName().equals(actorClass.getSimpleName())) {
                return charKind;
            }
        }
        return null;
    }

    public boolean isHousehold() {
        return this == ECharKind.Household;
    }

    public boolean isMonster() {
        return this == ECharKind.Monster;
    }

    public boolean isVehicle() {
        return this == ECharKind.Vehicle;
    }

    public boolean isNpc() {
        return this == ECharKind.Npc;
    }

    public boolean isCollect() {
        return this == ECharKind.Collect;
    }

    public boolean isDeadbody() {
        return this == ECharKind.Deadbody;
    }

    public boolean isPlayer() {
        return this == ECharKind.Player;
    }

    public boolean isAlterego() {
        return this == ECharKind.Alterego;
    }

    public byte getId() {
        return this.id;
    }

    public ERemoveActorType getOutOfRangeRemoveActorType() {
        return this.outOfRangeRemoveActorType;
    }
}
