package com.bdoemu;

import com.bdoemu.core.startup.StartupLevel;
import com.bdoemu.core.startup.StartupManager;
import com.bdoemu.gameserver.service.GameServerRMI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName MainServer
 * @Description  启动类
 * @Author JiangBangMing
 * @Date 2019/7/7 20:58
 * VERSION 1.0
 */

public class MainServer {

    private static final Logger log = LoggerFactory.getLogger(MainServer.class);
    private static GameServerRMI rmi;

    private MainServer() throws Exception {
        StartupManager.getInstance().startup(StartupLevel.class);
        MainServer.rmi = new GameServerRMI();
    }

    public static void main(final String[] args) {
        try {
            new MainServer();
        } catch (Exception ex) {
            MainServer.log.error("Error while starting MainServer", ex);
        }
    }

    public static GameServerRMI getRmi() {
        return MainServer.rmi;
    }
}
