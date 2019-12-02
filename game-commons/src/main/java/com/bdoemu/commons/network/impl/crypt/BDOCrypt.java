package com.bdoemu.commons.network.impl.crypt;

import com.bdoemu.commons.network.ICipher;
import com.bdoemu.commons.utils.BitUtils;
import com.bdoemu.commons.utils.HexUtils;
import com.bdoemu.commons.utils.Rnd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidAlgorithmParameterException;

/**
 * @ClassName BDOCrypt
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/24 14:58
 * VERSION 1.0
 */

public class BDOCrypt implements ICipher {
    private static final Logger log;
    private byte[] frameWorkData;
    private byte[] aesKey;
    private byte[] sessionKey;
    private ByteBuffer xorKey;

    public BDOCrypt() {
        this.frameWorkData = new byte[108];
        this.aesKey = new byte[16];
        this.initFrameWorkData();
        this.initAESKey();
        (this.xorKey = ByteBuffer.wrap(HexUtils.hex2Byte("672FED5B59F159AFD78B165BFAD0EE4A429CEC438D88D4F9D238BA55EE32824293941BA5FDD63D1BA871C538B6CDE142654685105F3EB1211293E1E47A01322B4ED3344D79096CF520653F9B1C1A553777909BBC6CF40403B5F77024FDF1A80C8C4A9697FFE7B33354608CE72A0CFD8C1711F2187F1BB398779A287422835E9A7BE201DB4DD3344D00000000000000000000000000000000"))).order(ByteOrder.LITTLE_ENDIAN);
    }

    private void initFrameWorkData() {
        for (int i = 0; i < this.frameWorkData.length; ++i) {
            this.frameWorkData[i] = (byte) Rnd.get(1, 255);
        }
        this.frameWorkData[0] = 0;
        this.frameWorkData[1] = 0;
    }

    @Override
    public boolean decrypt(final ByteBuffer buf, final int offset, final int size) {
        this.xor(buf, offset, size);
        return true;
    }

    @Override
    public boolean encrypt(final ByteBuffer buf, final int offset, final int size) {
        this.xor(buf, offset, size);
        return true;
    }

    @Override
    public byte[] getFrameWorkData() {
        final byte[] data = new byte[this.frameWorkData.length - 2];
        System.arraycopy(this.frameWorkData, 2, data, 0, data.length);
        this.enDecryptBlock(data, true);
        return data;
    }

    private void enDecryptBlock(final byte[] data, final boolean result) {
        final int offSet = 2;
        try {
            final Decrypt decryptor = new Decrypt(this.aesKey, result);
            decryptor.init();
            byte[] res = decryptor.getCipher().doFinal(data, 64 - offSet, 16);
            System.arraycopy(res, 0, data, 64 - offSet, 16);
            res = decryptor.getCipher().doFinal(data, 17 - offSet, 16);
            System.arraycopy(res, 0, data, 17 - offSet, 16);
            res = decryptor.getCipher().doFinal(data, 40 - offSet, 16);
            System.arraycopy(res, 0, data, 40 - offSet, 16);
            res = decryptor.getCipher().doFinal(data, 87 - offSet, 16);
            System.arraycopy(res, 0, data, 87 - offSet, 16);
        }
        catch (Exception e) {
            BDOCrypt.log.error("Error while enDecryptBlock()", (Throwable)e);
        }
    }

    private void xor(final ByteBuffer buffer, final int offset, final int size) {
        int v6 = 0;
        int v7 = size;
        if (size >= 16) {
            int v8 = size >> 4;
            boolean v9;
            do {
                shuffleXorKey(this.xorKey, 68);
                v7 -= 16;
                BitUtils.putArrInt(buffer, v6 + offset, this.xorKey.getInt(68) ^ BitUtils.getArrInt(buffer, v6 + offset) ^ this.xorKey.getInt(80) << 16 ^ (this.xorKey.getShort(90) & 0xFFFF));
                BitUtils.putArrInt(buffer, v6 + 4 + offset, this.xorKey.getInt(76) ^ BitUtils.getArrInt(buffer, v6 + 4 + offset) ^ this.xorKey.getInt(88) << 16 ^ (this.xorKey.getShort(98) & 0xFFFF));
                BitUtils.putArrInt(buffer, v6 + 8 + offset, this.xorKey.getInt(84) ^ BitUtils.getArrInt(buffer, v6 + 8 + offset) ^ this.xorKey.getInt(96) << 16 ^ (this.xorKey.getShort(74) & 0xFFFF));
                BitUtils.putArrInt(buffer, v6 + 12 + offset, this.xorKey.getInt(92) ^ BitUtils.getArrInt(buffer, v6 + 12 + offset) ^ this.xorKey.getInt(72) << 16 ^ (this.xorKey.getShort(82) & 0xFFFF));
                v6 += 16;
                v9 = (v8-- == 1);
            } while (!v9);
        }
        if (v7 != 0) {
            shuffleXorKey(this.xorKey, 68);
            this.xorKey.putInt(136, this.xorKey.getInt(68) ^ this.xorKey.getInt(80) << 16 ^ BitUtils.LODWORD(Integer.toUnsignedLong(this.xorKey.getInt(88)) >> 16));
            this.xorKey.putInt(140, this.xorKey.getInt(76) ^ this.xorKey.getInt(88) << 16 ^ BitUtils.LODWORD(Integer.toUnsignedLong(this.xorKey.getInt(96)) >> 16));
            this.xorKey.putInt(144, this.xorKey.getInt(84) ^ this.xorKey.getInt(96) << 16 ^ BitUtils.LODWORD(Integer.toUnsignedLong(this.xorKey.getInt(72)) >> 16));
            this.xorKey.putInt(148, this.xorKey.getInt(92) ^ this.xorKey.getInt(72) << 16 ^ BitUtils.LODWORD(Integer.toUnsignedLong(this.xorKey.getInt(80)) >> 16));
            int result = 0;
            if (result < v7) {
                do {
                    final int pos = v6 + offset + result;
                    buffer.put(pos, (byte)(buffer.get(pos) ^ (this.xorKey.get(136 + result) & 0xFF)));
                } while (++result < v7);
            }
        }
        if (this.sessionKey == null) {
            this.sessionKey = new byte[16];
            System.arraycopy(this.frameWorkData, 64, this.sessionKey, 0, 16);
            final ByteBuffer bufferSessionKey = ByteBuffer.wrap(this.sessionKey);
            bufferSessionKey.order(ByteOrder.LITTLE_ENDIAN);
            shuffleXorKeyBaseOnSessionKeyA(this.xorKey, bufferSessionKey);
            shuffleXorKeyBaseOnSessionKeyB(this.xorKey, bufferSessionKey);
        }
    }

