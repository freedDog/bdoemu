package com.bdoemu.gameserver.model.creature.player.titles.observers;

/**
 * @ClassName TitleQuestCompleteObserver
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 20:58
 * VERSION 1.0
 */

public class TitleQuestCompleteObserver extends ATitleObserver{

    @Override
    public boolean canReward(final Object... params) {
        return (int) params[0] == this.template.getParameter1() && (int) params[1] == this.template.getParameter2();
    }

    @Override
    public Object getKey() {
        return this.template.getParameter1();
    }
}
