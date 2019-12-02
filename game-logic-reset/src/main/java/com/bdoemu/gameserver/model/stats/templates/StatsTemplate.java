package com.bdoemu.gameserver.model.stats.templates;

import com.bdoemu.gameserver.model.stats.elements.BaseElement;
import com.bdoemu.gameserver.model.stats.enums.StatEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName StatsTemplate
 * @Description   状态模板
 * @Author JiangBangMing
 * @Date 2019/7/9 0:04
 * VERSION 1.0
 */

public abstract class StatsTemplate {

    protected Map<StatEnum, BaseElement> baseContainer;

    public StatsTemplate() {
        this.baseContainer = new HashMap<StatEnum, BaseElement>();
    }

    public BaseElement getBaseElement(final StatEnum type) {
        return this.baseContainer.get(type);
    }
}
