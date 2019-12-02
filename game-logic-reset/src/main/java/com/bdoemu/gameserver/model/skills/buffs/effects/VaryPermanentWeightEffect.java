package com.bdoemu.gameserver.model.skills.buffs.effects;

import com.bdoemu.gameserver.model.creature.Creature;
import com.bdoemu.gameserver.model.skills.buffs.ABuffEffect;
import com.bdoemu.gameserver.model.skills.buffs.ActiveBuff;
import com.bdoemu.gameserver.model.skills.buffs.templates.BuffTemplate;
import com.bdoemu.gameserver.model.skills.templates.SkillT;
import com.bdoemu.gameserver.model.stats.elements.WeightElement;

import java.util.Collection;

/**
 * @ClassName VaryPermanentWeightEffect
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/12 10:05
 * VERSION 1.0
 */

public class VaryPermanentWeightEffect extends ABuffEffect {
    @Override
    public Collection<ActiveBuff> initEffect(final Creature owner, final Collection<? extends Creature> targets, final SkillT skillT, final BuffTemplate buffTemplate) {
        final Integer[] params = buffTemplate.getParams();
        final int maxWeightValue = params[0];
        final WeightElement element = new WeightElement(maxWeightValue);
        owner.getGameStats().getWeight().addElement(element);
//        owner.sendPacket(new SMSetCharacterPrivatePoints(owner));
        return null;
    }
}
