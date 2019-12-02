package com.bdoemu.commons.xmlrpc.common.common;

import com.bdoemu.commons.xmlrpc.common.parser.BigDecimalParser;
import com.bdoemu.commons.xmlrpc.common.parser.BigIntegerParser;
import com.bdoemu.commons.xmlrpc.common.parser.BooleanParser;
import com.bdoemu.commons.xmlrpc.common.parser.ByteArrayParser;
import com.bdoemu.commons.xmlrpc.common.parser.CalendarParser;
import com.bdoemu.commons.xmlrpc.common.parser.DateParser;
import com.bdoemu.commons.xmlrpc.common.parser.DoubleParser;
import com.bdoemu.commons.xmlrpc.common.parser.FloatParser;
import com.bdoemu.commons.xmlrpc.common.parser.I1Parser;
import com.bdoemu.commons.xmlrpc.common.parser.I2Parser;
import com.bdoemu.commons.xmlrpc.common.parser.I4Parser;
import com.bdoemu.commons.xmlrpc.common.parser.I8Parser;
import com.bdoemu.commons.xmlrpc.common.parser.MapParser;
import com.bdoemu.commons.xmlrpc.common.parser.NodeParser;
import com.bdoemu.commons.xmlrpc.common.parser.NullParser;
import com.bdoemu.commons.xmlrpc.common.parser.ObjectArrayParser;
import com.bdoemu.commons.xmlrpc.common.parser.SerializableParser;
import com.bdoemu.commons.xmlrpc.common.parser.StringParser;
import com.bdoemu.commons.xmlrpc.common.parser.TypeParser;
import com.bdoemu.commons.xmlrpc.common.serializer.BigDecimalSerializer;
import com.bdoemu.commons.xmlrpc.common.serializer.BigIntegerSerializer;
import com.bdoemu.commons.xmlrpc.common.serializer.BooleanSerializer;
import com.bdoemu.commons.xmlrpc.common.serializer.ByteArraySerializer;
import com.bdoemu.commons.xmlrpc.common.serializer.CalendarSerializer;
import com.bdoemu.commons.xmlrpc.common.serializer.DateSerializer;
import com.bdoemu.commons.xmlrpc.common.serializer.DoubleSerializer;
import com.bdoemu.commons.xmlrpc.common.serializer.FloatSerializer;
import com.bdoemu.commons.xmlrpc.common.serializer.I1Serializer;
import com.bdoemu.commons.xmlrpc.common.serializer.I2Serializer;
import com.bdoemu.commons.xmlrpc.common.serializer.I4Serializer;
import com.bdoemu.commons.xmlrpc.common.serializer.I8Serializer;
import com.bdoemu.commons.xmlrpc.common.serializer.ListSerializer;
import com.bdoemu.commons.xmlrpc.common.serializer.MapSerializer;
import com.bdoemu.commons.xmlrpc.common.serializer.NodeSerializer;
import com.bdoemu.commons.xmlrpc.common.serializer.NullSerializer;
import com.bdoemu.commons.xmlrpc.common.serializer.ObjectArraySerializer;
import com.bdoemu.commons.xmlrpc.common.serializer.SerializableSerializer;
import com.bdoemu.commons.xmlrpc.common.serializer.StringSerializer;
import com.bdoemu.commons.xmlrpc.common.serializer.TypeSerializer;
import com.bdoemu.commons.xmlrpc.common.util.XmlRpcDateTimeDateFormat;
import org.apache.ws.commons.util.NamespaceContextImpl;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * @ClassName TypeFactoryImpl
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 14:46
 * VERSION 1.0
 */

public class TypeFactoryImpl implements TypeFactory{

    private static final TypeSerializer NULL_SERIALIZER;
    private static final TypeSerializer STRING_SERIALIZER;
    private static final TypeSerializer I4_SERIALIZER;
    private static final TypeSerializer BOOLEAN_SERIALIZER;
    private static final TypeSerializer DOUBLE_SERIALIZER;
    private static final TypeSerializer BYTE_SERIALIZER;
    private static final TypeSerializer SHORT_SERIALIZER;
    private static final TypeSerializer LONG_SERIALIZER;
    private static final TypeSerializer FLOAT_SERIALIZER;
    private static final TypeSerializer NODE_SERIALIZER;
    private static final TypeSerializer SERIALIZABLE_SERIALIZER;
    private static final TypeSerializer BIGDECIMAL_SERIALIZER;
    private static final TypeSerializer BIGINTEGER_SERIALIZER;
    private static final TypeSerializer CALENDAR_SERIALIZER;
    private final XmlRpcController controller;
    private DateSerializer dateSerializer;

