package com.bdoemu.gameserver.model.ai.deprecated;

/**
 * @ClassName EAIFindTargetType
 * @Description  ai 查找目标类型
 * @Author JiangBangMing
 * @Date 2019/7/6 16:04
 * VERSION 1.0
 */
public enum EAIFindTargetType {

    Self,
    Child,
    Parent,
    Sibling,
    Interaction,
    Player,
    Monster,
    EliteMonster,
    Npc,
    Enemy,
    Collect,
    Character,
    Corpse,
    VehicleHorse,
    Tent,
    CombineWaveAlly,
    CombineWavePlayer,
    Installation,
    InTheHouse,
    PKPlayer,
    RidingVehicle,
    EnemyLordOrKingTent,
    EnemyBarricade,
    AllyLordOrKingTent,
    OwnerPlayer,
    LordOrKingPlayer,
    AllyVehicle,

    // Pet only - Server-side
    _Skill0_TargetType0,
    _Skill0_TargetType1
}
