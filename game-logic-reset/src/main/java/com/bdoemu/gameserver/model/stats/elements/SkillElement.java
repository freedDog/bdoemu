package com.bdoemu.gameserver.model.stats.elements;

import com.bdoemu.gameserver.model.stats.enums.ElementEnum;

/**
 * @ClassName SkillElement
 * @Description  技能元素
 * @Author JiangBangMing
 * @Date 2019/7/10 11:17
 * VERSION 1.0
 */

public class SkillElement extends Element{

    public SkillElement(final float value) {
        super(ElementEnum.SKILL, value);
    }

    public SkillElement(final int[] dValue) {
        super(ElementEnum.SKILL, dValue);
    }
}
