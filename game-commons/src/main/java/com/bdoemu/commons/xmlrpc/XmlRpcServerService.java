package com.bdoemu.commons.xmlrpc;

import com.bdoemu.commons.xmlrpc.common.common.XmlRpcHttpRequestConfig;
import com.bdoemu.commons.xmlrpc.server.AbstractReflectiveHandlerMapping;
import com.bdoemu.commons.xmlrpc.server.PropertyHandlerMapping;
import com.bdoemu.commons.xmlrpc.server.XmlRpcServer;
import com.bdoemu.commons.xmlrpc.server.XmlRpcServerConfigImpl;
import com.bdoemu.commons.xmlrpc.webserver.WebServer;
import com.bdoemu.core.configs.XmlRpcConfig;
import com.bdoemu.core.startup.StartupComponent;
import org.atteo.classindex.ClassIndex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName XmlRpcServerService
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 17:53
 * VERSION 1.0
 */

@StartupComponent("Service")
public class XmlRpcServerService {
    private static final Logger log;
    private static final AtomicReference<Object> instance;

    private XmlRpcServerService() {
        if (XmlRpcConfig.XML_RPC_ENABLE) {
            try {
                final WebServer webServer = new WebServer(XmlRpcConfig.XML_RPC_PORT, InetAddress.getByName(XmlRpcConfig.XML_RPC_HOST));
                final XmlRpcServer xmlServer = webServer.getXmlRpcServer();
                final PropertyHandlerMapping phm = new PropertyHandlerMapping();
                for (final Class<?> handlerClass : ClassIndex.getAnnotated(XmlRpcHandler.class)) {
                    phm.addHandler(handlerClass.getSimpleName().replace("XmlRpcHandler", "Service"), handlerClass);
                }
                if (phm.getListMethods().length > 0) {
                    final AbstractReflectiveHandlerMapping.AuthenticationHandler authHandler = pRequest -> {
                        final XmlRpcHttpRequestConfig config;
                        config = (XmlRpcHttpRequestConfig)pRequest.getConfig();
                        return this.isAuthenticated(config.getBasicUserName(), config.getBasicPassword());
                    };
                    phm.setAuthenticationHandler(authHandler);
                    xmlServer.setHandlerMapping(phm);
                    final XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl)xmlServer.getConfig();
                    serverConfig.setEnabledForExtensions(true);
                    serverConfig.setContentLengthOptional(false);
                    webServer.start();
                    XmlRpcServerService.log.info("XmlRpcServer started. Listening on {}:{}", XmlRpcConfig.XML_RPC_HOST, XmlRpcConfig.XML_RPC_PORT);
                }
                else {
                    XmlRpcServerService.log.warn("XmlRpcServerService didn't started duo no services!");
                }
            }
            catch (Exception e) {
                XmlRpcServerService.log.error("Error while starting of XmlRpcServer. on {}:{}",XmlRpcConfig.XML_RPC_HOST, XmlRpcConfig.XML_RPC_PORT,e);
            }
        }
        else {
            XmlRpcServerService.log.info("XmlRpcServer disabled by configuration file.");
        }
    }

    private boolean isAuthenticated(final String pUserName, final String pPassword) {
        final boolean result = XmlRpcConfig.XML_RPC_LOGIN.equals(pUserName) && XmlRpcConfig.XML_RPC_PASSWORD.equals(pPassword);
        if (!result) {
            XmlRpcServerService.log.error("Trying connect to XML Rpc server with invalid credentials: {}:{}", (Object)pUserName, (Object)pPassword);
        }
        return result;
    }

    public static XmlRpcServerService getInstance() {
        Object value = XmlRpcServerService.instance.get();
        if (value == null) {
            synchronized (XmlRpcServerService.instance) {
                value = XmlRpcServerService.instance.get();
                if (value == null) {
                    final XmlRpcServerService actualValue = new XmlRpcServerService();
                    value = ((actualValue == null) ? XmlRpcServerService.instance : actualValue);
                    XmlRpcServerService.instance.set(value);
                }
            }
        }
        return (XmlRpcServerService)((value == XmlRpcServerService.instance) ? null : value);
    }

    static {
        log = LoggerFactory.getLogger((Class)XmlRpcServerService.class);
        instance = new AtomicReference<Object>();
    }
}