    public TypeFactoryImpl(final XmlRpcController pController) {
        this.controller = pController;
    }

    public XmlRpcController getController() {
        return this.controller;
    }

    @Override
    public TypeSerializer getSerializer(final XmlRpcStreamConfig pConfig, final Object pObject) throws SAXException {
        if (pObject == null) {
            if (pConfig.isEnabledForExtensions()) {
                return TypeFactoryImpl.NULL_SERIALIZER;
            }
            throw new SAXException(new XmlRpcExtensionException("Null values aren't supported, if isEnabledForExtensions() == false"));
        }
        else {
            if (pObject instanceof String) {
                return TypeFactoryImpl.STRING_SERIALIZER;
            }
            if (pObject instanceof Byte) {
                if (pConfig.isEnabledForExtensions()) {
                    return TypeFactoryImpl.BYTE_SERIALIZER;
                }
                throw new SAXException(new XmlRpcExtensionException("Byte values aren't supported, if isEnabledForExtensions() == false"));
            }
            else if (pObject instanceof Short) {
                if (pConfig.isEnabledForExtensions()) {
                    return TypeFactoryImpl.SHORT_SERIALIZER;
                }
                throw new SAXException(new XmlRpcExtensionException("Short values aren't supported, if isEnabledForExtensions() == false"));
            }
            else {
                if (pObject instanceof Integer) {
                    return TypeFactoryImpl.I4_SERIALIZER;
                }
                if (pObject instanceof Long) {
                    if (pConfig.isEnabledForExtensions()) {
                        return TypeFactoryImpl.LONG_SERIALIZER;
                    }
                    throw new SAXException(new XmlRpcExtensionException("Long values aren't supported, if isEnabledForExtensions() == false"));
                }
                else {
                    if (pObject instanceof Boolean) {
                        return TypeFactoryImpl.BOOLEAN_SERIALIZER;
                    }
                    if (pObject instanceof Float) {
                        if (pConfig.isEnabledForExtensions()) {
                            return TypeFactoryImpl.FLOAT_SERIALIZER;
                        }
                        throw new SAXException(new XmlRpcExtensionException("Float values aren't supported, if isEnabledForExtensions() == false"));
                    }
                    else {
                        if (pObject instanceof Double) {
                            return TypeFactoryImpl.DOUBLE_SERIALIZER;
                        }
                        if (pObject instanceof Calendar) {
                            if (pConfig.isEnabledForExtensions()) {
                                return TypeFactoryImpl.CALENDAR_SERIALIZER;
                            }
                            throw new SAXException(new XmlRpcExtensionException("Calendar values aren't supported, if isEnabledForExtensions() == false"));
                        }
                        else {
                            if (pObject instanceof Date) {
                                if (this.dateSerializer == null) {
                                    this.dateSerializer = new DateSerializer(new XmlRpcDateTimeDateFormat() {
                                        private static final long serialVersionUID = 24345909123324234L;

                                        @Override
                                        protected TimeZone getTimeZone() {
                                            return TypeFactoryImpl.this.controller.getConfig().getTimeZone();
                                        }
                                    });
                                }
                                return this.dateSerializer;
                            }
                            if (pObject instanceof byte[]) {
                                return new ByteArraySerializer();
                            }
                            if (pObject instanceof Object[]) {
                                return new ObjectArraySerializer(this, pConfig);
                            }
                            if (pObject instanceof List) {
                                return new ListSerializer(this, pConfig);
                            }
                            if (pObject instanceof Map) {
                                return new MapSerializer(this, pConfig);
                            }
                            if (pObject instanceof Node) {
                                if (pConfig.isEnabledForExtensions()) {
                                    return TypeFactoryImpl.NODE_SERIALIZER;
                                }
                                throw new SAXException(new XmlRpcExtensionException("DOM nodes aren't supported, if isEnabledForExtensions() == false"));
                            }
                            else if (pObject instanceof BigInteger) {
                                if (pConfig.isEnabledForExtensions()) {
                                    return TypeFactoryImpl.BIGINTEGER_SERIALIZER;
                                }
                                throw new SAXException(new XmlRpcExtensionException("BigInteger values aren't supported, if isEnabledForExtensions() == false"));
                            }
                            else if (pObject instanceof BigDecimal) {
                                if (pConfig.isEnabledForExtensions()) {
                                    return TypeFactoryImpl.BIGDECIMAL_SERIALIZER;
                                }
                                throw new SAXException(new XmlRpcExtensionException("BigDecimal values aren't supported, if isEnabledForExtensions() == false"));
                            }
                            else {
                                if (!(pObject instanceof Serializable)) {
                                    return null;
                                }
                                if (pConfig.isEnabledForExtensions()) {
                                    return TypeFactoryImpl.SERIALIZABLE_SERIALIZER;
                                }
                                throw new SAXException(new XmlRpcExtensionException("Serializable objects aren't supported, if isEnabledForExtensions() == false"));
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public TypeParser getParser(final XmlRpcStreamConfig pConfig, final NamespaceContextImpl pContext, final String pURI, final String pLocalName) {
        if ("http://ws.apache.org/xmlrpc/namespaces/extensions".equals(pURI)) {
            if (!pConfig.isEnabledForExtensions()) {
                return null;
            }
            if ("nil".equals(pLocalName)) {
                return new NullParser();
            }
            if ("i1".equals(pLocalName)) {
                return new I1Parser();
            }
            if ("i2".equals(pLocalName)) {
                return new I2Parser();
            }
            if ("i8".equals(pLocalName)) {
                return new I8Parser();
            }
            if ("float".equals(pLocalName)) {
                return new FloatParser();
            }
            if ("dom".equals(pLocalName)) {
                return new NodeParser();
            }
            if ("bigdecimal".equals(pLocalName)) {
                return new BigDecimalParser();
            }
            if ("biginteger".equals(pLocalName)) {
                return new BigIntegerParser();
            }
            if ("serializable".equals(pLocalName)) {
                return new SerializableParser();
            }
            if ("dateTime".equals(pLocalName)) {
                return new CalendarParser();
            }
        }
        else if ("".equals(pURI)) {
            if ("int".equals(pLocalName) || "i4".equals(pLocalName)) {
                return new I4Parser();
            }
            if ("boolean".equals(pLocalName)) {
                return new BooleanParser();
            }
            if ("double".equals(pLocalName)) {
                return new DoubleParser();
            }
            if ("dateTime.iso8601".equals(pLocalName)) {
                return new DateParser(new XmlRpcDateTimeDateFormat() {
                    private static final long serialVersionUID = 7585237706442299067L;

                    @Override
                    protected TimeZone getTimeZone() {
                        return TypeFactoryImpl.this.controller.getConfig().getTimeZone();
                    }
                });
            }
            if ("array".equals(pLocalName)) {
                return new ObjectArrayParser(pConfig, pContext, this);
            }
            if ("struct".equals(pLocalName)) {
                return new MapParser(pConfig, pContext, this);
            }
            if ("base64".equals(pLocalName)) {
                return new ByteArrayParser();
            }
            if ("string".equals(pLocalName)) {
                return new StringParser();
            }
        }
        return null;
    }

    static {
        NULL_SERIALIZER = new NullSerializer();
        STRING_SERIALIZER = new StringSerializer();
        I4_SERIALIZER = new I4Serializer();
        BOOLEAN_SERIALIZER = new BooleanSerializer();
        DOUBLE_SERIALIZER = new DoubleSerializer();
        BYTE_SERIALIZER = new I1Serializer();
        SHORT_SERIALIZER = new I2Serializer();
        LONG_SERIALIZER = new I8Serializer();
        FLOAT_SERIALIZER = new FloatSerializer();
        NODE_SERIALIZER = new NodeSerializer();
        SERIALIZABLE_SERIALIZER = new SerializableSerializer();
        BIGDECIMAL_SERIALIZER = new BigDecimalSerializer();
        BIGINTEGER_SERIALIZER = new BigIntegerSerializer();
        CALENDAR_SERIALIZER = new CalendarSerializer();
    }
}
