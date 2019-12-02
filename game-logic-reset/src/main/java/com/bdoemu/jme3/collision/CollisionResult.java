package com.bdoemu.jme3.collision;

import com.bdoemu.jme3.math.Vector3f;

/**
 * @ClassName CollisionResult
 * @Description  碰撞结果
 * @Author JiangBangMing
 * @Date 2019/7/11 16:15
 * VERSION 1.0
 */

public class CollisionResult implements Comparable<CollisionResult>{

    private Vector3f contactPoint;
    private float distance;

    public CollisionResult(Vector3f contactPoint, float distance) {
        this.contactPoint = contactPoint;
        this.distance = distance;
    }

    public CollisionResult() {
    }

    @Override
    public int compareTo(CollisionResult other) {
        return Float.compare(this.distance, other.distance);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CollisionResult) {
            return ((CollisionResult)obj).compareTo(this) == 0;
        }
        return super.equals(obj);
    }
    @Override
    public int hashCode() {
        return Float.floatToIntBits(this.distance);
    }

    public Vector3f getContactPoint() {
        return this.contactPoint;
    }

    public float getDistance() {
        return this.distance;
    }
}
