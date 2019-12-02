package com.bdoemu.jme3.collision;

/**
 * @ClassName Collidable
 * @Description  碰撞体
 * @Author JiangBangMing
 * @Date 2019/7/11 16:13
 * VERSION 1.0
 */

public interface Collidable {

    public int collideWith(Collidable var1, CollisionResults var2) throws UnsupportedCollisionException;
}
