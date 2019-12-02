package com.bdoemu.commons.xmlrpc.common.serializer;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;
import com.bdoemu.commons.xmlrpc.common.common.XmlRpcStreamConfig;
import org.xml.sax.ContentHandler;

import java.io.OutputStream;

/**
 * @ClassName XmlWriterFactory
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 12:07
 * VERSION 1.0
 */

public interface XmlWriterFactory {

    ContentHandler getXmlWriter(XmlRpcStreamConfig paramXmlRpcStreamConfig, OutputStream paramOutputStream) throws XmlRpcException;
}
