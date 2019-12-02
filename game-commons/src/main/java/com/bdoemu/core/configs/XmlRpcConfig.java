package com.bdoemu.core.configs;

import com.bdoemu.commons.config.annotation.ConfigComments;
import com.bdoemu.commons.config.annotation.ConfigFile;
import com.bdoemu.commons.config.annotation.ConfigProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName XmlRpcConfig
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 17:44
 * VERSION 1.0
 */

@ConfigFile(name = "configs/xmlrpc.properties")
public class XmlRpcConfig {

    @ConfigComments(comment = { "Enable/disable this XML RPC server." })
    @ConfigProperty(name = "xmlrpc_enable", value = "true")
    public static boolean XML_RPC_ENABLE;
    @ConfigComments(comment = { "Host of this XML RPC server." })
    @ConfigProperty(name = "xmlrpc_host", value = "0.0.0.0")
    public static String XML_RPC_HOST;
    @ConfigComments(comment = { "Port of this XML RPC server." })
    @ConfigProperty(name = "xmlrpc_port", value = "7001")
    public static int XML_RPC_PORT;
    @ConfigComments(comment = { "Login for this XML RPC server." })
    @ConfigProperty(name = "xmlrpc_login", value = "admin")
    public static String XML_RPC_LOGIN;
    @ConfigComments(comment = { "Password for this XML RPC server." })
    @ConfigProperty(name = "xmlrpc_password", value = "password")
    public static String XML_RPC_PASSWORD;
    @ConfigComments(comment = { "List of XML RPC clients", "Format: name:host:port:user:password,name2:host:port:user:password", "Default values:", "  login:127.0.0.1:7000:user:password" })
    @ConfigProperty(name = "xmlrpc_clients")
    public static List<String> XML_RPC_CLIENTS;

    static {
        XmlRpcConfig.XML_RPC_CLIENTS = new ArrayList<String>();
    }
}
