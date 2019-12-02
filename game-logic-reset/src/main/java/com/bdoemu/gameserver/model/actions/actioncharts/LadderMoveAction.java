package com.bdoemu.gameserver.model.actions.actioncharts;

import com.bdoemu.gameserver.model.actions.ADefaultAction;
import com.bdoemu.gameserver.model.actions.templates.ActionChartActionT;
import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.creature.servant.Servant;

/**
 * @ClassName LadderMoveAction
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/11 17:51
 * VERSION 1.0
 */

public class LadderMoveAction extends ADefaultAction {
    private Servant servant;

    public LadderMoveAction(final ActionChartActionT actionChartActionT) {
        super(actionChartActionT);
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public boolean canAct() {
        final Player player = (Player) this.owner;
        this.servant = player.getCurrentVehicle();
        return this.servant != null && super.canAct();
    }
}
