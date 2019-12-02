package com.bdoemu.commons.xml.factory;


import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @ClassName NodeForEach
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/24 17:00
 * VERSION 1.0
 */

public abstract class NodeForEach {

    public NodeForEach(final NodeList nList) {
        for (int temp = 0; temp < nList.getLength() && this.read(nList.item(temp)); ++temp) {}
    }

    protected abstract boolean read(final Node node);
}
