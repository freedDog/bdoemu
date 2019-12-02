package com.bdoemu.gameserver.model.actions.actioncharts;

import com.bdoemu.gameserver.model.actions.ADefaultAction;
import com.bdoemu.gameserver.model.actions.templates.ActionChartActionT;
import com.bdoemu.gameserver.model.creature.player.Player;

/**
 * @ClassName TamingEndAction
 * @Description  驯服结束动作
 * @Author JiangBangMing
 * @Date 2019/7/11 18:28
 * VERSION 1.0
 */

public class TamingEndAction extends ADefaultAction {

    public TamingEndAction(final ActionChartActionT actionChartActionT) {
        super(actionChartActionT);
    }

    @Override
    public void init() {
        super.init();
        final Player player = (Player) this.getOwner();
        player.getServantController().setTameServant(null);
    }
}