    public static void shuffleXorKey(final ByteBuffer bufferKey, final int offSet) {
        int v5 = 0;
        final int v6 = bufferKey.getInt(offSet + 32);
        final int v7 = bufferKey.getInt(offSet + 64);
        final int v8 = bufferKey.getInt(offSet + 0);
        v5 = v6 + v7;
        bufferKey.putInt(offSet + 32, v5);
        final int v9 = -((Integer.toUnsignedLong(v5) < Integer.toUnsignedLong(v6)) ? 1 : 0);
        long v10 = Integer.toUnsignedLong(v5 + v8);
        v10 *= v10;
        final int v11 = bufferKey.getInt(offSet + 36);
        final int v13;
        final int v12 = v13 = (BitUtils.LODWORD(v10) ^ BitUtils.HIDWORD(v10));
        v10 = BitUtils.LODWORD(v10, v11 + -v9 - 749914925);
        boolean v14 = Integer.toUnsignedLong(BitUtils.LODWORD(v10)) < Integer.toUnsignedLong(v11);
        bufferKey.putInt(offSet + 36, BitUtils.LODWORD(v10));
        long v15 = Integer.toUnsignedLong(BitUtils.LODWORD(v10) + bufferKey.getInt(offSet + 4));
        v15 *= v15;
        final int v16 = bufferKey.getInt(offSet + 40);
        final int v18;
        final int v17 = v18 = (BitUtils.LODWORD(v15) ^ BitUtils.HIDWORD(v15));
        v15 = BitUtils.LODWORD(v15, v16 + (v14 ? 1 : 0) + 886263092);
        v14 = (Integer.toUnsignedLong(BitUtils.LODWORD(v15)) < Integer.toUnsignedLong(v16));
        bufferKey.putInt(offSet + 40, BitUtils.LODWORD(v15));
        long v19 = Integer.toUnsignedLong(bufferKey.getInt(offSet + 8) + BitUtils.LODWORD(v15));
        v19 *= v19;
        final int v20 = Integer.rotateLeft(v17, 16);
        int v21 = BitUtils.LODWORD(v19) ^ BitUtils.HIDWORD(v19);
        v19 = BitUtils.LODWORD(v19, Integer.rotateLeft(v12, 16));
        v19 = BitUtils.LODWORD(v19, v21 + v20 + BitUtils.LODWORD(v19));
        final int v22 = bufferKey.getInt(offSet + 44);
        bufferKey.putInt(offSet + 8, BitUtils.LODWORD(v19));
        v19 = BitUtils.LODWORD(v19, v22 + (v14 ? 1 : 0) + 1295307597);
        v14 = (Integer.toUnsignedLong(BitUtils.LODWORD(v19)) < Integer.toUnsignedLong(v22));
        bufferKey.putInt(offSet + 44, BitUtils.LODWORD(v19));
        long v23 = Integer.toUnsignedLong(BitUtils.LODWORD(v19) + bufferKey.getInt(offSet + 12));
        v23 *= v23;
        final int v24 = BitUtils.LODWORD(v23) ^ BitUtils.HIDWORD(v23);
        v23 = BitUtils.LODWORD(v23, Integer.rotateLeft(v21, 8));
        v23 = BitUtils.LODWORD(v23, v17 + v24 + BitUtils.LODWORD(v23));
        final int v25 = bufferKey.getInt(offSet + 48);
        bufferKey.putInt(offSet + 12, BitUtils.LODWORD(v23));
        v23 = BitUtils.LODWORD(v23, v25 + (v14 ? 1 : 0) - 749914925);
        bufferKey.putInt(offSet + 48, BitUtils.LODWORD(v23));
        final int v26 = (Integer.toUnsignedLong(BitUtils.LODWORD(v23)) < Integer.toUnsignedLong(v25)) ? 1 : 0;
        v21 = Integer.rotateLeft(v21, 16);
        long v27 = Integer.toUnsignedLong(BitUtils.LODWORD(v23) + bufferKey.getInt(offSet + 16));
        v27 *= v27;
        final int v28 = bufferKey.getInt(offSet + 52);
        v27 = BitUtils.HIDWORD(v27, BitUtils.HIDWORD(v27) ^ BitUtils.LODWORD(v27));
        v27 = BitUtils.LODWORD(v27, Integer.rotateLeft(v24, 16));
        final int v29 = BitUtils.HIDWORD(v27);
        final int v30 = BitUtils.LODWORD(BitUtils.HIDWORD(v27) + v27 + v21);
        v27 = BitUtils.LODWORD(v27, v28 + v26 + 886263092);
        v14 = (Integer.toUnsignedLong(BitUtils.LODWORD(v27)) < Integer.toUnsignedLong(v28));
        bufferKey.putInt(offSet + 52, BitUtils.LODWORD(v27));
        bufferKey.putInt(offSet + 16, v30);
        long test = Integer.toUnsignedLong(bufferKey.getInt(offSet + 20) + v28 + v26 + 886263092);
        test *= test;
        final int v31 = BitUtils.LODWORD(test) ^ BitUtils.HIDWORD(test);
        v27 = BitUtils.LODWORD(v27, Integer.rotateLeft(BitUtils.HIDWORD(v27), 8));
        final int v32 = bufferKey.getInt(offSet + 56);
        bufferKey.putInt(offSet + 20, BitUtils.LODWORD(v24 + v31 + v27));
        v27 = BitUtils.LODWORD(v27, v32 + (v14 ? 1 : 0) + 1295307597);
        v14 = (Integer.toUnsignedLong(BitUtils.LODWORD(v27)) < Integer.toUnsignedLong(v32));
        bufferKey.putInt(offSet + 56, BitUtils.LODWORD(v27));
        long v33 = Integer.toUnsignedLong(BitUtils.LODWORD(v27) + bufferKey.getInt(offSet + 24));
        v33 *= v33;
        final int v34 = Integer.rotateLeft(v29, 16);
        int v35 = BitUtils.LODWORD(v33) ^ BitUtils.HIDWORD(v33);
        v33 = Integer.rotateLeft(v31, 16);
        bufferKey.putInt(offSet + 24, v35 + BitUtils.LODWORD(v33) + v34);
        final int v36 = bufferKey.getInt(offSet + 60);
        final int v37 = BitUtils.LODWORD(Integer.toUnsignedLong(v36) + (v14 ? 1 : 0) - 749914925L);
        bufferKey.putInt(offSet + 60, v37);
        long v38 = Integer.toUnsignedLong(v37 + bufferKey.getInt(offSet + 28));
        v38 *= v38;
        v38 = BitUtils.HIDWORD(v38, BitUtils.HIDWORD(v38) ^ BitUtils.LODWORD(v38));
        v38 = BitUtils.LODWORD(v38, Integer.rotateLeft(v35, 8));
        v35 = Integer.rotateLeft(v35, 16);
        bufferKey.putInt(offSet + 28, v31 + BitUtils.HIDWORD(v38) + BitUtils.LODWORD(v38));
        v38 = BitUtils.LODWORD(v38, Integer.rotateLeft(BitUtils.HIDWORD(v38), 16));
        final int v39 = Integer.rotateLeft(v13, 8);
        bufferKey.putInt(offSet, v13 + BitUtils.LODWORD(v38) + v35);
        bufferKey.putInt(offSet + 4, v18 + BitUtils.HIDWORD(v38) + v39);
        final int result = ((Integer.toUnsignedLong(v37) < Integer.toUnsignedLong(v36)) ? 1 : 0) + 1295307597;
        bufferKey.putInt(offSet + 64, result);
    }

