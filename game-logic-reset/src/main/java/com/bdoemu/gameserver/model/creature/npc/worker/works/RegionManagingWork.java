package com.bdoemu.gameserver.model.creature.npc.worker.works;

import com.bdoemu.commons.utils.BuffReader;
import com.bdoemu.gameserver.model.creature.npc.worker.enums.ENpcWorkingType;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * @ClassName RegionManagingWork
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 21:11
 * VERSION 1.0
 */

public class RegionManagingWork extends ANpcWork{
    public RegionManagingWork(final ENpcWorkingType workType) {
        super(workType);
    }

    @Override
    public void load(final BasicDBObject dbObject) {
    }

    @Override
    public void read(final BuffReader buffReader) {
        buffReader.skipAll();
    }

    @Override
    public DBObject toDBObject() {
        return null;
    }
}
