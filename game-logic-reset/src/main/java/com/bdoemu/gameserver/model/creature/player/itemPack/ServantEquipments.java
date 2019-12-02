package com.bdoemu.gameserver.model.creature.player.itemPack;

import com.bdoemu.gameserver.model.creature.servant.Servant;
import com.bdoemu.gameserver.model.items.enums.EItemStorageLocation;
import com.bdoemu.gameserver.model.stats.Stat;
import com.bdoemu.gameserver.model.stats.elements.BaseElement;
import com.mongodb.BasicDBObject;

/**
 * @ClassName ServantEquipments
 * @Description  仆从装备
 * @Author JiangBangMing
 * @Date 2019/7/9 20:48
 * VERSION 1.0
 */

public class ServantEquipments extends EquipmentsBag{

    public ServantEquipments(final Servant servant) {
        super(EItemStorageLocation.ServantEquip, new Stat(servant, new BaseElement(31.0f)), servant);
    }

    public ServantEquipments(final BasicDBObject dbObject, final Servant servant) {
        super(EItemStorageLocation.ServantEquip, dbObject, servant);
    }

    @Override
    public int getMaxExpandSize() {
        return this.expandStat.getBase().getIntValue();
    }
}
