package com.bdoemu.jme3.scene;

import com.bdoemu.jme3.bounding.BoundingVolume;
import com.bdoemu.jme3.collision.Collidable;
import com.bdoemu.jme3.collision.CollisionResults;

/**
 * @ClassName CollisionData
 * @Description 碰撞数据
 * @Author JiangBangMing
 * @Date 2019/7/11 16:45
 * VERSION 1.0
 */

public interface CollisionData extends Cloneable{

    public int collideWith(Collidable var1, BoundingVolume var2, CollisionResults var3);
}
