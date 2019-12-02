package com.bdoemu.commons.xmlrpc.common.common;

import org.w3c.dom.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

/**
 * @ClassName TypeConverterFactoryImpl
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 18:14
 * VERSION 1.0
 */

public class TypeConverterFactoryImpl implements TypeConverterFactory{
    private static final TypeConverter voidTypeConverter;
    private static final TypeConverter mapTypeConverter;
    private static final TypeConverter objectArrayTypeConverter;
    private static final TypeConverter byteArrayTypeConverter;
    private static final TypeConverter stringTypeConverter;
    private static final TypeConverter booleanTypeConverter;
    private static final TypeConverter characterTypeConverter;
    private static final TypeConverter byteTypeConverter;
    private static final TypeConverter shortTypeConverter;
    private static final TypeConverter integerTypeConverter;
    private static final TypeConverter longTypeConverter;
    private static final TypeConverter bigDecimalTypeConverter;
    private static final TypeConverter bigIntegerTypeConverter;
    private static final TypeConverter floatTypeConverter;
    private static final TypeConverter doubleTypeConverter;
    private static final TypeConverter dateTypeConverter;
    private static final TypeConverter calendarTypeConverter;
    private static final TypeConverter domTypeConverter;
    private static final TypeConverter primitiveBooleanTypeConverter;
    private static final TypeConverter primitiveCharTypeConverter;
    private static final TypeConverter primitiveByteTypeConverter;
    private static final TypeConverter primitiveShortTypeConverter;
    private static final TypeConverter primitiveIntTypeConverter;
    private static final TypeConverter primitiveLongTypeConverter;
    private static final TypeConverter primitiveFloatTypeConverter;
    private static final TypeConverter primitiveDoubleTypeConverter;
    private static final TypeConverter propertiesTypeConverter;
    private static final TypeConverter hashTableTypeConverter;
    private static final TypeConverter listTypeConverter;
    private static final TypeConverter vectorTypeConverter;

