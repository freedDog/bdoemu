package com.bdoemu.gameserver.model.creature.services;

import com.bdoemu.commons.thread.APeriodicTaskService;
import com.bdoemu.commons.utils.Rnd;
import com.bdoemu.core.startup.StartupComponent;
import com.bdoemu.gameserver.model.creature.Creature;
import com.bdoemu.gameserver.model.creature.DeadBody;
import com.bdoemu.gameserver.model.creature.collect.Collect;
import com.bdoemu.gameserver.model.creature.collect.templates.CollectTemplate;
import com.bdoemu.gameserver.model.creature.templates.CreatureTemplate;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RespawnService
 * @Description     刷新孵化器服务
 * @Author JiangBangMing
 * @Date 2019/7/8 20:59
 * VERSION 1.0
 */

@StartupComponent("Service")
public class RespawnService extends APeriodicTaskService {
    private static class Holder {
        static final RespawnService INSTANCE = new RespawnService();
    }
    private ConcurrentHashMap<Integer, Creature> map;

    private RespawnService() {
        super(1L, TimeUnit.SECONDS);
        this.map = new ConcurrentHashMap<>();
    }

    public static RespawnService getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void run() {
        this.map.values().removeIf(Creature::isTimeToRespawn);
    }

    public void putBody(final DeadBody deadBody) {
        if (deadBody.canRespawn()) {
            final CreatureTemplate template = deadBody.getTemplate();
            deadBody.setRespawnTime(System.currentTimeMillis() + template.getSpawnDelayTime() + Rnd.get(0, template.getSpawnVariableTime()));
            this.map.put(deadBody.getGameObjectId(), deadBody);
        }
    }

    public void putCollect(final Collect collect) {
        final CollectTemplate template = collect.getCollectTemplate();
        collect.setRespawnTime(System.currentTimeMillis() + template.getSpawnDelayTime() + Rnd.get(0, template.getSpawnVariableTime()));
        this.map.put(collect.getGameObjectId(), collect);
    }
}
