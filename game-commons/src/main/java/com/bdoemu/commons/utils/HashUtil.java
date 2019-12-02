package com.bdoemu.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @ClassName HashUtil
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/24 16:19
 * VERSION 1.0
 */

public class HashUtil {
    private static final Logger log;

    public static long generateHashA(final String actionName) {
        return generateHash(actionName, 1);
    }

    public static long generateHashB(final String actionName) {
        return generateHash(actionName, 2);
    }

    public static long generateHashC(final String actionName) {
        return generateHash(actionName, 3);
    }

    public static long generateHashA(final String actionName, final String charset) {
        try {
            return generateHash(new String(actionName.getBytes(charset)), 1);
        }
        catch (UnsupportedEncodingException e) {
            HashUtil.log.error("Error while generateHashA", (Throwable)e);
            return 0L;
        }
    }

    public static long generateHashB(final String actionName, final String charset) {
        try {
            return generateHash(new String(actionName.getBytes(charset)), 2);
        }
        catch (UnsupportedEncodingException e) {
            HashUtil.log.error("Error while generateHashB", (Throwable)e);
            return 0L;
        }
    }

    public static long generateHashC(final String actionName, final String charset) {
        try {
            return generateHash(new String(actionName.getBytes(charset)), 3);
        }
        catch (UnsupportedEncodingException e) {
            HashUtil.log.error("Error while generateHashC", (Throwable)e);
            return 0L;
        }
    }