    private static void shuffleXorKeyBaseOnSessionKeyB(final ByteBuffer bufferKey, final ByteBuffer bufferSessionKey) {
        final int v2 = bufferSessionKey.getInt(6);
        final int v3 = bufferSessionKey.getInt(2);
        final int v4 = (v2 & 0xFFFF0000) | BitUtils.LODWORD(Integer.toUnsignedLong(v3) >> 16);
        final int v5 = Short.toUnsignedInt((short)v3) | v2 << 16;
        bufferKey.putInt(100, v3 ^ bufferKey.getInt(32));
        bufferKey.putInt(104, v4 ^ bufferKey.getInt(36));
        bufferKey.putInt(108, v2 ^ bufferKey.getInt(40));
        bufferKey.putInt(112, v5 ^ bufferKey.getInt(44));
        bufferKey.putInt(116, v3 ^ bufferKey.getInt(48));
        bufferKey.putInt(120, v4 ^ bufferKey.getInt(52));
        bufferKey.putInt(124, v2 ^ bufferKey.getInt(56));
        bufferKey.putInt(128, v5 ^ bufferKey.getInt(60));
        bufferKey.putInt(68, bufferKey.getInt(0));
        bufferKey.putInt(72, bufferKey.getInt(4));
        bufferKey.putInt(76, bufferKey.getInt(8));
        bufferKey.putInt(80, bufferKey.getInt(12));
        bufferKey.putInt(84, bufferKey.getInt(16));
        bufferKey.putInt(88, bufferKey.getInt(20));
        bufferKey.putInt(92, bufferKey.getInt(24));
        bufferKey.putInt(96, bufferKey.getInt(28));
        int v6 = bufferKey.getInt(64);
        bufferKey.putInt(132, v6);
        int v7 = bufferKey.getInt(100);
        int v8 = bufferKey.getInt(104);
        int v9 = bufferKey.getInt(72);
        int v10 = bufferKey.getInt(108);
        int v11 = bufferKey.getInt(76);
        int v12 = bufferKey.getInt(112);
        int v13 = bufferKey.getInt(80);
        int v14 = bufferKey.getInt(116);
        int v15 = bufferKey.getInt(84);
        int v16 = bufferKey.getInt(120);
        int v17 = bufferKey.getInt(88);
        int v18 = bufferKey.getInt(124);
        int v19 = bufferKey.getInt(92);
        int v20 = bufferKey.getInt(128);
        int v21 = bufferKey.getInt(96);
        int v22 = 4;
        int v25;
        int v31;
        int v50;
        do {
            final int v23 = v7;
            final int v24 = v25 = v6 + v7;
            final int v26 = -((Integer.toUnsignedLong(v24) < Integer.toUnsignedLong(v23)) ? 1 : 0);
            long v27 = Integer.toUnsignedLong(v24 + bufferKey.getInt(68));
            v27 *= v27;
            final int v28 = BitUtils.LODWORD(v27) ^ BitUtils.HIDWORD(v27);
            v27 = BitUtils.LODWORD(v27, v8);
            final int v29 = -v26 - 749914925 + v8;
            final int v30 = v28;
            v31 = v29;
            long test = Integer.toUnsignedLong(v29 + v9);
            test *= test;
            final int v32 = BitUtils.LODWORD(test) ^ BitUtils.HIDWORD(test);
            v27 = BitUtils.HIDWORD(v27, ((Integer.toUnsignedLong(v29) < Integer.toUnsignedLong(BitUtils.LODWORD(v27))) ? 1 : 0) + 886263092 + v10);
            final int v33 = v32;
            boolean v34 = Integer.toUnsignedLong(BitUtils.HIDWORD(v27)) < Integer.toUnsignedLong(v10);
            v10 += ((Integer.toUnsignedLong(v29) < Integer.toUnsignedLong(BitUtils.LODWORD(v27))) ? 1 : 0) + 886263092;
            long v35 = Integer.toUnsignedLong(v11 + BitUtils.HIDWORD(v27));
            v35 *= v35;
            final int v36 = BitUtils.LODWORD(v35) ^ BitUtils.HIDWORD(v35);
            v35 = BitUtils.HIDWORD(v35, Integer.rotateLeft(v28, 16));
            v35 = BitUtils.LODWORD(v35, Integer.rotateLeft(v32, 16));
            v11 = BitUtils.LODWORD(v36 + v35 + BitUtils.HIDWORD(v35));
            v35 = BitUtils.HIDWORD(v35, (v34 ? 1 : 0) + 1295307597 + v12);
            v34 = (Integer.toUnsignedLong(BitUtils.HIDWORD(v35)) < Integer.toUnsignedLong(v12));
            v12 = BitUtils.HIDWORD(v35);
            long v37 = Integer.toUnsignedLong(v13 + BitUtils.HIDWORD(v35));
            v37 *= v37;
            final int v38 = BitUtils.LODWORD(v37) ^ BitUtils.HIDWORD(v37);
            v37 = BitUtils.LODWORD(v37, Integer.rotateLeft(v36, 8));
            final int v39 = v38;
            v13 = BitUtils.LODWORD(v32 + v38 + v37);
            v37 = BitUtils.HIDWORD(v37, (v34 ? 1 : 0) - 749914925 + v14);
            v34 = (Integer.toUnsignedLong(BitUtils.HIDWORD(v37)) < Integer.toUnsignedLong(v14));
            v14 = BitUtils.HIDWORD(v37);
            long v40 = Integer.toUnsignedLong(v15 + BitUtils.HIDWORD(v37));
            v40 *= v40;
            final int v42;
            final int v41 = v42 = (BitUtils.LODWORD(v40) ^ BitUtils.HIDWORD(v40));
            v40 = BitUtils.LODWORD(v40, Integer.rotateLeft(v38, 16));
            v40 = BitUtils.HIDWORD(v40, Integer.rotateLeft(v36, 16));
            v15 = BitUtils.LODWORD(v41 + v40 + BitUtils.HIDWORD(v40));
            v40 = BitUtils.HIDWORD(v40, (v34 ? 1 : 0) + 886263092 + v16);
            v34 = (Integer.toUnsignedLong(BitUtils.HIDWORD(v40)) < Integer.toUnsignedLong(v16));
            v16 = BitUtils.HIDWORD(v40);
            long v43 = Integer.toUnsignedLong(v17 + BitUtils.HIDWORD(v40));
            v43 *= v43;
            final int v44 = BitUtils.LODWORD(v43) ^ BitUtils.HIDWORD(v43);
            v43 = BitUtils.LODWORD(v43, Integer.rotateLeft(v41, 8));
            v17 = BitUtils.LODWORD(v39 + v44 + v43);
            v43 = BitUtils.HIDWORD(v43, (v34 ? 1 : 0) + 1295307597 + v18);
            v34 = (Integer.toUnsignedLong(BitUtils.HIDWORD(v43)) < Integer.toUnsignedLong(v18));
            v18 = BitUtils.HIDWORD(v43);
            long v45 = Integer.toUnsignedLong(v19 + BitUtils.HIDWORD(v43));
            v45 *= v45;
            int v46 = BitUtils.LODWORD(v45) ^ BitUtils.HIDWORD(v45);
            v45 = BitUtils.HIDWORD(v45, Integer.rotateLeft(v42, 16));
            v45 = BitUtils.LODWORD(v45, Integer.rotateLeft(v44, 16));
            final int v47 = v20;
            v19 = BitUtils.LODWORD(v46 + v45 + BitUtils.HIDWORD(v45));
            v20 += (v34 ? 1 : 0) - 749914925;
            long v48 = Integer.toUnsignedLong(v20 + v21);
            v48 *= v48;
            v48 = BitUtils.HIDWORD(v48, BitUtils.LODWORD(v48) ^ BitUtils.HIDWORD(v48));
            v48 = BitUtils.LODWORD(v48, Integer.rotateLeft(v46, 8));
            v46 = Integer.rotateLeft(v46, 16);
            v21 = BitUtils.LODWORD(v44 + BitUtils.HIDWORD(v48) + v48);
            final int v49 = Integer.rotateLeft(v30, 8);
            v48 = BitUtils.LODWORD(v48, Integer.rotateLeft(BitUtils.HIDWORD(v48), 16));
            v50 = v33 + BitUtils.HIDWORD(v48) + v49;
            v34 = (Integer.toUnsignedLong(v20) < Integer.toUnsignedLong(v47));
            v8 = v31;
            bufferKey.putInt(68, BitUtils.LODWORD(v30 + v48 + v46));
            v7 = v25;
            v6 = (v34 ? 1 : 0) + 1295307597;
            v9 = v50;
        } while (--v22 != 0);
        bufferKey.putInt(128, v20);
        bufferKey.putInt(100, v25);
        bufferKey.putInt(104, v31);
        bufferKey.putInt(108, v10);
        bufferKey.putInt(76, v11);
        bufferKey.putInt(112, v12);
        bufferKey.putInt(80, v13);
        bufferKey.putInt(116, v14);
        bufferKey.putInt(84, v15);
        bufferKey.putInt(120, v16);
        bufferKey.putInt(88, v17);
        bufferKey.putInt(124, v18);
        bufferKey.putInt(132, v6);
        bufferKey.putInt(72, v50);
        bufferKey.putInt(92, v19);
        bufferKey.putInt(96, v21);
    }

