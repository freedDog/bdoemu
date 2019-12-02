package com.bdoemu.commons.xml.models;

import org.w3c.dom.NodeList;

/**
 * @ClassName XmlDataHolder
 * @Description XML 数据持有者
 * @Author JiangBangMing
 * @Date 2019/6/24 11:43
 * VERSION 1.0
 */

public abstract class XmlDataHolder {

    public abstract void loadData(NodeList nodeList);
}
