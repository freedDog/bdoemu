package com.bdoemu.core.configs;

import com.bdoemu.commons.config.annotation.ConfigComments;
import com.bdoemu.commons.config.annotation.ConfigFile;
import com.bdoemu.commons.config.annotation.ConfigProperty;
import com.bdoemu.commons.model.enums.EAccessLevel;
import com.bdoemu.commons.model.enums.EGameServiceType;

/**
 * @ClassName LoginConfig
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/26 11:54
 * VERSION 1.0
 */

@ConfigFile(name = "configs/login.properties")
public class LoginConfig {
    @ConfigComments(comment = { "Login server service type.", "Options: DEV, KOR_REAL, NA_REAL, RUS_REAL, JPN_REAL" })
    @ConfigProperty(name = "login.game.service.type", value = "NA_REAL")
    public static EGameServiceType GAME_SERVICE_TYPE;
    @ConfigComments(comment = { "Automatic registration for accounts.", "Default: false" })
    @ConfigProperty(name = "login.autoregistration.accounts", value = "false")
    public static boolean AUTO_REGISTRATION;
    @ConfigComments(comment = { "Enable auth by token from web.", "Default: true" })
    @ConfigProperty(name = "login.server.token.auth.enabled", value = "true")
    public static boolean TOKEN_AUTH_ENABLED;
    @ConfigComments(comment = { "Enable auth by email database field.", "Default: false" })
    @ConfigProperty(name = "login.server.auth.by.email", value = "false")
    public static boolean AUTH_BY_EMAIL;
    @ConfigComments(comment = { "Max age of auth token (in minutes)", "Default: 5" })
    @ConfigProperty(name = "login.server.token.max.age", value = "5")
    public static int TOKEN_AUTH_MAX_AGE;
    @ConfigComments(comment = { "Show special bonuses on server list.", "Default: 0", "Exp Bonus: 1", "Drop Bonus: 2", "Exp + Drop: 3" })
    @ConfigProperty(name = "login.server.bonuses.value", value = "0")
    public static byte SERVER_BONUSES_VALUE;
    @ConfigComments(comment = { "Show special golden medal on server list.", "Default: false" })
    @ConfigProperty(name = "login.server.show.golden.medal", value = "false")
    public static boolean SERVER_GOLDEN_MEDAL;
    @ConfigComments(comment = { "Min access level for login.", "Default: USER" })
    @ConfigProperty(name = "login.connect.access.level", value = "USER")
    public static EAccessLevel CONNECT_ACCESS_LEVEL;
}
