package com.bdoemu.gameserver.model.skills.packageeffects;

import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.creature.player.itemPack.Warehouse;
import com.bdoemu.gameserver.model.skills.packageeffects.enums.EChargeUserType;
import com.bdoemu.gameserver.model.stats.elements.BuffElement;
import com.bdoemu.gameserver.model.stats.elements.ExpandElement;

/**
 * @ClassName StarterPackageEffect
 * @Description  起动器包装效果
 * @Author JiangBangMing
 * @Date 2019/7/11 10:07
 * VERSION 1.0
 */

public class StarterPackageEffect extends AChargeUserEffect {

    private BuffElement weight;
    private BuffElement expBonus;

    public StarterPackageEffect(final long effectEndTime, final EChargeUserType chargeUserType) {
        super(effectEndTime, chargeUserType);
    }

    @Override
    public void initEffect(final Player owner) {
        this.weight = new BuffElement(1000000.0f);
        owner.getGameStats().getWeight().addElement(this.weight);
        final ExpandElement inventoryExpandElement = new ExpandElement(16, this.effectEndTime);
        owner.getPlayerBag().getInventory().addExpandElement(inventoryExpandElement);
//        owner.sendPacket(new SMSetCharacterPrivatePoints(owner));
//        owner.sendPacket(new SMInventorySlotCount(owner.getPlayerBag().getInventory().getExpandSize()));
        for (final Warehouse warehouse : owner.getPlayerBag().getWarehouses()) {
            final ExpandElement warehouseExpandElement = new ExpandElement(16, this.effectEndTime);
            warehouse.addExpandElement(warehouseExpandElement);
//            owner.sendPacket(new SMWarehouseSlotCount(warehouse));
        }
        this.expBonus = new BuffElement(300000.0f);
        owner.getGameStats().getExpRate().addElement(this.expBonus);
        owner.getGameStats().getLifeExpRate().addElement(this.expBonus);
        owner.getGameStats().getSkillExpRate().addElement(this.expBonus);
    }

    @Override
    public void endEffect(final Player owner) {
        owner.getGameStats().getExpRate().removeElement(this.expBonus);
        owner.getGameStats().getLifeExpRate().removeElement(this.expBonus);
        owner.getGameStats().getSkillExpRate().removeElement(this.expBonus);
        owner.getGameStats().getWeight().removeElement(this.weight);
//        owner.sendPacket(new SMSetCharacterPrivatePoints(owner));
//        owner.sendPacket(new SMInventorySlotCount(owner.getPlayerBag().getInventory().getExpandSize()));
    }
}