    private static void shuffleXorKeyBaseOnSessionKeyA(final ByteBuffer bufferKey, final ByteBuffer bufferSessionKey) {
        final int v2 = bufferSessionKey.getInt(12);
        int v3 = bufferSessionKey.getInt(0);
        final int v4 = bufferSessionKey.getInt(4);
        final int v5 = bufferSessionKey.getInt(8);
        final int v6 = BitUtils.LODWORD(bufferSessionKey.getLong(8) >> 16);
        bufferKey.putInt(0, v3);
        bufferKey.putInt(4, v6);
        bufferKey.putInt(8, v4);
        bufferKey.putInt(12, BitUtils.LODWORD(Integer.toUnsignedLong(v3) << 16) | BitUtils.LODWORD(Integer.toUnsignedLong(v2) >> 16));
        bufferKey.putInt(16, v5);
        bufferKey.putInt(20, BitUtils.LODWORD(Integer.toUnsignedLong(v3) >> 16) | BitUtils.LODWORD(Integer.toUnsignedLong(v4) << 16));
        bufferKey.putInt(24, v2);
        bufferKey.putInt(28, BitUtils.LODWORD(Integer.toUnsignedLong(v4) >> 16) | BitUtils.LODWORD(Integer.toUnsignedLong(v5) << 16));
        final int v7 = Integer.rotateLeft(v5, 16);
        bufferKey.putInt(32, v7);
        final int v8 = Integer.rotateLeft(v2, 16);
        bufferKey.putInt(40, v8);
        final int v9 = Integer.rotateLeft(v3, 16);
        bufferKey.putInt(48, v9);
        final int v10 = Integer.rotateLeft(v4, 16);
        bufferKey.putInt(56, v10);
        bufferKey.putInt(64, 1295307597);
        bufferKey.putInt(36, v3 ^ Short.toUnsignedInt((short)(v3 ^ v4)));
        int v11 = v4;
        bufferKey.putInt(44, v4 ^ Short.toUnsignedInt((short)(v4 ^ v5)));
        bufferKey.putInt(52, v5 ^ Short.toUnsignedInt((short)(v5 ^ v2)));
        bufferKey.putInt(60, v2 ^ Short.toUnsignedInt((short)(v3 ^ v2)));
        int v12 = bufferKey.getInt(32);
        int v13 = bufferKey.getInt(36);
        int v14 = bufferKey.getInt(4);
        int v15 = bufferKey.getInt(64);
        int v16 = bufferKey.getInt(40);
        int v17 = bufferKey.getInt(44);
        int v18 = bufferKey.getInt(12);
        int v19 = bufferKey.getInt(48);
        int v20 = bufferKey.getInt(20);
        int v21 = bufferKey.getInt(24);
        int v22 = bufferKey.getInt(52);
        final int v23 = bufferKey.getInt(56);
        int v24 = bufferKey.getInt(60);
        final int v25 = bufferKey.getInt(28);
        int v26 = bufferKey.getInt(16);
        int v27 = v23;
        int v28 = v25;
        int v29 = 4;
        int v32;
        int v38;
        int v57;
        int v58;
        while (true) {
            final int v30 = v12;
            final int v31 = v32 = v15 + v12;
            final int v33 = -((Integer.toUnsignedLong(v31) < Integer.toUnsignedLong(v30)) ? 1 : 0);
            long v34 = Integer.toUnsignedLong(v3 + v31);
            v34 *= v34;
            final int v35 = BitUtils.LODWORD(v34) ^ BitUtils.HIDWORD(v34);
            v34 = BitUtils.LODWORD(v34, v13);
            final int v36 = -v33 - 749914925 + v13;
            final int v37 = v35;
            v38 = v36;
            final int v39 = (Integer.toUnsignedLong(v36) < Integer.toUnsignedLong(BitUtils.LODWORD(v34))) ? 1 : 0;
            long test = Integer.toUnsignedLong(v14 + v36);
            test *= test;
            final int v40 = BitUtils.LODWORD(test) ^ BitUtils.HIDWORD(test);
            v34 = BitUtils.HIDWORD(v34, v39 + 886263092 + v16);
            final int v41 = v40;
            boolean v42 = Integer.toUnsignedLong(BitUtils.HIDWORD(v34)) < Integer.toUnsignedLong(v16);
            v16 += v39 + 886263092;
            long v43 = Integer.toUnsignedLong(v11 + BitUtils.HIDWORD(v34));
            v43 *= v43;
            int v44 = BitUtils.LODWORD(v43) ^ BitUtils.HIDWORD(v43);
            v43 = BitUtils.HIDWORD(v43, Integer.rotateLeft(v35, 16));
            v43 = BitUtils.LODWORD(v43, Integer.rotateLeft(v40, 16));
            v11 = BitUtils.LODWORD(v44 + v43 + BitUtils.HIDWORD(v43));
            v43 = BitUtils.HIDWORD(v43, (v42 ? 1 : 0) + 1295307597 + v17);
            v42 = (Integer.toUnsignedLong(BitUtils.HIDWORD(v43)) < Integer.toUnsignedLong(v17));
            v17 = BitUtils.HIDWORD(v43);
            long v45 = Integer.toUnsignedLong(v18 + v17);
            v45 *= v45;
            final int v46 = BitUtils.LODWORD(v45) ^ BitUtils.HIDWORD(v45);
            v45 = BitUtils.LODWORD(v45, Integer.rotateLeft(v44, 8));
            v18 = BitUtils.LODWORD(v40 + v46 + v45);
            v45 = BitUtils.HIDWORD(v45, (v42 ? 1 : 0) - 749914925 + v19);
            v42 = (Integer.toUnsignedLong(BitUtils.HIDWORD(v45)) < Integer.toUnsignedLong(v19));
            v19 = BitUtils.HIDWORD(v45);
            v44 = Integer.rotateLeft(v44, 16);
            long v47 = Integer.toUnsignedLong(v26 + BitUtils.HIDWORD(v45));
            v47 *= v47;
            final int v48 = Integer.rotateLeft(v46, 16);
            final int v49 = BitUtils.LODWORD(v47) ^ BitUtils.HIDWORD(v47);
            v26 = v44 + (BitUtils.LODWORD(v47) ^ BitUtils.HIDWORD(v47)) + v48;
            v47 = BitUtils.HIDWORD(v47, (v42 ? 1 : 0) + 886263092 + v22);
            v42 = (Integer.toUnsignedLong(BitUtils.HIDWORD(v47)) < Integer.toUnsignedLong(v22));
            v22 = BitUtils.HIDWORD(v47);
            long v50 = Integer.toUnsignedLong(v20 + BitUtils.HIDWORD(v47));
            v50 *= v50;
            final int v51 = BitUtils.LODWORD(v50) ^ BitUtils.HIDWORD(v50);
            v50 = BitUtils.LODWORD(v50, Integer.rotateLeft(v49, 8));
            v20 = BitUtils.LODWORD(v46 + v51 + v50);
            v50 = BitUtils.HIDWORD(v50, (v42 ? 1 : 0) + 1295307597 + v27);
            v42 = (Integer.toUnsignedLong(BitUtils.HIDWORD(v50)) < Integer.toUnsignedLong(v27));
            v27 = BitUtils.HIDWORD(v50);
            long v52 = Integer.toUnsignedLong(v21 + BitUtils.HIDWORD(v50));
            v52 *= v52;
            int v53 = BitUtils.LODWORD(v52) ^ BitUtils.HIDWORD(v52);
            v52 = BitUtils.HIDWORD(v52, Integer.rotateLeft(v49, 16));
            v52 = BitUtils.LODWORD(v52, Integer.rotateLeft(v51, 16));
            final int v54 = v24;
            v21 = BitUtils.LODWORD(v53 + v52 + BitUtils.HIDWORD(v52));
            v24 += (v42 ? 1 : 0) - 749914925;
            long v55 = Integer.toUnsignedLong(v24 + v28);
            v55 *= v55;
            v55 = BitUtils.HIDWORD(v55, BitUtils.HIDWORD(v55) ^ BitUtils.LODWORD(v55));
            v55 = BitUtils.LODWORD(v55, Integer.rotateLeft(v53, 8));
            v53 = Integer.rotateLeft(v53, 16);
            v55 = BitUtils.LODWORD(v55, BitUtils.LODWORD(v51 + BitUtils.HIDWORD(v55) + v55));
            v13 = v38;
            v28 = BitUtils.LODWORD(v55);
            v55 = BitUtils.LODWORD(v55, Integer.rotateLeft(BitUtils.HIDWORD(v55), 16));
            final int v56 = Integer.rotateLeft(v37, 8);
            v3 = BitUtils.LODWORD(v37 + v55 + v53);
            v57 = v41 + BitUtils.HIDWORD(v55) + v56;
            v12 = v32;
            v58 = (v15 = ((Integer.toUnsignedLong(v24) < Integer.toUnsignedLong(v54)) ? 1 : 0) + 1295307597);
            if (v29-- == 1) {
                break;
            }
            v14 = v57;
        }
        final int v59 = v26;
        bufferKey.putInt(32, v32);
        bufferKey.putInt(36, v38);
        bufferKey.putInt(4, v57);
        bufferKey.putInt(40, v16);
        bufferKey.putInt(0, v3);
        bufferKey.putInt(60, v24);
        bufferKey.putInt(8, v11);
        bufferKey.putInt(64, v58);
        bufferKey.putInt(44, v17);
        bufferKey.putInt(12, v18);
        bufferKey.putInt(48, v19);
        bufferKey.putInt(16, v59);
        bufferKey.putInt(52, v22);
        bufferKey.putInt(56, v27);
        bufferKey.putInt(20, v20);
        bufferKey.putInt(24, v21);
        bufferKey.putInt(28, v28);
        bufferKey.putInt(32, bufferKey.getInt(32) ^ v59);
        bufferKey.putInt(84, v59);
        bufferKey.putInt(100, bufferKey.getInt(32));
        final int v60 = bufferKey.getInt(20);
        bufferKey.putInt(36, bufferKey.getInt(36) ^ v60);
        bufferKey.putInt(88, v60);
        bufferKey.putInt(104, bufferKey.getInt(36));
        final int v61 = bufferKey.getInt(24);
        bufferKey.putInt(40, bufferKey.getInt(40) ^ v61);
        bufferKey.putInt(92, v61);
        bufferKey.putInt(108, bufferKey.getInt(40));
        final int v62 = bufferKey.getInt(28);
        bufferKey.putInt(44, bufferKey.getInt(44) ^ v62);
        bufferKey.putInt(96, v62);
        bufferKey.putInt(112, bufferKey.getInt(44));
        final int v63 = bufferKey.getInt(0);
        bufferKey.putInt(48, bufferKey.getInt(48) ^ v63);
        bufferKey.putInt(68, v63);
        bufferKey.putInt(116, bufferKey.getInt(48));
        final int v64 = bufferKey.getInt(4);
        bufferKey.putInt(52, bufferKey.getInt(52) ^ v64);
        bufferKey.putInt(72, v64);
        bufferKey.putInt(120, bufferKey.getInt(52));
        final int v65 = bufferKey.getInt(8);
        bufferKey.putInt(56, bufferKey.getInt(56) ^ v65);
        bufferKey.putInt(76, v65);
        bufferKey.putInt(124, bufferKey.getInt(56));
        final int v66 = bufferKey.getInt(12);
        bufferKey.putInt(60, bufferKey.getInt(60) ^ v66);
        bufferKey.putInt(80, v66);
        bufferKey.putInt(128, bufferKey.getInt(60));
        final int result = bufferKey.getInt(64);
        bufferKey.putInt(132, result);
    }