    private static long generateHash(final String str, final int type) {
        int blockCount = 0;
        final ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        int length = str.length();
        int v3 = length - 558228019;
        int v4 = length - 558228019;
        int v5 = length - 558228019;
        int offSet = 0;
        if (type == 1) {
            if (length > 12) {
                blockCount = (length - 13) / 12 + 1;
                do {
                    final int v6 = buffer.getInt(8 + offSet) + v3;
                    final int v7 = buffer.getInt(4 + offSet) + v5;
                    final int v8 = Integer.rotateLeft(v6, 4);
                    final int v9 = v4 + buffer.getInt(offSet) - v6 ^ v8;
                    final int v10 = v7 + v6;
                    final int v11 = v7 - v9;
                    final int v12 = Integer.rotateLeft(v9, 6);
                    final int v13 = v10 + v9;
                    final int v14 = v11 ^ v12;
                    final int v15 = v10 - v14;
                    final int v16 = Integer.rotateLeft(v14, 8);
                    final int v17 = v13 + v14;
                    final int v18 = v15 ^ v16;
                    final int v19 = v13 - v18;
                    final int v20 = Integer.rotateLeft(v18, 16);
                    final int v21 = v17 + v18;
                    final int v22 = v19 ^ v20;
                    final int v23 = v17 - v22;
                    final int v24 = Integer.rotateRight(v22, 13);
                    v4 = v21 + v22;
                    final int v25 = v23 ^ v24;
                    final int v26 = Integer.rotateLeft(v25, 4);
                    v3 = (v21 - v25 ^ v26);
                    v5 = v4 + v25;
                    length -= 12;
                    offSet += 12;
                } while (--blockCount > 0);
            }
            final int remainingSize = buffer.capacity() - offSet;
            switch (remainingSize) {
                case 12: {
                    v3 += buffer.getInt(offSet + 8);
                }
                case 8: {
                    v5 += buffer.getInt(offSet + 4);
                }
                case 4: {
                    v4 += buffer.getInt(offSet);
                    break;
                }
                case 11: {
                    v5 += buffer.getInt(offSet + 4);
                    v3 += readD3(buffer, offSet + 8);
                    v4 += buffer.getInt(offSet);
                    break;
                }
                case 10: {
                    v5 += buffer.getInt(offSet + 4);
                    v3 += readH(buffer, offSet + 8);
                    v4 += buffer.getInt(offSet);
                    break;
                }
                case 9: {
                    v5 += buffer.getInt(offSet + 4);
                    v3 += readC(buffer, offSet + 8);
                    v4 += buffer.getInt(offSet);
                    break;
                }
                case 7: {
                    v5 += readD3(buffer, offSet + 4);
                    v4 += buffer.getInt(offSet);
                    break;
                }
                case 6: {
                    v5 += readH(buffer, offSet + 4);
                    v4 += buffer.getInt(offSet);
                    break;
                }
                case 5: {
                    v5 += readC(buffer, offSet + 4);
                    v4 += buffer.getInt(offSet);
                    break;
                }
                case 3: {
                    v4 += readD3(buffer, offSet);
                    break;
                }
                case 2: {
                    v4 += readH(buffer, offSet);
                    break;
                }
                case 1: {
                    v4 += readC(buffer, offSet);
                    break;
                }
                case 0: {
                    return (long)v3 & 0xFFFFFFFFL;
                }
            }
        }
        if (type == 2) {
            if (length > 12) {
                blockCount = (length - 13) / 12 + 1;
                do {
                    final int v27 = v5 + readH(buffer, 4 + offSet);
                    final int v28 = (readH(buffer, 10 + offSet) << 16) + v3 + readH(buffer, 8 + offSet);
                    final int v29 = v27 + (readH(buffer, 6 + offSet) << 16);
                    final int v30 = readH(buffer, offSet);
                    final int v31 = readH(buffer, 2 + offSet) << 16;
                    final int v32 = Integer.rotateLeft(v28, 4);
                    final int v33 = v4 + v31 - v28;
                    final int v34 = v29 + v28;
                    final int v35 = v33 + v30 ^ v32;
                    final int v36 = v29 - v35;
                    final int v37 = Integer.rotateLeft(v35, 6);
                    final int v38 = v34 + v35;
                    final int v39 = v36 ^ v37;
                    final int v40 = v34 - v39;
                    final int v41 = Integer.rotateLeft(v39, 8);
                    final int v42 = v38 + v39;
                    final int v43 = v40 ^ v41;
                    final int v44 = v38 - v43;
                    final int v45 = Integer.rotateLeft(v43, 16);
                    final int v46 = v42 + v43;
                    final int v47 = v44 ^ v45;
                    final int v48 = v42 - v47;
                    final int v49 = Integer.rotateRight(v47, 13);
                    v4 = v46 + v47;
                    final int v50 = v48 ^ v49;
                    final int v51 = Integer.rotateLeft(v50, 4);
                    v3 = (v46 - v50 ^ v51);
                    v5 = v4 + v50;
                    length -= 12;
                    offSet += 12;
                } while (--blockCount > 0);
            }
            final int remainingSize = buffer.capacity() - offSet;
            switch (remainingSize) {
                case 12: {
                    v3 += (readH(buffer, 10 + offSet) << 16) + readH(buffer, 8 + offSet);
                }
                case 8: {
                    v5 += (readH(buffer, 6 + offSet) << 16) + readH(buffer, 4 + offSet);
                }
                case 4: {
                    v4 += (readH(buffer, 2 + offSet) << 16) + readH(buffer, offSet);
                    break;
                }
                case 11: {
                    v3 += readC(buffer, 10 + offSet) << 16;
                }
                case 10: {
                    v3 += readH(buffer, 8 + offSet);
                    v5 += (readH(buffer, 6 + offSet) << 16) + readH(buffer, 4 + offSet);
                    v4 += (readH(buffer, 2 + offSet) << 16) + readH(buffer, offSet);
                    break;
                }
                case 9: {
                    v3 += readC(buffer, 8 + offSet);
                    v5 += (readH(buffer, 6 + offSet) << 16) + readH(buffer, 4 + offSet);
                    v4 += (readH(buffer, 2 + offSet) << 16) + readH(buffer, offSet);
                    break;
                }
                case 7: {
                    v5 += readC(buffer, 6 + offSet) << 16;
                }
                case 6: {
                    v5 += readH(buffer, 4 + offSet);
                    v4 += (readH(buffer, 2 + offSet) << 16) + readH(buffer, offSet);
                    break;
                }
                case 5: {
                    v5 += readC(buffer, 4 + offSet);
                    v4 += (readH(buffer, 2 + offSet) << 16) + readH(buffer, offSet);
                    break;
                }
                case 3: {
                    v4 += readC(buffer, 2 + offSet) << 16;
                }
                case 2: {
                    v4 += readH(buffer, offSet);
                    break;
                }
                case 1: {
                    v4 += readC(buffer, offSet);
                    break;
                }
                case 0: {
                    return (long)v3 & 0xFFFFFFFFL;
                }
            }
        }
        if (type == 3) {
            if (length > 12) {
                blockCount = (length - 13) / 12 + 1;
                do {
                    final int v52 = (readC(buffer, 5 + offSet) + (readC(buffer, 6 + offSet) + (readC(buffer, 7 + offSet) << 8) << 8) << 8) + readC(buffer, 4 + offSet) + v5;
                    final int v53 = (readC(buffer, 9 + offSet) + (readC(buffer, 10 + offSet) + (readC(buffer, 11 + offSet) << 8) << 8) << 8) + v3 + readC(buffer, 8 + offSet);
                    final int v54 = Integer.rotateLeft(v53, 4);
                    final int v55 = readC(buffer, offSet);
                    final int v56 = readC(buffer, 1 + offSet) + (readC(buffer, 2 + offSet) + (readC(buffer, 3 + offSet) << 8) << 8) << 8;
                    final int v57 = v55 + v56 - v53;
                    final int v58 = v52 + v53;
                    final int v59 = v4 + v57 ^ v54;
                    final int v60 = v52 - v59;
                    final int v61 = Integer.rotateLeft(v59, 6);
                    final int v62 = v58 + v59;
                    final int v63 = v60 ^ v61;
                    final int v64 = v58 - v63;
                    final int v65 = Integer.rotateLeft(v63, 8);
                    final int v66 = v62 + v63;
                    final int v67 = v64 ^ v65;
                    final int v68 = v62 - v67;
                    final int v69 = Integer.rotateLeft(v67, 16);
                    final int v70 = v66 + v67;
                    final int v71 = v68 ^ v69;
                    final int v72 = v66 - v71;
                    final int v73 = Integer.rotateRight(v71, 13);
                    v4 = v70 + v71;
                    final int v74 = v72 ^ v73;
                    final int v75 = Integer.rotateLeft(v74, 4);
                    v3 = (v70 - v74 ^ v75);
                    v5 = v4 + v74;
                    length -= 12;
                    offSet += 12;
                } while (--blockCount > 0);
            }
            final int remainingSize = buffer.capacity() - offSet;
            switch (remainingSize) {
                case 12: {
                    v3 += readC(buffer, 11 + offSet) << 24;
                }
                case 11: {
                    v3 += readC(buffer, 10 + offSet) << 16;
                }
                case 10: {
                    v3 += readC(buffer, 9 + offSet) << 8;
                }
                case 9: {
                    v3 += readC(buffer, 8 + offSet);
                }
                case 8: {
                    v5 += readC(buffer, 7 + offSet) << 24;
                }
                case 7: {
                    v5 += readC(buffer, 6 + offSet) << 16;
                }
                case 6: {
                    v5 += readC(buffer, 5 + offSet) << 8;
                }
                case 5: {
                    v5 += readC(buffer, 4 + offSet);
                }
                case 4: {
                    v4 += readC(buffer, 3 + offSet) << 24;
                }
                case 3: {
                    v4 += readC(buffer, 2 + offSet) << 16;
                }
                case 2: {
                    v4 += readC(buffer, 1 + offSet) << 8;
                }
                case 1: {
                    v4 += readC(buffer, offSet);
                    break;
                }
                case 0: {
                    return (long)v3 & 0xFFFFFFFFL;
                }
            }
        }
        final int v76 = Integer.rotateLeft(v5, 14);
        final int v77 = (v5 ^ v3) - v76;
        final int v78 = Integer.rotateLeft(v77, 11);
        final int v79 = (v4 ^ v77) - v78;
        final int v80 = Integer.rotateRight(v79, 7);
        final int v81 = (v79 ^ v5) - v80;
        final int v82 = Integer.rotateLeft(v81, 16);
        final int v83 = (v81 ^ v77) - v82;
        final int v84 = Integer.rotateLeft(v83, 4);
        final int v85 = (v79 ^ v83) - v84;
        final int v86 = Integer.rotateLeft(v85, 14);
        final int v87 = (v85 ^ v81) - v86;
        final int v88 = Integer.rotateRight(v87, 8);
        return (long)((v87 ^ v83) - v88) & 0xFFFFFFFFL;
    }

    private static final int readH(final ByteBuffer buffer, final int offset) {
        return buffer.getShort(offset) & 0xFFFF;
    }

    private static final int readC(final ByteBuffer buffer, final int offset) {
        return buffer.get(offset) & 0xFF;
    }

    private static final int readD3(final ByteBuffer buffer, final int offset) {
        int value = buffer.get(offset) & 0xFF;
        value |= (buffer.get(offset + 1) << 8 & 0xFF00);
        value |= (buffer.get(offset + 2) << 16 & 0xFF0000);
        return value;
    }

    static {
        log = LoggerFactory.getLogger((Class)HashUtil.class);
    }
}
