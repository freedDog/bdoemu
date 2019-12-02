package com.bdoemu.gameserver.model.creature.npc.worker.works;

import com.bdoemu.commons.utils.BuffReader;
import com.bdoemu.gameserver.model.creature.npc.worker.enums.ENpcWorkingType;
import com.mongodb.BasicDBObject;

/**
 * @ClassName PlantBulidingWork
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 21:10
 * VERSION 1.0
 */

public class PlantBulidingWork extends ANpcWork{

    public PlantBulidingWork(final ENpcWorkingType workType) {
        super(workType);
    }

    @Override
    public void load(final BasicDBObject dbObject) {
    }

    @Override
    public void read(final BuffReader buffReader) {
        buffReader.skipAll();
    }
}
