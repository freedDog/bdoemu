package com.bdoemu.gameserver.model.world.storages;

import com.bdoemu.gameserver.model.creature.collect.Collect;

/**
 * @ClassName CollectionStorage
 * @Description   收集物存储器
 * @Author JiangBangMing
 * @Date 2019/7/5 19:26
 * VERSION 1.0
 */

public class CollectionStorage extends CreatureStorage<Collect> {

    public CollectionStorage(final int capacity, final float loadFactor) {
        super(capacity, loadFactor);
    }

    @Override
    public boolean put(final Collect object) {
        return this.collection.put(object.getIndex(), object) != null;
    }

    @Override
    public boolean remove(final Collect object) {
        return this.collection.remove(object.getIndex()) != null;
    }

    @Override
    public boolean contains(final Collect object) {
        return this.collection.containsKey(object.getIndex());
    }
}
