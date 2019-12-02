package com.bdoemu.commons.database.mongo;

import com.bdoemu.commons.database.DatabaseFactory;
import com.bdoemu.commons.idfactory.IDFactory;
import com.bdoemu.commons.utils.DatabaseUtils;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * @ClassName ADatabaseCollection
 * @Description 数据库收集
 * @Author JiangBangMing
 * @Date 2019/6/20 12:27
 * VERSION 1.0
 */

public abstract class ADatabaseCollection<T extends JSONable,K extends IDFactory>  {

    private static final Logger log = LoggerFactory.getLogger(ADatabaseCollection.class);

    private static BasicDBObject EMPTY_OBJECT = new BasicDBObject();

    private Class<T> clazz;

    protected static DB db;

    protected DBCollection collection;

    protected Map<BasicDBObject, List<DatabaseLockInfo>> locks = new HashMap();

    protected void addLockInfo(DatabaseLockInfo lockInfo, BasicDBObject filter) {
        this.locks.computeIfAbsent(filter, item -> new ArrayList()).add(lockInfo);
    }

    protected void addLockInfo(DatabaseLockInfo lockInfo) {
        addLockInfo(lockInfo, EMPTY_OBJECT);
    }

    public void lockIds(K idFactory) {
        if (this.collection.count() > 0L && this.locks.size() > 0) {
            for (final Map.Entry<BasicDBObject, List<DatabaseLockInfo>> lockEntry : this.locks.entrySet()) {
                final BasicDBObject filter = lockEntry.getKey();
                for (final DatabaseLockInfo lockInfo : lockEntry.getValue()) {
                    try (final DBCursor cursor = this.collection.find(filter, lockInfo.getFilterFields(filter))) {
                        while (cursor.hasNext()) {
                            final BasicDBObject dbObject = (BasicDBObject) cursor.next();
                            final Object objectDB = DatabaseUtils.getFieldFromCursor(dbObject, lockInfo.getObjectPath());
                            if (objectDB != null) {
                                if (objectDB instanceof BasicDBList) {
                                    for (final Object innerDbObject : (BasicDBList) objectDB) {
                                        final long objectId = ((BasicDBObject) innerDbObject).getLong(lockInfo.getObjectIdField());
                                        idFactory.lockId(lockInfo.getStorageType(), objectId);
                                    }
                                } else {
                                    if (!(objectDB instanceof BasicDBObject)) {
                                        continue;
                                    }
                                    final long objectId2 = ((BasicDBObject) objectDB).getLong(lockInfo.getObjectIdField());
                                    idFactory.lockId(lockInfo.getStorageType(), objectId2);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public ADatabaseCollection(Class<T> clazz, String collectionName) {
        this.clazz = clazz;
        db = DatabaseFactory.getInstance().getDatabase();
        this.collection = db.getCollection(collectionName);
    }

    public boolean save(T model) {
        try {
            final BasicDBObject dbObject = (BasicDBObject)model.toDBObject();
            return this.collection.save((DBObject)dbObject).getN() > 0;
        } catch (MongoException ex) {
            log.error("Error while saving database entity", ex);
            return false;
        }
    }
    public void update(T model) {
        try {
            QueryBuilder where = QueryBuilder.start("_id");
            where.is(Long.valueOf(model.getObjectId()));
            BasicDBObject dbObject = (BasicDBObject)model.toDBObject();
            dbObject.removeField("_id");

            this.collection.update(where.get(), dbObject, false, false);
        } catch (MongoException ex) {
            log.error("Error while update database entity", ex);
        }
    }

    public void update(Collection<T> models) {
        try {
            for (Iterator iterator = models.iterator(); iterator.hasNext(); ) { T model = (T)(JSONable)iterator.next();
                update(model); }

        } catch (MongoException ex) {
            log.error("Error while update database entity", ex);
        }
    }

    public void updateField(long objectId, String field, Object value) {
        try {
            BasicDBObject where = new BasicDBObject("_id", Long.valueOf(objectId));
            BasicDBObject update = new BasicDBObject(field, value);
            this.collection.update(where, new BasicDBObject("$set", update), false, false);
        } catch (MongoException ex) {
            log.error("Error while updateField()", ex);
        }
    }

    public void delete(long objectId) {
        try {
            QueryBuilder where = QueryBuilder.start("_id");
            where.is(Long.valueOf(objectId));
            this.collection.remove(where.get());
        } catch (MongoException ex) {
            log.error("Error while delete database entity", ex);
        }
    }

    public T load(long objectId, Object... args) {
        try {
            BasicDBObject where = new BasicDBObject("_id", Long.valueOf(objectId));
            DBObject dbObject = this.collection.findOne(where);
            if (dbObject == null) {
                return null;
            }
            return getInstanceOfT((BasicDBObject)dbObject, args);
        } catch (MongoException ex) {
            log.error("Error while calling load database entity", ex);
        } catch (IllegalAccessException|InstantiationException|NoSuchMethodException| InvocationTargetException ex) {
            log.error("Error while initiating database entity constructor", ex);
        }
        return null;
    }


    public ConcurrentHashMap<Long, T> load() {
        return load(EMPTY_OBJECT);
    }


    public ConcurrentHashMap<Long, T> load(BasicDBObject filter) {
        final ConcurrentHashMap<Long, T> objects = new ConcurrentHashMap<Long, T>();
        try {
            if (filter.isEmpty()) {
                try (final DBCursor cursor = this.collection.find()) {
                    while (cursor.hasNext()) {
                        final DBObject dbObject = cursor.next();
                        final T model = this.getInstanceOfT((BasicDBObject)dbObject, new Object[0]);
                        objects.put(model.getObjectId(), model);
                    }
                }
            }
            else {
                try (final DBCursor cursor = this.collection.find((DBObject)filter)) {
                    while (cursor.hasNext()) {
                        final DBObject dbObject = cursor.next();
                        final T model = this.getInstanceOfT((BasicDBObject)dbObject, new Object[0]);
                        objects.put(model.getObjectId(), model);
                    }
                }
            }
        }
        catch (MongoException ex) {
            ADatabaseCollection.log.error("Error while loading entities", (Throwable)ex);
        }
        catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException ex4) {
            ADatabaseCollection.log.error("Error while initiating database entity constructor", (Throwable)ex4);
        }
        return objects;
    }

    public ConcurrentHashMap<Long, T> load(String field, Object value) {
        return load(new BasicDBObject(field, value));
    }

    public T loadOne(BasicDBObject filter) {
        try {
            DBObject dbObject = this.collection.findOne(filter);
            if (dbObject == null) {
                return null;
            }
            return (T)getInstanceOfT((BasicDBObject)dbObject, new Object[0]);
        } catch (MongoException ex) {
            log.error("Error while calling load database entity", ex);
        } catch (IllegalAccessException|InstantiationException|NoSuchMethodException|InvocationTargetException ex) {
            log.error("Error while initiating database entity constructor", ex);
        }
        return null;
    }

    public T loadOne(String field, Object value) {
        return (T)loadOne(new BasicDBObject(field, value));
    }

    public boolean exists(BasicDBObject filter) {
        try (final DBCursor cursor = this.collection.find((DBObject)filter)) {
            return cursor.hasNext();
        }
        catch (MongoException ex) {
            ADatabaseCollection.log.error("Error while checking exists entities", (Throwable)ex);
            return false;
        }
    }

    public boolean exists(String field, Object value) {
        try {
            BasicDBObject where = new BasicDBObject(field, value);
            return (this.collection.getCount(where) > 0L);
        } catch (MongoException ex) {
            log.error("Error while calling load database entity", ex);

            return false;
        }
    }
    public boolean exists(String field, String value, boolean ignoreCase) {
        try {
            BasicDBObject where;
            if (ignoreCase) {
                where = new BasicDBObject(field, Pattern.compile("^" + value + "$", 2));
            } else {

                where = new BasicDBObject(field, value);
            }
            return (this.collection.count(where) > 0L);
        }
        catch (MongoException ex) {
            log.error("Error while exists()", ex);

            return false;
        }
    }

    public boolean exists(String field, String value) {
        return exists(field, value, true);
    }

    public boolean exists(long objectId) {
        return exists("_id", Long.valueOf(objectId));
    }

    private T getInstanceOfT(BasicDBObject object, Object... args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (args.length == 0) {
            return (T)(JSONable)this.clazz.getDeclaredConstructor(new Class[] { BasicDBObject.class }).newInstance(new Object[] { object });
        }

        Class[] constructorClasses = new Class[args.length + 1];
        Object[] constructorObjects = new Object[args.length + 1];
        constructorClasses[0] = BasicDBObject.class;
        constructorObjects[0] = object;
        for (int index = 1; index < constructorClasses.length; index++) {
            constructorClasses[index] = args[index - 1].getClass();
            constructorObjects[index] = args[index - 1];
        }
        return this.clazz.getDeclaredConstructor(constructorClasses).newInstance(constructorObjects);
    }
}
