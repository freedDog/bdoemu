package com.bdoemu.commons.xmlrpc.common.util;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

/**
 * @ClassName SAXParsers
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 14:14
 * VERSION 1.0
 */

public class SAXParsers {
    private static SAXParserFactory spf;

    public static XMLReader newXMLReader() throws XmlRpcException {
        try {
            return SAXParsers.spf.newSAXParser().getXMLReader();
        }
        catch (ParserConfigurationException e) {
            throw new XmlRpcException("Unable to create XML parser: " + e.getMessage(), e);
        }
        catch (SAXException e2) {
            throw new XmlRpcException("Unable to create XML parser: " + e2.getMessage(), e2);
        }
    }

    public static SAXParserFactory getSAXParserFactory() {
        return SAXParsers.spf;
    }

    public static void setSAXParserFactory(final SAXParserFactory pFactory) {
        SAXParsers.spf = pFactory;
    }

    static {
        (SAXParsers.spf = SAXParserFactory.newInstance()).setNamespaceAware(true);
        SAXParsers.spf.setValidating(false);
        try {
            SAXParsers.spf.setFeature("http://xml.org/sax/features/external-general-entities", false);
        }
        catch (ParserConfigurationException ex) {}
        catch (SAXException ex2) {}
        try {
            SAXParsers.spf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        }
        catch (ParserConfigurationException ex3) {}
        catch (SAXException ex4) {}
    }
}
