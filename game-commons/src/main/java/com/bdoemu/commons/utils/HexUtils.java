package com.bdoemu.commons.utils;

import java.nio.ByteBuffer;

/**
 * @ClassName HexUtils
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/24 15:02
 * VERSION 1.0
 */

public class HexUtils {
    public static void hexToString(final String string) {
        final String[] stringArray = string.split(" ");
        final byte[] bytes = new byte[stringArray.length];
        for (int i = 0; i < bytes.length; ++i) {
            bytes[i] = (byte)Integer.parseInt(stringArray[i], 16);
        }
        System.out.println(new String(bytes));
    }

    public static String stringToHex(final String string) {
        final byte[] chars = string.getBytes();
        return byteArraytoHex(chars);
    }

    public static String byteArraytoHex(final byte[] b) {
        final StringBuilder out = new StringBuilder();
        for (final byte c : b) {
            out.append(Integer.toHexString(c & 0xFF)).append(" ");
        }
        return out.toString();
    }

    public static String toHex(final ByteBuffer data, final int endPOsition) {
        final StringBuilder result = new StringBuilder();
        int counter = 0;
        while (data.hasRemaining() && (endPOsition <= 0 || data.position() < endPOsition)) {
            if (counter % 16 == 0) {
                result.append(String.format("%04X: ", counter));
            }
            final int b = data.get() & 0xFF;
            result.append(String.format("%02X ", b));
            if (++counter % 16 == 0) {
                result.append("  ");
                toText(data, result, 16);
                result.append("\n");
            }
        }
        final int rest = counter % 16;
        if (rest > 0) {
            for (int i = 0; i < 17 - rest; ++i) {
                result.append("   ");
            }
            toText(data, result, rest);
        }
        return result.toString();
    }

    private static void toText(final ByteBuffer data, final StringBuilder result, final int cnt) {
        int charPos = data.position() - cnt;
        for (int a = 0; a < cnt; ++a) {
            final int c = data.get(charPos++);
            if (c > 31 && c < 128) {
                result.append((char)c);
            }
            else {
                result.append('.');
            }
        }
    }

    public static String fillHex(final int data, final int digits) {
        final String hex = Integer.toHexString(data);
        final StringBuilder number = new StringBuilder(Math.max(hex.length(), digits));
        for (int i = hex.length(); i < digits; ++i) {
            number.append(0);
        }
        number.append(hex);
        return number.toString();
    }

    public static byte[] hex2Byte(final String str) {
        final byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < bytes.length; ++i) {
            bytes[i] = (byte)Integer.parseInt(str.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}
