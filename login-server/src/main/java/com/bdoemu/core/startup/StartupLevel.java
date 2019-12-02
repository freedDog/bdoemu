package com.bdoemu.core.startup;

import com.bdoemu.commons.utils.ServerInfoUtils;
import com.bdoemu.commons.utils.versioning.Version;
import com.bdoemu.core.network.LoginNetworkThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;

/**
 * @ClassName StartupLevel
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/26 17:40
 * VERSION 1.0
 */

public enum  StartupLevel implements IStartupLevel{
    /***/
    BeforeStart {
        @Override
        public void invokeDepends() {
            Version.getInstance().init(this.getClass());
        }
    },
    Configure {
        @Override
        public void invokeDepends() {

        }
    },

    Threading {
        @Override
        public void invokeDepends() {

        }
    },
    Database {
        @Override
        public void invokeDepends() {

        }
    },
    Service {
        @Override
        public void invokeDepends() {

        }
    },
    Data {
        @Override
        public void invokeDepends() {

        }
    },
    World {
        @Override
        public void invokeDepends() {

        }
    },
    Network {
        @Override
        public void invokeDepends() {

        }
    },
    AfterStart {
        @Override
        public void invokeDepends() {
            System.gc();
            System.runFinalization();
            for (final String line : ServerInfoUtils.getMemUsage()) {
                StartupLevel.log.info(line);
            }
            try{
                LoginNetworkThread.getInstance().startup();
            }catch (Exception e){
                StartupLevel.log.error("Error while starting network thread", e);
            }
            StartupLevel.log.info("Server loaded in {} millisecond(s).", ServerInfoUtils.formatNumber(ManagementFactory.getRuntimeMXBean().getUptime()));
        }
    } ;
    private static final Logger log= LoggerFactory.getLogger(StartupLevel.class);
}
