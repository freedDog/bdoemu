package com.bdoemu.gameserver.model.skills.buffs.effects;

import com.bdoemu.gameserver.model.creature.Creature;
import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.creature.player.answer.SkillAnswer;
import com.bdoemu.gameserver.model.skills.buffs.ABuffEffect;
import com.bdoemu.gameserver.model.skills.buffs.ActiveBuff;
import com.bdoemu.gameserver.model.skills.buffs.templates.BuffTemplate;
import com.bdoemu.gameserver.model.skills.templates.SkillT;

import java.util.Collection;

/**
 * @ClassName RevivalOthersEffect
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/12 10:15
 * VERSION 1.0
 */

public class RevivalOthersEffect extends ABuffEffect {
    @Override
    public Collection<ActiveBuff> initEffect(final Creature owner, final Collection<? extends Creature> targets, final SkillT skillT, final BuffTemplate buffTemplate) {
        for (final Creature target : targets) {
            if (target.isPlayer() && target.isDead()) {
                final Player player = (Player) target;
                final ActiveBuff activeBuff = new ActiveBuff(skillT, buffTemplate, owner, target);
                player.getAnswerStorage().setSkillAnswer(new SkillAnswer(activeBuff));
//                player.sendPacket(new SMAnswerSkill(owner.getGameObjectId()));
            }
        }
        return null;
    }

    @Override
    public void applyEffect(final ActiveBuff activeBuff) {
        final Creature target = activeBuff.getTarget();
        final Integer[] params = activeBuff.getTemplate().getParams();
        final int hpPercentage = params[0] / 10000;
        final int mpPercentage = params[1] / 10000;
        if (target.isDead()) {
            final Player player = (Player) target;
            player.getAggroList().clear(true);
            player.getAi().notifyStart();
            player.revive(hpPercentage, mpPercentage);
        }
    }
}
