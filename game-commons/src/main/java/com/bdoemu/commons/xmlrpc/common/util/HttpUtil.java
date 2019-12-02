package com.bdoemu.commons.xmlrpc.common.util;

import com.bdoemu.commons.xmlrpc.common.common.XmlRpcHttpRequestConfigImpl;
import org.apache.ws.commons.util.Base64;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.StringTokenizer;

/**
 * @ClassName HttpUtil
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 16:47
 * VERSION 1.0
 */

public class HttpUtil {

    public static String encodeBasicAuthentication(final String pUser, final String pPassword, String pEncoding) throws UnsupportedEncodingException {
        if (pUser == null) {
            return null;
        }
        final String s = pUser + ':' + pPassword;
        if (pEncoding == null) {
            pEncoding = "UTF-8";
        }
        final byte[] bytes = s.getBytes(pEncoding);
        return Base64.encode(s.getBytes(pEncoding), 0, bytes.length, 0, (String)null);
    }

    public static boolean isUsingGzipEncoding(final String pHeaderValue) {
        if (pHeaderValue == null) {
            return false;
        }
        final StringTokenizer st = new StringTokenizer(pHeaderValue, ",");
        while (st.hasMoreTokens()) {
            String encoding = st.nextToken();
            final int offset = encoding.indexOf(59);
            if (offset >= 0) {
                encoding = encoding.substring(0, offset);
            }
            if ("gzip".equalsIgnoreCase(encoding.trim())) {
                return true;
            }
        }
        return false;
    }

    public static String getNonIdentityTransferEncoding(final String pHeaderValue) {
        if (pHeaderValue == null) {
            return null;
        }
        final StringTokenizer st = new StringTokenizer(pHeaderValue, ",");
        while (st.hasMoreTokens()) {
            String encoding = st.nextToken();
            final int offset = encoding.indexOf(59);
            if (offset >= 0) {
                encoding = encoding.substring(0, offset);
            }
            if (!"identity".equalsIgnoreCase(encoding.trim())) {
                return encoding.trim();
            }
        }
        return null;
    }

    public static boolean isUsingGzipEncoding(final Enumeration pValues) {
        if (pValues != null) {
            while (pValues.hasMoreElements()) {
                if (isUsingGzipEncoding((String) pValues.nextElement())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String readLine(final InputStream pIn, final byte[] pBuffer) throws IOException {
        int count = 0;
        do {
            final int next = pIn.read();
            if (next < 0 || next == 10) {
                return new String(pBuffer, 0, count, "US-ASCII");
            }
            if (next == 13) {
                continue;
            }
            pBuffer[count++] = (byte)next;
        } while (count < pBuffer.length);
        throw new IOException("HTTP Header too long");
    }

    public static void parseAuthorization(final XmlRpcHttpRequestConfigImpl pConfig, String pLine) {
        if (pLine == null) {
            return;
        }
        pLine = pLine.trim();
        final StringTokenizer st = new StringTokenizer(pLine);
        if (!st.hasMoreTokens()) {
            return;
        }
        final String type = st.nextToken();
        if (!"basic".equalsIgnoreCase(type)) {
            return;
        }
        if (!st.hasMoreTokens()) {
            return;
        }
        final String auth = st.nextToken();
        try {
            final byte[] c = Base64.decode(auth.toCharArray(), 0, auth.length());
            String enc = pConfig.getBasicEncoding();
            if (enc == null) {
                enc = "UTF-8";
            }
            final String str = new String(c, enc);
            final int col = str.indexOf(58);
            if (col >= 0) {
                pConfig.setBasicUserName(str.substring(0, col));
                pConfig.setBasicPassword(str.substring(col + 1));
            }
        }
        catch (Throwable t) {}
    }
}
