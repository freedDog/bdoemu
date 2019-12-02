package com.bdoemu.gameserver.model.stats.elements;

import com.bdoemu.gameserver.model.stats.enums.ElementEnum;

/**
 * @ClassName BaseElement
 * @Description  基础元素
 * @Author JiangBangMing
 * @Date 2019/7/6 10:40
 * VERSION 1.0
 */

public class BaseElement extends Element{

    public BaseElement(final float value) {
        super(ElementEnum.BASE, value);
    }

    public BaseElement(final int[] dValue) {
        super(ElementEnum.BASE, dValue);
    }
}
