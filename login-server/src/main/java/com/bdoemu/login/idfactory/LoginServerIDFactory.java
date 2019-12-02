package com.bdoemu.login.idfactory;

import com.bdoemu.commons.idfactory.IDFactory;
import com.bdoemu.commons.idfactory.IDStorage;
import com.bdoemu.core.startup.StartupComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName LoginServerIDFactory
 * @Description 登陆服id 工厂
 * @Author JiangBangMing
 * @Date 2019/6/26 19:32
 * VERSION 1.0
 */

@StartupComponent("Database")
public class LoginServerIDFactory extends IDFactory<LSIDStorageType> {
    private static final Logger log= LoggerFactory.getLogger(LoginServerIDFactory.class);

    @Override
    public void init() {
        for(LSIDStorageType type:LSIDStorageType.values()){
            this.storages.put(type,new IDStorage(type.getMinId(),type.getMaxId(),LSIDStorageType.class.getSimpleName()));
        }
    }

    @Override
    public void report() {
        for(LSIDStorageType storageType:LSIDStorageType.values()){
            IDStorage storage=this.storages.get(storageType);
            log.info("init{} objectidTypes used count: {}",storageType,storage.getUsedCount());
        }
    }

    public static LoginServerIDFactory getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        static final LoginServerIDFactory INSTANCE = new LoginServerIDFactory();
    }
}
