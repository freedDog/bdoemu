package com.bdoemu.gameserver.model.skills.buffs.effects;

import com.bdoemu.gameserver.model.creature.Creature;
import com.bdoemu.gameserver.model.skills.buffs.ABuffEffect;
import com.bdoemu.gameserver.model.skills.buffs.ActiveBuff;
import com.bdoemu.gameserver.model.skills.buffs.templates.BuffTemplate;
import com.bdoemu.gameserver.model.skills.templates.SkillT;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @ClassName HideActionEffect
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/11 20:53
 * VERSION 1.0
 */

public class HideActionEffect extends ABuffEffect {
    @Override
    public Collection<ActiveBuff> initEffect(final Creature owner, final Collection<? extends Creature> targets, final SkillT skillT, final BuffTemplate buffTemplate) {
        return targets.stream().map(target -> new ActiveBuff(skillT, buffTemplate, owner, target, null)).collect(Collectors.toList());
    }
}
