package com.bdoemu.gameserver.model.creature.player.titles.observers;

/**
 * @ClassName TitleLevelUpObserver
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 20:57
 * VERSION 1.0
 */

public class TitleLevelUpObserver extends ATitleObserver{
    @Override
    public boolean canReward(final Object... params) {
        return (int) params[0] == this.template.getParameter1();
    }

    @Override
    public Object getKey() {
        return this.template.getParameter1();
    }
}
