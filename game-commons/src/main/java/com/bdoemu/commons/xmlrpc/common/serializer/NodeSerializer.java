package com.bdoemu.commons.xmlrpc.common.serializer;

import org.apache.ws.commons.serialize.DOMSerializer;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @ClassName NodeSerializer
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:18
 * VERSION 1.0
 */

public class NodeSerializer extends ExtSerializer{

    private static final DOMSerializer ser;
    public static final String DOM_TAG = "dom";

    @Override
    protected String getTagName() {
        return "dom";
    }

    @Override
    protected void serialize(final ContentHandler pHandler, final Object pObject) throws SAXException {
        NodeSerializer.ser.serialize((Node)pObject, pHandler);
    }

    static {
        (ser = new DOMSerializer()).setStartingDocument(false);
    }
}
