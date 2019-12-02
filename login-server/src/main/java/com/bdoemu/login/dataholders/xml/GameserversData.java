package com.bdoemu.login.dataholders.xml;

import com.bdoemu.commons.rmi.model.GameChannelInfo;
import com.bdoemu.commons.utils.ServerInfoUtils;
import com.bdoemu.commons.xml.XmlDataHolderRoot;
import com.bdoemu.commons.xml.factory.NodeForEach;
import com.bdoemu.commons.xml.models.XmlDataHolder;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName GameserversData
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/26 14:11
 * VERSION 1.0
 */

@XmlDataHolderRoot("gameservers")
public class GameserversData extends XmlDataHolder {
    private static final AtomicReference<Object> instance = new AtomicReference<Object>();;
    private Map<Integer, Map<Integer, GameChannelInfo>> channels;

    public GameserversData() {
        this.channels = new ConcurrentHashMap<Integer, Map<Integer, GameChannelInfo>>();
    }
    @Override
    public void loadData(NodeList nList) {
        ServerInfoUtils.printSection("GameserversData Loading");
        new NodeForEach(nList.item(0).getChildNodes()){
            @Override
            protected boolean read(Node nNode) {
                if(!"gameserver".equalsIgnoreCase(nNode.getNodeName())){
                    return true;
                }
                GameChannelInfo info=new GameChannelInfo(nNode);
                Map<Integer,GameChannelInfo> tempMap=channels.get(info.getServerId());
                if(tempMap==null){
                    tempMap=new HashMap<>();
                    channels.put(info.getServerId(),tempMap);
                }
                if(!tempMap.containsKey(info.getServerId())){
                    tempMap.put(info.getChannelId(),info);
                }
                    return true;
            }
        };
    }

    public Map<Integer, Map<Integer, GameChannelInfo>> getChannels() {
        return this.channels;
    }

    public static GameserversData getInstance() {
        return Holder.INSTANCE;
    }
    private static class Holder {
        static final GameserversData INSTANCE = new GameserversData();
    }
}
