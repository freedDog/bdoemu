package com.bdoemu.gameserver.model.actions.actioncharts;

import com.bdoemu.gameserver.model.actions.ADefaultAction;
import com.bdoemu.gameserver.model.actions.templates.ActionChartActionT;
import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.skills.services.SkillService;

/**
 * @ClassName SkillAction
 * @Description  技能东西
 * @Author JiangBangMing
 * @Date 2019/7/11 17:24
 * VERSION 1.0
 */

public class SkillAction extends ADefaultAction {

    public SkillAction(final ActionChartActionT actionChartActionT) {
        super(actionChartActionT);
    }

    @Override
    public boolean canAct() {
        if (this.owner.isPlayer()) {
            final int skillId = this.actionChartActionT.getSkillId();
            if (skillId != 0) {
                final Player player = (Player) this.owner;
                if (!player.getSkillList().containsSkill(skillId)) {
                    return false;
                }
                if (!SkillService.executeSkill(player, skillId)) {
                    return false;
                }
            }
        }
        return super.canAct();
    }
}
