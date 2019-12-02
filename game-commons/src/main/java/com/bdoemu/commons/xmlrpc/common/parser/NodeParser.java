package com.bdoemu.commons.xmlrpc.common.parser;

import org.apache.ws.commons.serialize.DOMBuilder;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * @ClassName NodeParser
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 15:39
 * VERSION 1.0
 */

public class NodeParser extends ExtParser{
    private static final DocumentBuilderFactory dbf;
    private final DOMBuilder builder;

    public NodeParser() {
        this.builder = new DOMBuilder();
    }

    @Override
    protected String getTagName() {
        return "dom";
    }

    @Override
    protected ContentHandler getExtHandler() throws SAXException {
        try {
            this.builder.setTarget(dbf.newDocumentBuilder().newDocument());
        }
        catch (ParserConfigurationException e) {
            throw new SAXException(e);
        }
        return this.builder;
    }

    @Override
    public Object getResult() {
        return this.builder.getTarget();
    }

    static {
        dbf = DocumentBuilderFactory.newInstance();
    }
}