    private byte sub_F48550(final int a2) {
        byte result = 0;
        switch (a2) {
            case 1: {
                result = this.frameWorkData[3];
                break;
            }
            case 2: {
                result = this.frameWorkData[62];
                break;
            }
            case 3: {
                result = this.frameWorkData[5];
                break;
            }
            case 4: {
                result = this.frameWorkData[39];
                break;
            }
            case 5: {
                result = this.frameWorkData[37];
                break;
            }
            case 6: {
                result = this.frameWorkData[33];
                break;
            }
            case 7: {
                result = this.frameWorkData[59];
                break;
            }
            case 8: {
                result = this.frameWorkData[10];
                break;
            }
            case 9: {
                result = this.frameWorkData[7];
                break;
            }
            case 10: {
                result = this.frameWorkData[104];
                break;
            }
            case 11: {
                result = this.frameWorkData[82];
                break;
            }
            case 12: {
                result = this.frameWorkData[38];
                break;
            }
            case 13: {
                result = this.frameWorkData[13];
                break;
            }
            case 14: {
                result = this.frameWorkData[84];
                break;
            }
            case 15: {
                result = this.frameWorkData[12];
                break;
            }
            default: {
                result = this.frameWorkData[15];
                break;
            }
        }
        return result;
    }

    private byte sub_F48600(final int a2) {
        byte result = 0;
        switch (a2) {
            case 1: {
                result = this.frameWorkData[81];
                break;
            }
            case 2: {
                result = this.frameWorkData[9];
                break;
            }
            case 3: {
                result = this.frameWorkData[107];
                break;
            }
            case 4: {
                result = this.frameWorkData[36];
                break;
            }
            case 5: {
                result = this.frameWorkData[106];
                break;
            }
            case 6: {
                result = this.frameWorkData[14];
                break;
            }
            case 7: {
                result = this.frameWorkData[11];
                break;
            }
            case 8: {
                result = this.frameWorkData[34];
                break;
            }
            case 9: {
                result = this.frameWorkData[56];
                break;
            }
            case 10: {
                result = this.frameWorkData[6];
                break;
            }
            case 11: {
                result = this.frameWorkData[60];
                break;
            }
            case 12: {
                result = this.frameWorkData[85];
                break;
            }
            case 13: {
                result = this.frameWorkData[35];
                break;
            }
            case 14: {
                result = this.frameWorkData[61];
                break;
            }
            case 15: {
                result = this.frameWorkData[16];
                break;
            }
            case 16: {
                result = this.frameWorkData[8];
                break;
            }
            case 17: {
                result = this.frameWorkData[63];
                break;
            }
            case 18: {
                result = this.frameWorkData[57];
                break;
            }
            case 19: {
                result = this.frameWorkData[2];
                break;
            }
            case 20: {
                result = this.frameWorkData[80];
                break;
            }
            case 21: {
                result = this.frameWorkData[105];
                break;
            }
            case 22: {
                result = this.frameWorkData[58];
                break;
            }
            case 23: {
                result = this.frameWorkData[86];
                break;
            }
            case 24: {
                result = this.frameWorkData[103];
                break;
            }
            case 25: {
                result = this.frameWorkData[83];
                break;
            }
            default: {
                result = this.frameWorkData[4];
                break;
            }
        }
        return result;
    }

