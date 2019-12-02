package com.bdoemu.login.service;

import com.bdoemu.commons.model.xmlrpc.impl.XmlRpcAuthTokenData;
import com.bdoemu.core.startup.StartupComponent;
import com.bdoemu.login.models.AuthTokenData;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AuthTokenService
 * @Description 身份验证令牌服务
 * @Author JiangBangMing
 * @Date 2019/6/26 19:45
 * VERSION 1.0
 */

@StartupComponent("Service")
public class AuthTokenService {
    private static Map<String, AuthTokenData> activeTokens=new HashMap<>();

    public XmlRpcAuthTokenData generateTokenData(final String s) {
        this.removeTokenData(s);
        final AuthTokenData authTokenData = new AuthTokenData();
        // invokedynamic(\ua0c8:(Lcom/bdoemu/login/models/AuthTokenData;Ljava/lang/String;)V, authTokenData, s)
        // invokedynamic(\u45d6:(Lcom/bdoemu/login/models/AuthTokenData;J)V, authTokenData, invokedynamic(\u50c6:()J) + (long)LoginConfig.TOKEN_AUTH_MAX_AGE * 60 * 1000)
        // invokedynamic(\u2185:(Lcom/bdoemu/login/models/AuthTokenData;)V, authTokenData)
        AuthTokenService.activeTokens.put(authTokenData.getAccountName(),authTokenData);
        return null;
    }

    public AuthTokenData getTokenData(final String s) {
        for (final Map.Entry<String, AuthTokenData> entry : AuthTokenService.activeTokens.entrySet()) {
        }
        return null;
    }

    public void removeTokenData(final String s) {
        AuthTokenService.activeTokens.values().removeIf(authTokenData -> authTokenData.getAccountName().equals(s));
    }

    public static AuthTokenService getInstance() {
        return Holder.INSTANCE;
    }


    private static class Holder {
        static final AuthTokenService INSTANCE = new AuthTokenService();
    }

}
