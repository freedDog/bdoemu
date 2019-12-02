package com.bdoemu.commons.xmlrpc.common.common;

/**
 * @ClassName XmlRpcHttpConfig
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:45
 * VERSION 1.0
 */

public interface XmlRpcHttpConfig extends XmlRpcStreamConfig{

    String getBasicEncoding();

    boolean isContentLengthOptional();
}
