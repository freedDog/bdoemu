package com.bdoemu.commons.collection;

import java.util.BitSet;

/**
 * @ClassName BitMask
 * @Description 位掩码
 * @Author JiangBangMing
 * @Date 2019/6/15 16:34
 * VERSION 1.0
 */

public class BitMask extends BitSet {

    private int maxBitSize;

    public BitMask(int size) {
        super(size);
        this.maxBitSize = size;
    }


    public int getMaxBitSize() {
        return this.maxBitSize;
    }



    public void setBit(int bit) {
        set(bit, true);
    }


    public void setMask(long bits) {
        clear();
        for (int i = 0; i < size(); i++) {
            if ((bits & (1 << i)) > 0L) {
                setBit(i);
            }
        }
    }

    public long getMask() {
        long bitData = 0L;
        for (int i = 0; i < size(); i++) {
            if (get(i)) {
                bitData |= (1 << i);
            }
        }
        return bitData;
    }

    public int getMaskInt() {
        int bitData = 0;
        for (int i = 0; i < size(); i++) {
            if (get(i)) {
                bitData |= 1 << i;
            }
        }
        return bitData;
    }
}
