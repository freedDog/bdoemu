package com.bdoemu.gameserver.model.actions.actioncharts;

import com.bdoemu.gameserver.model.actions.ADefaultAction;
import com.bdoemu.gameserver.model.actions.templates.ActionChartActionT;
import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.creature.servant.Servant;

/**
 * @ClassName RideOffPrepareAction
 * @Description 岔开去准备
 * @Author JiangBangMing
 * @Date 2019/7/11 17:47
 * VERSION 1.0
 */

public class RideOffPrepareAction extends ADefaultAction {

    private Servant servant;

    public RideOffPrepareAction(final ActionChartActionT actionChartActionT) {
        super(actionChartActionT);
    }

    @Override
    public void init() {
        super.init();
        final Player player = (Player) this.owner;
        this.servant.unMount(player);
    }

    @Override
    public boolean canAct() {
        final Player player = (Player) this.owner;
        this.servant = player.getCurrentVehicle();
        return this.servant != null && super.canAct() && this.servant.getGameObjectId() == this.targetGameObjId;
    }
}
