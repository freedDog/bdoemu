package com.bdoemu.commons.xmlrpc.common.serializer;

import org.apache.ws.commons.serialize.CharSetXMLWriter;
import org.apache.ws.commons.serialize.XMLWriter;

/**
 * @ClassName CharSetXmlWriterFactory
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 17:25
 * VERSION 1.0
 */

public class CharSetXmlWriterFactory extends BaseXmlWriterFactory{
    @Override
    protected XMLWriter newXmlWriter() {
        return new CharSetXMLWriter();
    }
}
