package com.bdoemu.gameserver.model.actions.enums;

/**
 * @ClassName EMoveDirectionType
 * @Description 移动方向类型
 * @Author JiangBangMing
 * @Date 2019/7/11 14:42
 * VERSION 1.0
 */

public enum EMoveDirectionType {
    Foward,
    Backward,
    Foward_Direction,
    Backward_Direction,
    Foward_Direction_Fix,
    Backward_Direction_Fix,
    LadderUp,
    LadderDown,
    Foward_Camera_Direction,
    Backward_Camera_Direction;

    public boolean isFoward() {
        return this == EMoveDirectionType.Foward;
    }

    public boolean isFixedMoveDirection() {
        return this == EMoveDirectionType.Foward_Direction_Fix || this == EMoveDirectionType.Backward_Direction_Fix;
    }
}
