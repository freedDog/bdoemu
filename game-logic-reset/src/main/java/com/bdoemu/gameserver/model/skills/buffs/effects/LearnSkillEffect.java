package com.bdoemu.gameserver.model.skills.buffs.effects;

import com.bdoemu.gameserver.model.creature.Creature;
import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.skills.buffs.ABuffEffect;
import com.bdoemu.gameserver.model.skills.buffs.ActiveBuff;
import com.bdoemu.gameserver.model.skills.buffs.templates.BuffTemplate;
import com.bdoemu.gameserver.model.skills.templates.SkillT;

import java.util.Collection;

/**
 * @ClassName LearnSkillEffect
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/11 20:30
 * VERSION 1.0
 */

public class LearnSkillEffect extends ABuffEffect {
    @Override
    public Collection<ActiveBuff> initEffect(final Creature owner, final Collection<? extends Creature> targets, final SkillT skillT, final BuffTemplate buffTemplate) {
        final Integer[] params = buffTemplate.getParams();
        final int skillId = params[0];
        final int unk = params[1];
        final int skillLevel = params[2];
        if (owner.isPlayer()) {
            final Player player = (Player) owner;
            player.getSkillList().learnSkill(skillId, true);
        }
        return null;
    }
}