    private void initAESKey() {
        int index = 0;
        do {
            final int v6 = Byte.toUnsignedInt(this.sub_F48550(index));
            final int v7 = index % 3;
            final int v8;
            byte v10;
            if ((v8 = v7) != 0) {
                if (v7 == 1) {
                    byte v9 = (byte)(index % 8);
                    if (v9 < 1) {
                        v9 = 1;
                    }
                    v10 = (byte)(v6 << v9 | v6 >> 8 - v9);
                }
                else {
                    v10 = (byte)(v6 >> 4 | 16 * v6);
                }
            }
            else {
                int v11 = index % 8;
                if (v11 < 1) {
                    v11 = 1;
                }
                v10 = (byte)(v6 >> v11 | v6 << 8 - v11);
            }
            final int v12 = Byte.toUnsignedInt(this.sub_F48600(v10));
            byte v14;
            byte v15;
            if (v8 != 0) {
                if (v8 != 1) {
                    final byte result = (byte)(v12 >> 4 | 16 * v12);
                    this.aesKey[index++] = result;
                    continue;
                }
                int v13 = index % 8;
                if (v13 < 1) {
                    v13 = 1;
                }
                v14 = (byte)(v12 << v13);
                v15 = (byte)(v12 >> 8 - v13);
            }
            else {
                int v16 = index % 8;
                if (v16 < 1) {
                    v16 = 1;
                }
                v14 = (byte)(v12 >> v16);
                v15 = (byte)(v12 << 8 - v16);
            }
            final byte result = (byte)(v14 | v15);
            this.aesKey[index++] = result;
        } while (index < this.aesKey.length);
    }

    static {
        log = LoggerFactory.getLogger((Class)BDOCrypt.class);
    }

    static class Decrypt
    {
        private byte[] key;
        private Cipher cipher;
        private boolean result;

        Decrypt(final byte[] key, final boolean result) {
            this.key = key;
            this.result = result;
        }

        public void init() throws InvalidAlgorithmParameterException {
            final SecretKeySpec skeySpec = new SecretKeySpec(this.key, "AES");
            try {
                (this.cipher = Cipher.getInstance("AES/CBC/NoPadding")).init(this.result ? 1 : 2, skeySpec, new IvParameterSpec(new byte[16]));
            }
            catch (Exception e) {
                BDOCrypt.log.error("Error while cipher init", (Throwable)e);
            }
        }

        Cipher getCipher() {
            return this.cipher;
        }

        public byte[] decrypt(final byte[] data) {
            try {
                return this.cipher.doFinal(data);
            }
            catch (IllegalBlockSizeException | BadPaddingException ex) {
                BDOCrypt.log.error("Error while decrypt", (Throwable)ex);
                return null;
            }
        }
    }
}
