package com.bdoemu.gameserver.model.actions.enums;

/**
 * @ClassName EActionEffectType
 * @Description  动作效果类型
 * @Author JiangBangMing
 * @Date 2019/7/6 14:38
 * VERSION 1.0
 */
public enum EActionEffectType {
    /**无*/
    None(0),
    /**移动时不要使用耐力*/
    NotUseStaminaInMove(1);

    private int state;

    EActionEffectType(final int state) {
        this.state = state;
    }

    public int getState() {
        return this.state;
    }
}
