package com.bdoemu.gameserver.model.skills.buffs.effects;

import com.bdoemu.gameserver.model.creature.Creature;
import com.bdoemu.gameserver.model.skills.buffs.ABuffEffect;
import com.bdoemu.gameserver.model.skills.buffs.ActiveBuff;
import com.bdoemu.gameserver.model.skills.buffs.templates.BuffTemplate;
import com.bdoemu.gameserver.model.skills.templates.SkillT;

import java.util.Collection;

/**
 * @ClassName ActiveRegenSubResourcePointVariationAmountEffect
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/12 9:57
 * VERSION 1.0
 */

public class ActiveRegenSubResourcePointVariationAmountEffect extends ABuffEffect {
    @Override
    public Collection<ActiveBuff> initEffect(final Creature owner, final Collection<? extends Creature> targets, final SkillT skillT, final BuffTemplate buffTemplate) {
        Integer[] params = buffTemplate.getParams();
        owner.getGameStats().getSubResourcePointStat().addSubResourcePoints(params[0]);
        return null;
    }
}
