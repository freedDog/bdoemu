package com.bdoemu.core.configs;

import com.bdoemu.commons.config.annotation.ConfigFile;
import com.bdoemu.commons.config.annotation.ConfigProperty;

/**
 * @ClassName ThreadPoolConfig
 * @Description 线程池配置
 * @Author JiangBangMing
 * @Date 2019/6/22 11:40
 * VERSION 1.0
 */

@ConfigFile(name = "configs/threadpool.properties")
public class ThreadPoolConfig {

    @ConfigProperty(name = "effects.core.pool.size", value = "10")
    public static int EFFECTS_CORE_POOL_SIZE;

    @ConfigProperty(name = "general.core.pool.size", value = "15")
    public static int GENERAL_CORE_POOL_SIZE;

    @ConfigProperty(name = "ai.core.pool.size", value = "30")
    public static int AI_CORE_POOL_SIZE;
}