    @Override
    public TypeConverter getTypeConverter(final Class pClass) {
        if (Void.TYPE.equals(pClass)) {
            return TypeConverterFactoryImpl.voidTypeConverter;
        }
        if (pClass.isAssignableFrom(Boolean.TYPE)) {
            return TypeConverterFactoryImpl.primitiveBooleanTypeConverter;
        }
        if (pClass.isAssignableFrom(Character.TYPE)) {
            return TypeConverterFactoryImpl.primitiveCharTypeConverter;
        }
        if (pClass.isAssignableFrom(Byte.TYPE)) {
            return TypeConverterFactoryImpl.primitiveByteTypeConverter;
        }
        if (pClass.isAssignableFrom(Short.TYPE)) {
            return TypeConverterFactoryImpl.primitiveShortTypeConverter;
        }
        if (pClass.isAssignableFrom(Integer.TYPE)) {
            return TypeConverterFactoryImpl.primitiveIntTypeConverter;
        }
        if (pClass.isAssignableFrom(Long.TYPE)) {
            return TypeConverterFactoryImpl.primitiveLongTypeConverter;
        }
        if (pClass.isAssignableFrom(Float.TYPE)) {
            return TypeConverterFactoryImpl.primitiveFloatTypeConverter;
        }
        if (pClass.isAssignableFrom(Double.TYPE)) {
            return TypeConverterFactoryImpl.primitiveDoubleTypeConverter;
        }
        if (pClass.isAssignableFrom(String.class)) {
            return TypeConverterFactoryImpl.stringTypeConverter;
        }
        if (pClass.isAssignableFrom(Boolean.class)) {
            return TypeConverterFactoryImpl.booleanTypeConverter;
        }
        if (pClass.isAssignableFrom(Character.class)) {
            return TypeConverterFactoryImpl.characterTypeConverter;
        }
        if (pClass.isAssignableFrom(Byte.class)) {
            return TypeConverterFactoryImpl.byteTypeConverter;
        }
        if (pClass.isAssignableFrom(Short.class)) {
            return TypeConverterFactoryImpl.shortTypeConverter;
        }
        if (pClass.isAssignableFrom(Integer.class)) {
            return TypeConverterFactoryImpl.integerTypeConverter;
        }
        if (pClass.isAssignableFrom(Long.class)) {
            return TypeConverterFactoryImpl.longTypeConverter;
        }
        if (pClass.isAssignableFrom(BigDecimal.class)) {
            return TypeConverterFactoryImpl.bigDecimalTypeConverter;
        }
        if (pClass.isAssignableFrom(BigInteger.class)) {
            return TypeConverterFactoryImpl.bigIntegerTypeConverter;
        }
        if (pClass.isAssignableFrom(Float.class)) {
            return TypeConverterFactoryImpl.floatTypeConverter;
        }
        if (pClass.isAssignableFrom(Double.class)) {
            return TypeConverterFactoryImpl.doubleTypeConverter;
        }
        if (pClass.isAssignableFrom(Date.class)) {
            return TypeConverterFactoryImpl.dateTypeConverter;
        }
        if (pClass.isAssignableFrom(Calendar.class)) {
            return TypeConverterFactoryImpl.calendarTypeConverter;
        }
        if (pClass.isAssignableFrom(Object[].class)) {
            return TypeConverterFactoryImpl.objectArrayTypeConverter;
        }
        if (pClass.isAssignableFrom(List.class)) {
            return TypeConverterFactoryImpl.listTypeConverter;
        }
        if (pClass.isAssignableFrom(Vector.class)) {
            return TypeConverterFactoryImpl.vectorTypeConverter;
        }
        if (pClass.isAssignableFrom(Map.class)) {
            return TypeConverterFactoryImpl.mapTypeConverter;
        }
        if (pClass.isAssignableFrom(Hashtable.class)) {
            return TypeConverterFactoryImpl.hashTableTypeConverter;
        }
        if (pClass.isAssignableFrom(Properties.class)) {
            return TypeConverterFactoryImpl.propertiesTypeConverter;
        }
        if (pClass.isAssignableFrom(byte[].class)) {
            return TypeConverterFactoryImpl.byteArrayTypeConverter;
        }
        if (pClass.isAssignableFrom(Document.class)) {
            return TypeConverterFactoryImpl.domTypeConverter;
        }
        if (Serializable.class.isAssignableFrom(pClass)) {
            return new CastCheckingTypeConverter(pClass);
        }
        throw new IllegalStateException("Invalid parameter or result type: " + pClass.getName());
    }

    static {
        voidTypeConverter = new IdentityTypeConverter(Void.TYPE);
        mapTypeConverter = new IdentityTypeConverter(Map.class);
        objectArrayTypeConverter = new IdentityTypeConverter(Object[].class);
        byteArrayTypeConverter = new IdentityTypeConverter(byte[].class);
        stringTypeConverter = new IdentityTypeConverter(String.class);
        booleanTypeConverter = new IdentityTypeConverter(Boolean.class);
        characterTypeConverter = new IdentityTypeConverter(Character.class);
        byteTypeConverter = new IdentityTypeConverter(Byte.class);
        shortTypeConverter = new IdentityTypeConverter(Short.class);
        integerTypeConverter = new IdentityTypeConverter(Integer.class);
        longTypeConverter = new IdentityTypeConverter(Long.class);
        bigDecimalTypeConverter = new IdentityTypeConverter(BigDecimal.class);
        bigIntegerTypeConverter = new IdentityTypeConverter(BigInteger.class);
        floatTypeConverter = new IdentityTypeConverter(Float.class);
        doubleTypeConverter = new IdentityTypeConverter(Double.class);
        dateTypeConverter = new IdentityTypeConverter(Date.class);
        calendarTypeConverter = new IdentityTypeConverter(Calendar.class);
        domTypeConverter = new IdentityTypeConverter(Document.class);
        primitiveBooleanTypeConverter = new PrimitiveTypeConverter(Boolean.class);
        primitiveCharTypeConverter = new PrimitiveTypeConverter(Character.class);
        primitiveByteTypeConverter = new PrimitiveTypeConverter(Byte.class);
        primitiveShortTypeConverter = new PrimitiveTypeConverter(Short.class);
        primitiveIntTypeConverter = new PrimitiveTypeConverter(Integer.class);
        primitiveLongTypeConverter = new PrimitiveTypeConverter(Long.class);
        primitiveFloatTypeConverter = new PrimitiveTypeConverter(Float.class);
        primitiveDoubleTypeConverter = new PrimitiveTypeConverter(Double.class);
        propertiesTypeConverter = new TypeConverter() {
            @Override
            public boolean isConvertable(final Object pObject) {
                return pObject == null || pObject instanceof Map;
            }

            @Override
            public Object convert(final Object pObject) {
                if (pObject == null) {
                    return null;
                }
                final Properties props = new Properties();
                props.putAll((Map<?, ?>)pObject);
                return props;
            }

            @Override
            public Object backConvert(final Object pObject) {
                return pObject;
            }
        };
        hashTableTypeConverter = new TypeConverter() {
            @Override
            public boolean isConvertable(final Object pObject) {
                return pObject == null || pObject instanceof Map;
            }

            @Override
            public Object convert(final Object pObject) {
                if (pObject == null) {
                    return null;
                }
                return new Hashtable((Map<?, ?>)pObject);
            }

            @Override
            public Object backConvert(final Object pObject) {
                return pObject;
            }
        };
        listTypeConverter = new ListTypeConverter(List.class) {
            @Override
            protected List newList(final int pSize) {
                return new ArrayList(pSize);
            }
        };
        vectorTypeConverter = new ListTypeConverter(Vector.class) {
            @Override
            protected List newList(final int pSize) {
                return new Vector(pSize);
            }
        };
    }

