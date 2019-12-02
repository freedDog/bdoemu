package com.bdoemu.gameserver.model.ai.deprecated;

/**
 * @ClassName EAIMoveDestType
 * @Description  移动到目标的类型
 * @Author JiangBangMing
 * @Date 2019/7/6 15:54
 * VERSION 1.0
 */

public enum  EAIMoveDestType {
    Random,
    SenderPosition,
    SenderDestination,
    SenderTarget,
    DungeonDestination,
    DungeonTarget,
    DungeonWaypoint,
    OwnerPosition,
    OwnerDestination,
    OwnerRelative,
    OwnerPath,
    TargetPosition,
    Absolute,
    Relative,
    House,
    ExcelRoot,
    OnPath,
    WaypointRoute,
    WaypointVariable,
    RetryCurve,
    FloatingRelative,
}
