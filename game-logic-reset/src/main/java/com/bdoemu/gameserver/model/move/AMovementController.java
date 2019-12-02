package com.bdoemu.gameserver.model.move;

import com.bdoemu.gameserver.model.actions.enums.ENaviType;
import com.bdoemu.gameserver.model.creature.Creature;
import com.bdoemu.gameserver.model.world.Location;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

/**
 * @ClassName AMovementController
 * @Description  运动控制器
 * @Author JiangBangMing
 * @Date 2019/7/6 14:00
 * VERSION 1.0
 */

public abstract class AMovementController<T extends Creature> {

    private T _owner;

    @SuppressWarnings("WeakerAccess")
    protected AMovementController(final T owner) {
        _owner = owner;
    }

    public abstract boolean canMove(double x, double y);

    public void startMove(Collection<Location> destinations, Callable<Boolean> onEnd, Consumer<Method> onExit, ENaviType naviType) {
        //
    }

    public void startMove(Location destination, Callable<Boolean> movementCompleted, Consumer<Method> movementFailed, ENaviType naviType, boolean moveStraight) {
        //
    }

    public void startFollow(Creature target, Callable<Boolean> onEnd, Consumer<Method> onExit, ENaviType naviType) {
        //
    }

    public abstract boolean isMoving();

    public abstract Location getOrigin();

    public abstract Location getDestination();

    public abstract void cancelMoveTask();

    public T getOwner() {
        return _owner;
    }
}
