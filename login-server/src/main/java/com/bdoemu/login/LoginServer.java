package com.bdoemu.login;

import com.bdoemu.core.startup.StartupLevel;
import com.bdoemu.core.startup.StartupManager;
import com.bdoemu.login.service.LoginServerRMI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName LoginServer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/26 12:01
 * VERSION 1.0
 */

public class LoginServer {
    private static final Logger log= LoggerFactory.getLogger(LoginServer.class);
    private static LoginServerRMI rmi;

    public static void main(String[] args){
        try{
            StartupManager.getInstance().startup(StartupLevel.class);
            LoginServer.rmi=new LoginServerRMI();
        }catch (Exception ex){
            log.error("");
        }
    }

    public static LoginServerRMI getRmi() {
        return LoginServer.rmi;
    }

}
