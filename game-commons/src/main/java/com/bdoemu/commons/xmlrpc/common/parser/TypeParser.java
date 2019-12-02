package com.bdoemu.commons.xmlrpc.common.parser;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;
import org.xml.sax.ContentHandler;

/**
 * @ClassName TypeParser
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 14:19
 * VERSION 1.0
 */

public interface TypeParser extends ContentHandler {

    Object getResult() throws XmlRpcException;
}