    private static class IdentityTypeConverter implements TypeConverter
    {
        private final Class clazz;

        IdentityTypeConverter(final Class pClass) {
            this.clazz = pClass;
        }

        @Override
        public boolean isConvertable(final Object pObject) {
            return pObject == null || this.clazz.isAssignableFrom(pObject.getClass());
        }

        @Override
        public Object convert(final Object pObject) {
            return pObject;
        }

        @Override
        public Object backConvert(final Object pObject) {
            return pObject;
        }
    }

    private abstract static class ListTypeConverter implements TypeConverter
    {
        private final Class clazz;

        ListTypeConverter(final Class pClass) {
            this.clazz = pClass;
        }

        protected abstract List newList(final int p0);

        @Override
        public boolean isConvertable(final Object pObject) {
            return pObject == null || pObject instanceof Object[] || pObject instanceof Collection;
        }

        @Override
        public Object convert(final Object pObject) {
            if (pObject == null) {
                return null;
            }
            if (this.clazz.isAssignableFrom(pObject.getClass())) {
                return pObject;
            }
            if (pObject instanceof Object[]) {
                final Object[] objects = (Object[])pObject;
                final List result = this.newList(objects.length);
                for (int i = 0; i < objects.length; ++i) {
                    result.add(objects[i]);
                }
                return result;
            }
            final Collection collection = (Collection)pObject;
            final List result = this.newList(collection.size());
            result.addAll(collection);
            return result;
        }

        @Override
        public Object backConvert(final Object pObject) {
            return ((List)pObject).toArray();
        }
    }

    private static class PrimitiveTypeConverter implements TypeConverter
    {
        private final Class clazz;

        PrimitiveTypeConverter(final Class pClass) {
            this.clazz = pClass;
        }

        @Override
        public boolean isConvertable(final Object pObject) {
            return pObject != null && pObject.getClass().isAssignableFrom(this.clazz);
        }

        @Override
        public Object convert(final Object pObject) {
            return pObject;
        }

        @Override
        public Object backConvert(final Object pObject) {
            return pObject;
        }
    }

    private static class CastCheckingTypeConverter implements TypeConverter
    {
        private final Class clazz;

        CastCheckingTypeConverter(final Class pClass) {
            this.clazz = pClass;
        }

        @Override
        public boolean isConvertable(final Object pObject) {
            return pObject == null || this.clazz.isAssignableFrom(pObject.getClass());
        }

        @Override
        public Object convert(final Object pObject) {
            return pObject;
        }

        @Override
        public Object backConvert(final Object pObject) {
            return pObject;
        }
    }
}
