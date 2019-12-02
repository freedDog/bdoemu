package com.bdoemu.gameserver.model.actions.actioncharts;

import com.bdoemu.gameserver.model.actions.ADefaultAction;
import com.bdoemu.gameserver.model.actions.templates.ActionChartActionT;
import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.creature.servant.enums.ERidingSlotType;

/**
 * @ClassName RideOnFailedAction
 * @Description 骑在失败
 * @Author JiangBangMing
 * @Date 2019/7/11 17:46
 * VERSION 1.0
 */

public class RideOnFailedAction extends ADefaultAction {

    public RideOnFailedAction(final ActionChartActionT actionChartActionT) {
        super(actionChartActionT);
    }

    @Override
    public void init() {
        super.init();
        final Player player = (Player) this.owner;
        player.setCurrentVehicle(null, ERidingSlotType.None);
    }
}
