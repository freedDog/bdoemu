package com.bdoemu.gameserver.model.creature.servant.tasks;

import com.bdoemu.gameserver.model.creature.servant.Servant;

/**
 * @ClassName PetHungryTask
 * @Description 宠物饥饿任务
 * @Author JiangBangMing
 * @Date 2019/7/10 17:40
 * VERSION 1.0
 */

public class PetHungryTask implements Runnable{
    private Servant servant;

    public PetHungryTask(final Servant servant) {
        this.servant = servant;
    }

    @Override
    public void run() {
        this.servant.setHunger(Math.max(this.servant.getHunger() - 2, 0));
//        this.servant.getOwner().sendPacket(new SMUpdatePetHungry());
    }
}
