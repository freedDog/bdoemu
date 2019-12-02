package com.bdoemu.jme3.scene;

import com.bdoemu.jme3.bounding.BoundingBox;
import com.bdoemu.jme3.bounding.BoundingVolume;
import com.bdoemu.jme3.collision.Collidable;
import com.bdoemu.jme3.collision.CollisionResults;
import com.bdoemu.jme3.collision.bih.BIHTree;

/**
 * @ClassName Mesh
 * @Description 网格
 * @Author JiangBangMing
 * @Date 2019/7/11 16:08
 * VERSION 1.0
 */

public class Mesh implements Cloneable{
    private BoundingVolume meshBound = new BoundingBox();
    private final CollisionData collisionTree;

    public Mesh(float[] vertices, int[] indexes) {
        this.collisionTree = new BIHTree(vertices, indexes);
        this.meshBound.computeFromPoints(vertices);
    }

    public int collideWith(Collidable other, CollisionResults results) {
        return this.collisionTree.collideWith(other, this.meshBound, results);
    }
}
