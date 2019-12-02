package com.bdoemu.commons.xml.factory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @ClassName NodeForEachList
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/3 11:35
 * VERSION 1.0
 */

public abstract class NodeForEachList {
    public NodeForEachList(final String listNodeName, final String nodeName, final Node node) {
        final NodeList nList = node.getChildNodes();

        new NodeForEach(nList) {
            @Override
            protected boolean read(final Node nNode) {
                if (nNode.getNodeName().equalsIgnoreCase(listNodeName)) {
                    new NodeForEach(nNode.getChildNodes()) {
                        @Override
                        protected boolean read(final Node nNode) {
                            if (nNode.getNodeName().equalsIgnoreCase(nodeName)) {
                                NodeForEachList.this.readList(nNode);
                            }
                            return true;
                        }
                    };
                }
                return true;
            }
        };
    }

    protected abstract boolean readList(final Node p0);
}
