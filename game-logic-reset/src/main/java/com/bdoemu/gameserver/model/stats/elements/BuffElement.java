package com.bdoemu.gameserver.model.stats.elements;

import com.bdoemu.gameserver.model.stats.enums.ElementEnum;

/**
 * @ClassName BuffElement
 * @Description  buff 元素
 * @Author JiangBangMing
 * @Date 2019/7/6 12:00
 * VERSION 1.0
 */

public class BuffElement extends Element{

    public BuffElement(final float value) {
        super(ElementEnum.BUFF, value);
    }

    public BuffElement(final float value, final boolean isMaxValue) {
        super(ElementEnum.BUFF, value, isMaxValue);
    }

    public BuffElement(final int[] dValue) {
        super(ElementEnum.BUFF, dValue);
    }

    public BuffElement(final float value, final int[] dValue) {
        super(ElementEnum.BUFF, value, dValue);
    }
}
