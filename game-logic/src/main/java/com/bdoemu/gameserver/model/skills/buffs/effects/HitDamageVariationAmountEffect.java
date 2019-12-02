// 
// Decompiled by Procyon v0.5.30
// 

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

public class HitDamageVariationAmountEffect extends ABuffEffect {
    @Override
    public Collection<ActiveBuff> initEffect(final Creature owner, final Collection<? extends Creature> targets, final SkillT skillT, final BuffTemplate buffTemplate) {
        final List<ActiveBuff> buffs = new ArrayList<ActiveBuff>();
        final Integer[] params = buffTemplate.getParams();
        final int accuracyValue = params[1];
        final BuffElement element = new BuffElement(accuracyValue);
        for (final Creature target : targets) {
            buffs.add(new ActiveBuff(skillT, buffTemplate, owner, target, element));
        }
        return buffs;
    }

    @Override
    public void applyEffect(final ActiveBuff activeBuff) {
        final Creature target = activeBuff.getTarget();
        final int type = activeBuff.getTemplate().getParams()[0];
        final BuffElement element = activeBuff.getElement();
        switch (type) {
            case 0: {
                target.getGameStats().getDHIT().addElement(element);
                break;
            }
            case 1: {
                target.getGameStats().getRHIT().addElement(element);
                break;
            }
            case 2: {
                target.getGameStats().getMHIT().addElement(element);
                break;
            }
            case 3: {
                target.getGameStats().getDHIT().addElement(element);
                target.getGameStats().getRHIT().addElement(element);
                target.getGameStats().getMHIT().addElement(element);
                break;
            }
        }
    }

    @Override
    public void endEffect(final ActiveBuff activeBuff) {
        final Creature target = activeBuff.getTarget();
        final int type = activeBuff.getTemplate().getParams()[0];
        final BuffElement element = activeBuff.getElement();
        switch (type) {
            case 0: {
                target.getGameStats().getDHIT().removeElement(element);
                break;
            }
            case 1: {
                target.getGameStats().getRHIT().removeElement(element);
                break;
            }
            case 2: {
                target.getGameStats().getMHIT().removeElement(element);
                break;
            }
            case 3: {
                target.getGameStats().getDHIT().removeElement(element);
                target.getGameStats().getRHIT().removeElement(element);
                target.getGameStats().getMHIT().removeElement(element);
                break;
            }
        }
    }
}
