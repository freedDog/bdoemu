package com.bdoemu.commons.xmlrpc.common.serializer;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @ClassName TypeSerializer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 14:28
 * VERSION 1.0
 */

public interface TypeSerializer {

    void write(ContentHandler paramContentHandler, Object paramObject) throws SAXException;
}
