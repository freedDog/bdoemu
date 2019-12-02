package com.bdoemu.gameserver.model.actions.actioncharts;

import com.bdoemu.gameserver.model.actions.ADefaultAction;
import com.bdoemu.gameserver.model.actions.templates.ActionChartActionT;
import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.skills.buffs.ActiveBuff;

import java.util.Collection;

/**
 * @ClassName FinishUseInstallationAction
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/11 20:10
 * VERSION 1.0
 */

public class FinishUseInstallationAction extends ADefaultAction {

    public FinishUseInstallationAction(final ActionChartActionT actionChartActionT) {
        super(actionChartActionT);
    }

    @Override
    public void init() {
        super.init();
        final Player player = (Player) this.owner;
        final Collection<ActiveBuff> activeBuffs = player.getInstallationActiveBuffs();
        if (activeBuffs != null) {
            player.setInstallationActiveBuffs(null);
            activeBuffs.forEach(ActiveBuff::endEffect);
        }
    }
}
