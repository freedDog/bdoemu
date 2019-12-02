package com.bdoemu.gameserver.model.stats.elements;

import com.bdoemu.gameserver.model.stats.enums.ElementEnum;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

/**
 * @ClassName ExpandElement
 * @Description  扩展元素
 * @Author JiangBangMing
 * @Date 2019/7/9 17:36
 * VERSION 1.0
 */

public class ExpandElement extends Element{

    private long endTime;

    public ExpandElement(final BasicDBObject dbObject) {
        super(ElementEnum.EXPAND, dbObject.getInt("expandSize"));
        this.endTime = dbObject.getLong("endTime");
    }

    public ExpandElement(final int value, final long endTime) {
        super(ElementEnum.EXPAND, value);
        this.endTime = endTime;
    }

    @Override
    public int getIntValue() {
        if (this.endTime > System.currentTimeMillis()) {
            return super.getIntValue();
        }
        return 0;
    }

    public long getEndTime() {
        return this.endTime;
    }

    @Override
    public DBObject toDBObject() {
        final BasicDBObjectBuilder builder = new BasicDBObjectBuilder();
        builder.append("expandSize", (Object) this.getIntValue());
        builder.append("endTime", (Object) this.endTime);
        return builder.get();
    }
}
