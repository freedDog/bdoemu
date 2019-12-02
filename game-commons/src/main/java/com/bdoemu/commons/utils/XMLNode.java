package com.bdoemu.commons.utils;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @ClassName XMLNode
 * @Description XML 节点
 * @Author JiangBangMing
 * @Date 2019/6/21 20:49
 * VERSION 1.0
 */

public class XMLNode {

    protected String readS(NamedNodeMap node, String name) {
        Node result = node.getNamedItem(name);
        if (result != null) {
            return node.getNamedItem(name).getNodeValue();
        }
        return null;
    }


    protected int readD(NamedNodeMap node, String name) { return readD(node, name, 0); }


    protected int readD(NamedNodeMap node, String name, int defaultValue) {
        Node result = node.getNamedItem(name);
        if (result != null) {
            return Integer.parseInt(result.getNodeValue());
        }
        return defaultValue;
    }

    protected byte readC(NamedNodeMap node, String name) {
        Node result = node.getNamedItem(name);
        if (result != null) {
            return Byte.parseByte(result.getNodeValue());
        }
        return 0;
    }

    protected long readQ(NamedNodeMap node, String name) {
        Node result = node.getNamedItem(name);
        if (result != null) {
            return Long.parseUnsignedLong(result.getNodeValue());
        }
        return 0L;
    }

    protected float readF(NamedNodeMap node, String name) {
        Node result = node.getNamedItem(name);
        if (result != null) {
            return Float.parseFloat(result.getNodeValue());
        }
        return 0.0F;
    }

    protected boolean readBoolean(NamedNodeMap node, String name) {
        Node result = node.getNamedItem(name);
        if (result != null) {
            return Boolean.parseBoolean(result.getNodeValue());
        }
        return false;
    }

    private String getValue(Element e, String name) {
        if (e.hasAttribute(name)) {
            return e.getAttributes().getNamedItem(name).getNodeValue();
        }
        return e.getElementsByTagName(name).item(0).getTextContent();
    }


    protected String readS(Element e, String name) { return getValue(e, name); }



    protected int readD(Element e, String name) { return Integer.parseInt(getValue(e, name)); }



    protected byte readC(Element e, String name) { return Byte.parseByte(getValue(e, name)); }



    protected long readQ(Element e, String name) { return Long.parseLong(getValue(e, name)); }



    protected float readF(Element e, String name) { return Float.parseFloat(getValue(e, name)); }


    protected String[] readS(Element e, String parent, String child) {
        Element e2 = (Element)e.getElementsByTagName(parent).item(0);
        NodeList nl = e2.getElementsByTagName(child);
        String[] r = new String[nl.getLength()];
        for (int i = 0; i < nl.getLength(); i++) {
            r[i] = nl.item(i).getTextContent().trim();
        }
        return r;
    }

    protected int[] readD(Element e, String parent, String child) {
        Element e2 = (Element)e.getElementsByTagName(parent).item(0);
        NodeList nl = e2.getElementsByTagName(child);
        int[] r = new int[nl.getLength()];
        for (int i = 0; i < nl.getLength(); i++) {
            r[i] = Integer.parseInt(nl.item(i).getTextContent().trim());
        }
        return r;
    }
}
