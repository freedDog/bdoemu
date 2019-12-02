package com.bdoemu.commons.xmlrpc.metadata;

import com.bdoemu.commons.xmlrpc.common.XmlRpcException;
import org.w3c.dom.Node;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Util
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 20:39
 * VERSION 1.0
 */

public class Util {

    private static final Class jaxbElementClass;

    public static String getSignatureType(final Class pType) {
        if (pType == Integer.TYPE || pType == Integer.class) {
            return "int";
        }
        if (pType == Double.TYPE || pType == Double.class) {
            return "double";
        }
        if (pType == Boolean.TYPE || pType == Boolean.class) {
            return "boolean";
        }
        if (pType == String.class) {
            return "string";
        }
        if (Object[].class.isAssignableFrom(pType) || List.class.isAssignableFrom(pType)) {
            return "array";
        }
        if (Map.class.isAssignableFrom(pType)) {
            return "struct";
        }
        if (Date.class.isAssignableFrom(pType) || Calendar.class.isAssignableFrom(pType)) {
            return "dateTime.iso8601";
        }
        if (pType == byte[].class) {
            return "base64";
        }
        if (pType == Void.TYPE) {
            return "ex:nil";
        }
        if (pType == Byte.TYPE || pType == Byte.class) {
            return "ex:i1";
        }
        if (pType == Short.TYPE || pType == Short.class) {
            return "ex:i2";
        }
        if (pType == Long.TYPE || pType == Long.class) {
            return "ex:i8";
        }
        if (pType == Float.TYPE || pType == Float.class) {
            return "ex:float";
        }
        if (Node.class.isAssignableFrom(pType)) {
            return "ex:node";
        }
        if (Util.jaxbElementClass != null && Util.jaxbElementClass.isAssignableFrom(pType)) {
            return "ex:jaxbElement";
        }
        if (Serializable.class.isAssignableFrom(pType)) {
            return "base64";
        }
        return null;
    }

    public static String[][] getSignature(final Method[] pMethods) {
        final List<String[]> result = new ArrayList();
        for (int i = 0; i < pMethods.length; ++i) {
            final String[] sig = getSignature(pMethods[i]);
            if (sig != null) {
                result.add(sig);
            }
        }
        return result.toArray(new String[result.size()][]);
    }

    public static String[] getSignature(final Method pMethod) {
        final Class[] paramClasses = pMethod.getParameterTypes();
        final String[] sig = new String[paramClasses.length + 1];
        String s = getSignatureType(pMethod.getReturnType());
        if (s == null) {
            return null;
        }
        sig[0] = s;
        for (int i = 0; i < paramClasses.length; ++i) {
            s = getSignatureType(paramClasses[i]);
            if (s == null) {
                return null;
            }
            sig[i + 1] = s;
        }
        return sig;
    }

    public static String getMethodHelp(final Class pClass, final Method[] pMethods) {
        final List<String> result = new ArrayList();
        for (int i = 0; i < pMethods.length; ++i) {
            final String help = getMethodHelp(pClass, pMethods[i]);
            if (help != null) {
                result.add(help);
            }
        }
        switch (result.size()) {
            case 0: {
                return null;
            }
            case 1: {
                return result.get(0);
            }
            default: {
                final StringBuffer sb = new StringBuffer();
                for (int j = 0; j < result.size(); ++j) {
                    sb.append(j + 1);
                    sb.append(": ");
                    sb.append(result.get(j));
                    sb.append("\n");
                }
                return sb.toString();
            }
        }
    }

    public static String getMethodHelp(final Class pClass, final Method pMethod) {
        final StringBuffer sb = new StringBuffer();
        sb.append("Invokes the method ");
        sb.append(pClass.getName());
        sb.append(".");
        sb.append(pMethod.getName());
        sb.append("(");
        final Class[] paramClasses = pMethod.getParameterTypes();
        for (int i = 0; i < paramClasses.length; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(paramClasses[i].getName());
        }
        sb.append(").");
        return sb.toString();
    }

    public static String getSignature(final Object[] args) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < args.length; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            if (args[i] == null) {
                sb.append("null");
            }
            else {
                sb.append(args[i].getClass().getName());
            }
        }
        return sb.toString();
    }

    public static Object newInstance(final Class pClass) throws XmlRpcException {
        try {
            return pClass.newInstance();
        }
        catch (InstantiationException e) {
            throw new XmlRpcException("Failed to instantiate class " + pClass.getName(), e);
        }
        catch (IllegalAccessException e2) {
            throw new XmlRpcException("Illegal access when instantiating class " + pClass.getName(), e2);
        }
    }

    static {
        Class c;
        try {
            c = Class.forName("javax.xml.bind.Element");
        }
        catch (ClassNotFoundException e) {
            c = null;
        }
        jaxbElementClass = c;
    }
}
