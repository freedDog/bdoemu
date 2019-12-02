package com.bdoemu.commons.xmlrpc;

import com.bdoemu.commons.model.xmlrpc.XmlRpcMessage;
import com.bdoemu.commons.model.xmlrpc.impl.XmlRpcAccount;
import com.bdoemu.commons.model.xmlrpc.impl.XmlRpcAuthTokenData;
import com.bdoemu.commons.model.xmlrpc.impl.XmlRpcGuild;
import com.bdoemu.commons.model.xmlrpc.impl.XmlRpcHardwareInfo;
import com.bdoemu.commons.model.xmlrpc.impl.XmlRpcLog;
import com.bdoemu.commons.model.xmlrpc.impl.XmlRpcOnline;
import com.bdoemu.commons.model.xmlrpc.impl.XmlRpcPayment;
import com.bdoemu.commons.model.xmlrpc.impl.XmlRpcPlayer;
import com.bdoemu.commons.xmlrpc.client.AsyncCallback;
import com.bdoemu.commons.xmlrpc.client.XmlRpcClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * @ClassName XmlRpcServiceConnection
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 21:29
 * VERSION 1.0
 */

public abstract class XmlRpcServiceConnection {


    private static final Logger log;
    protected static final TypeReference<XmlRpcMessage> XML_RPC_MESSAGE_REFERENCE;
    protected static final TypeReference<XmlRpcAccount> XML_RPC_ACCOUNT_REFERENCE;
    protected static final TypeReference<XmlRpcPayment> XML_RPC_PAYMENT_REFERENCE;
    protected static final TypeReference<XmlRpcLog> XML_RPC_LOG_REFERENCE;
    protected static final TypeReference<XmlRpcHardwareInfo> XML_RPC_HARDWARE_INFO_REFERENCE;
    protected static final TypeReference<ArrayList<XmlRpcPayment>> XML_RPC_PAYMENT_LIST_REFERENCE;
    protected static final TypeReference<ArrayList<XmlRpcGuild>> XML_RPC_GUILD_LIST_REFERENCE;
    protected static final TypeReference<ArrayList<XmlRpcPlayer>> XML_RPC_PLAYER_LIST_REFERENCE;
    protected static final TypeReference<ArrayList<XmlRpcLog>> XML_RPC_LOG_LIST_REFERENCE;
    protected static final TypeReference<XmlRpcOnline> XML_RPC_ONLINE_REFERENCE;
    protected static final TypeReference<XmlRpcAuthTokenData> XML_RPC_AUTHTOKEN_DATA;
    protected static final TypeReference<Long> LONG_REFERENCE;
    protected static final TypeReference<Double> DOUBLE_REFERENCE;

    protected <T> T unJson(final String jsonPacket, final TypeReference<T> type) {
        T data = null;
        try {
            data = (T)new ObjectMapper().readValue(jsonPacket, (TypeReference)type);
        }
        catch (Exception ex) {}
        return data;
    }

    protected <T> T execute(final String methodName, final Object[] params, final TypeReference<T> reference) {
        try {
            final String fullHandlerName = this.getXmlRpcHandlerName().concat(".").concat(methodName);
            Object result = XmlRpcClientCacheService.getInstance().getCache(fullHandlerName, params);
            if (result == null) {
                result = this.getClient().execute(fullHandlerName, params);
                if (this.isCacheEnabled() && result != null) {
                    XmlRpcClientCacheService.getInstance().addCache(fullHandlerName, params, result);
                }
            }
            if (result != null) {
                return this.unJson((String)result, reference);
            }
        }
        catch (Exception e) {
            XmlRpcServiceConnection.log.error("Exception while calling {}.{}:{}", new Object[] { this.getXmlRpcHandlerName(), methodName, e.getLocalizedMessage(), e });
            return null;
        }
        return null;
    }

    protected String executeString(final String methodName, final Object[] params) {
        try {
            final Object result = this.getClient().execute(this.getXmlRpcHandlerName().concat(".").concat(methodName), params);
            if (result != null) {
                return (String)result;
            }
        }
        catch (Exception e) {
            XmlRpcServiceConnection.log.error("Exception while calling {}.{}:{}", new Object[] { this.getXmlRpcHandlerName(), methodName, e.getLocalizedMessage(), e });
            return null;
        }
        return null;
    }

    protected void executeAsync(final String methodName, final Object[] params, final AsyncCallback callback) {
        try {
            this.getClient().executeAsync(this.getXmlRpcHandlerName().concat(".").concat(methodName), params, callback);
        }
        catch (Exception e) {
            XmlRpcServiceConnection.log.error("Exception while calling {}.{}:{}", new Object[] { this.getXmlRpcHandlerName(), methodName, e.getLocalizedMessage(), e });
        }
    }

    protected boolean executeBoolean(final String methodName, final Object[] params, final AsyncCallback callback) {
        try {
            final Object result = this.getClient().execute(this.getXmlRpcHandlerName().concat(".").concat(methodName), params);
            if (result != null) {
                return (boolean)result;
            }
        }
        catch (Exception e) {
            XmlRpcServiceConnection.log.error("Exception while calling {}.{}:{}", new Object[] { this.getXmlRpcHandlerName(), methodName, e.getLocalizedMessage(), e });
        }
        return false;
    }

    protected boolean isCacheEnabled() {
        return true;
    }

    protected abstract XmlRpcClient getClient();

    protected abstract String getXmlRpcHandlerName();

    static {
        log = LoggerFactory.getLogger((Class)XmlRpcServiceConnection.class);
        XML_RPC_MESSAGE_REFERENCE = new TypeReference<XmlRpcMessage>() {};
        XML_RPC_ACCOUNT_REFERENCE = new TypeReference<XmlRpcAccount>() {};
        XML_RPC_PAYMENT_REFERENCE = new TypeReference<XmlRpcPayment>() {};
        XML_RPC_LOG_REFERENCE = new TypeReference<XmlRpcLog>() {};
        XML_RPC_HARDWARE_INFO_REFERENCE = new TypeReference<XmlRpcHardwareInfo>() {};
        XML_RPC_PAYMENT_LIST_REFERENCE = new TypeReference<ArrayList<XmlRpcPayment>>() {};
        XML_RPC_GUILD_LIST_REFERENCE = new TypeReference<ArrayList<XmlRpcGuild>>() {};
        XML_RPC_PLAYER_LIST_REFERENCE = new TypeReference<ArrayList<XmlRpcPlayer>>() {};
        XML_RPC_LOG_LIST_REFERENCE = new TypeReference<ArrayList<XmlRpcLog>>() {};
        XML_RPC_ONLINE_REFERENCE = new TypeReference<XmlRpcOnline>() {};
        XML_RPC_AUTHTOKEN_DATA = new TypeReference<XmlRpcAuthTokenData>() {};
        LONG_REFERENCE = new TypeReference<Long>() {};
        DOUBLE_REFERENCE = new TypeReference<Double>() {};
    }
}
