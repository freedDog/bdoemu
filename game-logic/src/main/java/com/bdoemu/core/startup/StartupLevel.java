package com.bdoemu.core.startup;

import com.bdoemu.commons.utils.ServerInfoUtils;
import com.bdoemu.commons.utils.versioning.Version;
import com.bdoemu.core.network.GameNetworkThread;
import com.bdoemu.gameserver.model.creature.services.SpawnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;

public enum StartupLevel implements IStartupLevel {

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
            SpawnService.getInstance().spawnAll();
            System.gc();
            System.runFinalization();
            for (final String line : ServerInfoUtils.getMemUsage()) {
                StartupLevel.log.info(line);
            }
            try {
                GameNetworkThread.getInstance().startup();
            }
            catch (Exception ex2) {
                StartupLevel.log.error("Error while starting network thread", (Throwable)ex2);
            }
            StartupLevel.log.info("Server loaded in {} millisecond(s).", ServerInfoUtils.formatNumber(ManagementFactory.getRuntimeMXBean().getUptime()));
        }
    };
    private static final Logger log = LoggerFactory.getLogger(StartupLevel.class);

}
