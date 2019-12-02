package com.bdoemu.commons.xmlrpc;

import com.bdoemu.commons.xmlrpc.client.XmlRpcClient;
import com.bdoemu.commons.xmlrpc.client.XmlRpcClientConfigImpl;
import com.bdoemu.core.configs.XmlRpcConfig;
import com.bdoemu.core.startup.StartupComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName XmlRpcClientService
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 11:58
 * VERSION 1.0
 */


@StartupComponent("Service")
public class XmlRpcClientService {
    private static final Logger log;
    private static final AtomicReference<Object> instance;
    private Map<String, XmlRpcClient> clients;

    public XmlRpcClientService() {
        this.clients = new HashMap<String, XmlRpcClient>();
        try {
            for (final String xmlRpcClientConfig : XmlRpcConfig.XML_RPC_CLIENTS) {
                final XmlRpcClientEntry clientEntry = new XmlRpcClientEntry(xmlRpcClientConfig);
                final URL url = new URL("http", clientEntry.getHost(), clientEntry.getPort(), "");
                final XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
                config.setEnabledForExtensions(true);
                config.setServerURL(url);
                config.setBasicUserName(clientEntry.getLogin());
                config.setBasicPassword(clientEntry.getPassword());
                this.clients.put(clientEntry.getName(), new XmlRpcClient(config));
            }
        }
        catch (Exception e) {
            XmlRpcClientService.log.error("Error in XML-RPC Client URL format");
        }
    }

    public XmlRpcClient getClient(final String name) {
        return this.clients.get(name);
    }

    public static XmlRpcClientService getInstance() {
        Object value = XmlRpcClientService.instance.get();
        if (value == null) {
            synchronized (XmlRpcClientService.instance) {
                value = XmlRpcClientService.instance.get();
                if (value == null) {
                    final XmlRpcClientService actualValue = new XmlRpcClientService();
                    value = ((actualValue == null) ? XmlRpcClientService.instance : actualValue);
                    XmlRpcClientService.instance.set(value);
                }
            }
        }
        return (XmlRpcClientService)((value == XmlRpcClientService.instance) ? null : value);
    }

    static {
        log = LoggerFactory.getLogger((Class)XmlRpcClientService.class);
        instance = new AtomicReference<Object>();
    }
}
