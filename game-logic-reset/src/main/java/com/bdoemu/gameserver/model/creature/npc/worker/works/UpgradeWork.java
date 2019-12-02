package com.bdoemu.gameserver.model.creature.npc.worker.works;

import com.bdoemu.commons.utils.BuffReader;
import com.bdoemu.gameserver.model.creature.npc.worker.NpcWorker;
import com.bdoemu.gameserver.model.creature.npc.worker.enums.ENpcWorkingType;
import com.bdoemu.gameserver.model.creature.player.Player;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

/**
 * @ClassName UpgradeWork
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 21:06
 * VERSION 1.0
 */

public class UpgradeWork extends ANpcWork{

    private int waypoint;

    public UpgradeWork(final ENpcWorkingType workType) {
        super(workType);
    }

    @Override
    public void load(final BasicDBObject dbObject) {
        this.waypoint = dbObject.getInt("waypoint");
    }

    @Override
    public void read(final BuffReader buffReader) {
        this.waypoint = buffReader.readD();
        buffReader.readHD();
        buffReader.readH();
        buffReader.readC();
        buffReader.readD();
        buffReader.readD();
    }

    public int getWaypoint() {
        return this.waypoint;
    }

    @Override
    public boolean canAct(final NpcWorker npcWorker, final Player player) {
        return npcWorker.getPlantWorkerT().getUpgradeCharacterKey() != null && npcWorker.getUgradeChance() != 0;
    }

    @Override
    public DBObject toDBObject() {
        final BasicDBObjectBuilder builder = new BasicDBObjectBuilder();
        builder.append("workType", (Object) this.workType.name());
        builder.append("waypoint", (Object) this.waypoint);
        return builder.get();
    }
}
