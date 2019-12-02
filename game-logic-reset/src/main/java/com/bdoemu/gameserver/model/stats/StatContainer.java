package com.bdoemu.gameserver.model.stats;

import com.bdoemu.gameserver.model.stats.enums.StatEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName StatContainer
 * @Description  状态容器
 * @Author JiangBangMing
 * @Date 2019/7/6 10:44
 * VERSION 1.0
 */

public class StatContainer {

    private Map<StatEnum, Stat> container;

    public StatContainer() {
        this.container = new HashMap<>();
    }

    public void addStat(final StatEnum type, final Stat stat) {
        this.container.put(type, stat);
    }

    public Stat getStat(final StatEnum type) {
        return this.container.get(type);
    }
}
