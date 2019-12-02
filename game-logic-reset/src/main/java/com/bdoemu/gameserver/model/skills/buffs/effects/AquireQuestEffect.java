package com.bdoemu.gameserver.model.skills.buffs.effects;

import com.bdoemu.gameserver.model.creature.Creature;
import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.skills.buffs.ABuffEffect;
import com.bdoemu.gameserver.model.skills.buffs.ActiveBuff;
import com.bdoemu.gameserver.model.skills.buffs.templates.BuffTemplate;
import com.bdoemu.gameserver.model.skills.templates.SkillT;

import java.util.Collection;

/**
 * @ClassName AquireQuestEffect
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/11 21:04
 * VERSION 1.0
 */

public class AquireQuestEffect extends ABuffEffect {

    @Override
    public Collection<ActiveBuff> initEffect(final Creature owner, final Collection<? extends Creature> targets, final SkillT skillT, final BuffTemplate buffTemplate) {
        if (owner.isPlayer()) {
            final Player player = (Player) owner;
            final Integer[] params = buffTemplate.getParams();
            final int groupQuestId = params[0];
            final int questId = params[1];
            player.getPlayerQuestHandler().acceptQuest(groupQuestId, questId);
        }
        return null;
    }
}
