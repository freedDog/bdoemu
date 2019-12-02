package com.bdoemu.gameserver.model.stats.elements;

import com.bdoemu.gameserver.model.stats.enums.ElementEnum;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

/**
 * @ClassName WeightElement
 * @Description  重量元素
 * @Author JiangBangMing
 * @Date 2019/7/9 20:45
 * VERSION 1.0
 */

public class WeightElement extends Element{

    public WeightElement(final BasicDBObject dbObject) {
        super(ElementEnum.EXPAND, dbObject.getInt("weight"));
    }

    public WeightElement(final int value) {
        super(ElementEnum.EXPAND, value);
    }
    @Override
    public DBObject toDBObject() {
        final BasicDBObjectBuilder builder = new BasicDBObjectBuilder();
        builder.append("weight", this.getIntValue());
        return builder.get();
    }
}
