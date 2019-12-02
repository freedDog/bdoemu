package com.bdoemu.gameserver.model.ai.deprecated;

/**
 * @ClassName EAIBehavior
 * @Description 人工智能行为
 * @Author JiangBangMing
 * @Date 2019/7/6 15:01
 * VERSION 1.0
 */

public enum  EAIBehavior {

    /**闲置*/
    idle,
    /**死亡*/
    dead,
    /**回击*/
    knockback,
    /**无*/
    none,
    /**转向*/
    turn,
    /**恢复*/
    recovery,
    /**追击*/
    chase,
    /**围绕*/
    around,
    /**行动*/
    action,
    /**复活*/
    revive,
    /**逃跑*/
    escape,
    /**不行*/
    walk,
    /**返回*/
    return_,
    /**技能*/
    skill,
    /**跟踪*/
    trace,
    /**攻击*/
    attack;

    public boolean isReturn() {
        return this == EAIBehavior.return_;
    }

    public boolean isChase() {
        return this == EAIBehavior.chase;
    }

    public boolean isKnockback() {
        return this == EAIBehavior.knockback;
    }

    public boolean isNoDelayBehavior() {
        return this != EAIBehavior.knockback && this != EAIBehavior.skill && this != EAIBehavior.action;
    }
}
