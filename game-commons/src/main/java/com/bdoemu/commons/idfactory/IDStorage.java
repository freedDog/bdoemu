package com.bdoemu.commons.idfactory;

import com.bdoemu.commons.concurrent.CloseableReentrantLock;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.BitSet;
import java.util.Collection;

/**
 * @ClassName IDStorage
 * @Description ID 仓库
 * @Author JiangBangMing
 * @Date 2019/6/20 15:08
 * VERSION 1.0
 */

public class IDStorage {

    private static final Logger log = LoggerFactory.getLogger(IDStorage.class);;
    private final BitSet idList;
    private final CloseableReentrantLock lock;
    private final long minId;
    private final long maxId;
    private final String storageName;
    private volatile int nextMinId;

    public IDStorage(final long minId, final long maxId, final String storageName) {
        this.idList = new BitSet();
        this.lock = new CloseableReentrantLock();
        this.nextMinId = 1;
        this.minId = minId;
        this.maxId = maxId;
        this.storageName = storageName;
        this.lockIds((Object)minId);
    }

    public IDStorage(final long minId, final long maxId, final int segment, final String storageName) {
        this.idList = new BitSet();
        this.lock = new CloseableReentrantLock();
        this.nextMinId = 1;
        this.minId = minId;
        this.maxId = maxId;
        this.storageName = storageName;
        this.lockIds((Object)minId);
        if (segment > 0) {
            for (long i = minId; i <= maxId; i += segment * 2) {
                final int minIndex = (int)(i - minId);
                final int maxIndex = minIndex + segment;
                this.lockSegment(minIndex, maxIndex);
            }
        }
    }

    public long nextId() {
        try (final CloseableReentrantLock closeableLock = this.lock.open()) {
            int id;
            if (this.nextMinId == Integer.MIN_VALUE) {
                id = Integer.MIN_VALUE;
            }
            else {
                id = this.idList.nextClearBit(this.nextMinId);
            }
            if (id + this.minId == this.maxId) {
                throw new IDStorageError("All id's are used, please clear your database");
            }
            this.idList.set(id);
            this.nextMinId = id + 1;
            return this.minId + id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void lockIds(final long... ids) {
        try (final CloseableReentrantLock closeableLock = this.lock.open()) {
            for (final long value : ids) {
                final int id = (int)(value - this.minId);
                final boolean status = this.idList.get(id);
                if (status) {
                    throw new IDStorageError("Storage:" + this.storageName + " ID: " + id + " value: " + value + " is already taken, fatal error!!!");
                }
                this.idList.set(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lockId(final long value) {
        final int id = (int)(value - this.minId);
        final boolean status = this.idList.get(id);
        if (status) {
            throw new IDStorageError("Storage:" + this.storageName + " ID: " + id + " value: " + value + " is already taken, fatal error!!!");
        }
        this.idList.set(id);
    }

    private void lockBasicDBList(final Object value) {
        final BasicDBList ids = (BasicDBList)((BasicDBObject)value).get("ids");
        for (final Object id : ids) {
            if (id instanceof Long) {
                this.lockId((long)id);
            }
            else {
                this.lockBasicDBList(id);
            }
        }
    }

    public void lockIds(final Object value) {
        try (final CloseableReentrantLock closeableLock = this.lock.open()) {
            if (value instanceof DBObject) {
                this.lockBasicDBList(value);
            }
            else {
                this.lockId((long)value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void releaseId(final long value) {
        try (final CloseableReentrantLock closeableLock = this.lock.open()) {
            final int id = (int)(value - this.minId);
            final boolean status = this.idList.get(id);
            if (!status) {
                IDStorage.log.error("Storage: {} ID [{}] value [{}] is not taken, can't release it.", new Object[] { this.storageName, id, value });
            }
            this.idList.clear(id);
            if (value < this.nextMinId || this.nextMinId == Integer.MIN_VALUE) {
                this.nextMinId = id;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void releaseIds(final Collection<Integer> ids) {
        try (final CloseableReentrantLock closeableLock = this.lock.open()) {
            for (Integer id : ids) {
                id -= (int)this.minId;
                final boolean status = this.idList.get(id);
                if (!status) {
                    IDStorage.log.error("Storage: {} ID [{}] is not taken, can't release it.", (Object)this.storageName, (Object)id);
                }
                this.idList.clear(id);
                if (id < this.nextMinId || this.nextMinId == Integer.MIN_VALUE) {
                    this.nextMinId = id;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getUsedCount() {
        try (final CloseableReentrantLock closeableLock = this.lock.open()) {
            return this.idList.cardinality();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void lockSegment(final int minIndex, final int maxIndex) {
        try (final CloseableReentrantLock closeableLock = this.lock.open()) {
            this.idList.set(minIndex, maxIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
