package com.bdoemu.gameserver.model.skills.buffs.effects;

import com.bdoemu.gameserver.model.creature.Creature;
import com.bdoemu.gameserver.model.skills.buffs.ABuffEffect;
import com.bdoemu.gameserver.model.skills.buffs.ActiveBuff;
import com.bdoemu.gameserver.model.skills.buffs.templates.BuffTemplate;
import com.bdoemu.gameserver.model.skills.templates.SkillT;
import com.bdoemu.gameserver.model.stats.elements.BuffElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName RegenMpVariationAmountEffect
 * @Description 回复Mp变化量效果
 * @Author JiangBangMing
 * @Date 2019/7/11 20:26
 * VERSION 1.0
 */

public class RegenMpVariationAmountEffect extends ABuffEffect {
    @Override
    public Collection<ActiveBuff> initEffect(final Creature owner, final Collection<? extends Creature> targets, final SkillT skillT, final BuffTemplate buffTemplate) {
        final List<ActiveBuff> buffs = new ArrayList<ActiveBuff>();
        final Integer[] params = buffTemplate.getParams();
        final int mpRegenCount = params[0];
        final BuffElement element = new BuffElement(mpRegenCount);
        for (final Creature target : targets) {
            buffs.add(new ActiveBuff(skillT, buffTemplate, owner, target, element));
        }
        return buffs;
    }

    @Override
    public void applyEffect(final ActiveBuff activeBuff) {
        final Creature target = activeBuff.getTarget();
        target.getGameStats().getMPRegen().addElement(activeBuff.getElement());
        target.getGameStats().getMp().startMpRegenTask();
    }

    @Override
    public void endEffect(final ActiveBuff activeBuff) {
        activeBuff.getTarget().getGameStats().getMPRegen().removeElement(activeBuff.getElement());
    }
}
