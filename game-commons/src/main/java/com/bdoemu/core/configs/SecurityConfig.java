package com.bdoemu.core.configs;

import com.bdoemu.commons.config.annotation.ConfigFile;
import com.bdoemu.commons.config.annotation.ConfigProperty;

/**
 * @ClassName SecurityConfig
 * @Description 安全配置
 * @Author JiangBangMing
 * @Date 2019/6/24 20:11
 * VERSION 1.0
 */

@ConfigFile(name = "configs/security.properties")
public class SecurityConfig {
    @ConfigProperty(name = "ban.system.enable", value = "true")
    public static boolean BAN_SYSTEM_ENABLE;
    @ConfigProperty(name = "ban.system.announce.punishment", value = "true")
    public static boolean BAN_SYSTEM_ANNOUNCE_PUNISHMENT;
}
