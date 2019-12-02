package com.bdoemu.commons.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName XmlParser
 * @Description Xml解析器
 * @Author JiangBangMing
 * @Date 2019/6/24 12:09
 * VERSION 1.0
 */

public class XmlParser {
    private String name;
    private String content;
    private Map<String, String> nameAttributes = new HashMap();
    private Map<String, ArrayList<XmlParser>> nameChildren = new HashMap();


    public XmlParser(InputStream inputStream, String rootName) {
        this(rootElement(inputStream, rootName));
    }

    public XmlParser(String filename, String rootName) {
        this(fileInputStream(filename), rootName);
    }

    public XmlParser(String rootName) {
        this.name = rootName;
    }

    private XmlParser(Element element) {
        this.name = element.getNodeName();
        this.content = element.getTextContent();
        NamedNodeMap namedNodeMap = element.getAttributes();
        int n = namedNodeMap.getLength();
        for (int i = 0; i < n; i++) {
            Node node = namedNodeMap.item(i);
            String name = node.getNodeName();
            addAttribute(name, node.getNodeValue());
        }
        NodeList nodes = element.getChildNodes();
        n = nodes.getLength();
        for (int i = 0; i < n; i++) {
            Node node = nodes.item(i);
            int type = node.getNodeType();
            if (type == 1) {
                XmlParser child = new XmlParser((Element)node);
                addChild(node.getNodeName(), child);
            }
        }
    }

    public void addAttribute(String name, String value) {
        this.nameAttributes.put(name, value);
    }


    private void addChild(String name, XmlParser child) {
        ArrayList<XmlParser> children = (ArrayList)this.nameChildren.get(name);
        if (children == null) {
            children = new ArrayList<XmlParser>();
            this.nameChildren.put(name, children);
        }
        children.add(child);
    }

    public String name() {
        return this.name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String content() {
        return this.content;
    }

    public void addChild(XmlParser xml) {
        addChild(xml.name(), xml);
    }
    public void addChildren(XmlParser... xmls) { // Byte code:
        for (final XmlParser xml : xmls) {
            this.addChild(xml.name(), xml);
        }
    }

        public XmlParser child(String name) {
            XmlParser child = optChild(name);
            if (child == null) {
                throw new RuntimeException("Could not find child node: " + name);
            }
            return child;
        }

        public XmlParser optChild(String name) {
            List<XmlParser> children = children(name);
            int n = children.size();
            if (n > 1) {
                throw new RuntimeException("Could not find individual child node: " + name);
            }
            return (n == 0) ? null : (XmlParser)children.get(0);
        }

        public boolean option(String name) {
        return (optChild(name) != null);
     }



    public List<XmlParser> children() { return (this.nameChildren == null) ? new ArrayList() :
            (List)this.nameChildren.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

        public List<XmlParser> children(String name) {
            List<XmlParser> children = (List)this.nameChildren.get(name);
            return (children == null) ? new ArrayList() : children;
        }

        public String string(String name) {
            String value = optString(name);
            if (value == null) {
                throw new RuntimeException("Could not find attribute: " + name + ", in node: " + this.name);
            }
            return value;
        }


        public String optString(String name) {
        return (String)this.nameAttributes.get(name);
    }

        public int integer(String name) {
        return Integer.parseInt(string(name));
    }

        public int integerHex(String name) {
        return Integer.decode(string(name)).intValue();
    }

        public Integer optInteger(String name) {
            String string = optString(name);
            return (string == null) ? null : Integer.valueOf(integer(name));
        }


        public double doubleValue(String name) {
        return Double.parseDouble(optString(name));
    }

        public Double optDouble(String name) {
            String string = optString(name);
            return (string == null) ? null : Double.valueOf(doubleValue(name));
        }

        private static Element rootElement(InputStream inputStream, String rootName) {
            try {
                DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = builderFactory.newDocumentBuilder();
                Document document = builder.parse(inputStream);
                Element rootElement = document.getDocumentElement();
                if (!rootElement.getNodeName().equals(rootName)) {
                    throw new RuntimeException("Could not find root node: " + rootName);
                }
                return rootElement;
            } catch (IOException |javax.xml.parsers.ParserConfigurationException|org.xml.sax.SAXException exception) {
                throw new RuntimeException(exception);
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception exception) {
                        throw new RuntimeException(exception);
                    }
                }
            }
        }

        private static FileInputStream fileInputStream(String filename) {
            try {
                return new FileInputStream(filename);
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }
}
