package com.bdoemu.commons.utils;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.AbstractList;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/**
 * @ClassName XmlUtils
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/24 20:19
 * VERSION 1.0
 */

public class XmlUtils {

    public static List<Node> asList(final NodeList n) {
        return (n.getLength() == 0) ? Collections.emptyList() : new NodeListWrapper(n);
    }

    private static final class NodeListWrapper extends AbstractList<Node> implements RandomAccess
    {
        private final NodeList list;

        NodeListWrapper(final NodeList l) {
            this.list = l;
        }

        @Override
        public Node get(final int index) {
            return this.list.item(index);
        }

        @Override
        public int size() {
            return this.list.getLength();
        }
    }
}
