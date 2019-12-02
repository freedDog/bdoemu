package com.bdoemu.gameserver.dataholders.xml;

import com.bdoemu.commons.utils.ServerInfoUtils;
import com.bdoemu.commons.xml.XmlDataHolderRoot;
import com.bdoemu.commons.xml.factory.NodeForEach;
import com.bdoemu.commons.xml.models.XmlDataHolder;
import com.bdoemu.gameserver.model.creature.player.exploration.templates.ExplorationTemplate;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Collection;
import java.util.HashMap;

/**
 * @ClassName ExploreData
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 19:49
 * VERSION 1.0
 */

@XmlDataHolderRoot("realExploreData")
public class ExploreData extends XmlDataHolder {
    private static final HashMap<Integer, ExplorationTemplate> explorations = new HashMap<>();

    public static ExploreData getInstance() {
        return Holder.INSTANCE;
    }
    @Override
    public void loadData(NodeList nList) {
        ServerInfoUtils.printSection("ExploreData Loading");
        new NodeForEach(nList.item(0).getChildNodes()) {
            @Override
            protected boolean read(Node nNode) {
                if (!nNode.getNodeName().equalsIgnoreCase("WaypointList")) {
                    return true;
                }
                new NodeForEach(nNode.getChildNodes()) {
                    @Override
                    protected boolean read(Node nNode) {
                        if (!nNode.getNodeName().equalsIgnoreCase("Waypoint")) {
                            return true;
                        }
                        ExplorationTemplate template = new ExplorationTemplate(nNode);
                        explorations.put(Integer.valueOf(template.getExplorationId()), template);
                        return true;
                    }
                };
                return true;
            }
        };
    }

    public Collection<ExplorationTemplate> getExplorations() {
        return ExploreData.explorations.values();
    }

    public ExplorationTemplate getTemplate(final int explorationId) {
        return ExploreData.explorations.get(explorationId);
    }

    private static class Holder {
        static final ExploreData INSTANCE = new ExploreData();
    }
}
