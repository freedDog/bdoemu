package com.bdoemu.gameserver.model.stats.elements;

import com.bdoemu.gameserver.model.stats.enums.ElementEnum;

/**
 * @ClassName ItemElement
 * @Description  道具元素
 * @Author JiangBangMing
 * @Date 2019/7/9 17:25
 * VERSION 1.0
 */

public class ItemElement extends Element{

    public ItemElement(final float value, final boolean result) {
        super(ElementEnum.ITEM, value, result);
    }

    public ItemElement(final float value) {
        super(ElementEnum.ITEM, value);
    }

    public ItemElement(final int[] dValue) {
        super(ElementEnum.ITEM, dValue);
    }
}
