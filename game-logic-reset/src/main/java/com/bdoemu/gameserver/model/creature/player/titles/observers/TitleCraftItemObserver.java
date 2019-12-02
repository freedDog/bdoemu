package com.bdoemu.gameserver.model.creature.player.titles.observers;

/**
 * @ClassName TitleCraftItemObserver
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 20:56
 * VERSION 1.0
 */

public class TitleCraftItemObserver extends ATitleObserver{
    @Override
    public boolean canReward(final Object... params) {
        return (int) params[0] == this.template.getParameter1();
    }

    @Override
    public Object getKey() {
        return this.template.getParameter1();
    }
}
