package com.bdoemu.commons.idfactory;

import com.bdoemu.commons.database.mongo.ADatabaseCollection;
import com.bdoemu.commons.database.mongo.DatabaseCollection;
import com.bdoemu.commons.utils.ClassUtils;
import org.atteo.classindex.ClassIndex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName IDFactory
 * @Description id 工厂类
 * @Author JiangBangMing
 * @Date 2019/6/20 15:06
 * VERSION 1.0
 */

public abstract class IDFactory<T extends Enum<T>> {

    private static final Logger log= LoggerFactory.getLogger(IDFactory.class);

    /**储存设备*/
    protected final Map<T,IDStorage> storages;

    public IDFactory() {
        this.storages = new HashMap<>();
        this.init();
        for (final Class<?> clazz : ClassIndex.getAnnotated( DatabaseCollection.class)) {
            try {
                final Object collectionObj = ClassUtils.singletonInstance(clazz);
                if (collectionObj instanceof ADatabaseCollection) {
                    final ADatabaseCollection collection = (ADatabaseCollection)collectionObj;
                    collection.lockIds(this);
                }
                else {
                    IDFactory.log.error("Database collection {} marked by @DatabaseCollection, but not extend ADatabaseCollection", clazz.getSimpleName());
                }
            }
            catch (Exception e) {
                IDFactory.log.error("Error while initializing DatabaseCollection in class=[{}]", clazz.getSimpleName(), e);
            }
        }
        this.report();
    }

    public long nextId(final T storageType) {
        return this.storages.get(storageType).nextId();
    }

    public void releaseId(final T storageType, final long id) {
        this.storages.get(storageType).releaseId(id);
    }

    public void lockId(final T storageType, final long value) {
        this.storages.get(storageType).lockId(value);
    }

    public abstract void init();

    public abstract void report();
}
