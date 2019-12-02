package com.bdoemu.gameserver.model.creature.player.titles.observers;

/**
 * @ClassName TitleKnowledgeObserver
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 20:53
 * VERSION 1.0
 */

public class TitleKnowledgeObserver extends ATitleObserver{

    @Override
    public boolean canReward(final Object... params) {
        return (int) params[1] <= this.template.getParameter1() && (int) params[1] == this.template.getParameter1();
    }

    @Override
    public Object getKey() {
        return this.template.getParameter1();
    }
}
