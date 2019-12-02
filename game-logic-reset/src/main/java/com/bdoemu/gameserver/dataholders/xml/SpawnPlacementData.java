package com.bdoemu.gameserver.dataholders.xml;

import com.bdoemu.commons.xml.XmlDataHolderRoot;
import com.bdoemu.commons.xml.factory.NodeForEach;
import com.bdoemu.commons.xml.models.XmlDataHolder;
import com.bdoemu.gameserver.model.creature.services.SpawnService;
import com.bdoemu.gameserver.model.creature.templates.SpawnPlacementT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @ClassName SpawnPlacementData
 * @Description  产卵位置数据
 * @Author JiangBangMing
 * @Date 2019/7/8 22:30
 * VERSION 1.0
 */

@XmlDataHolderRoot("SpawnPlacementList")
public class SpawnPlacementData extends XmlDataHolder {

    private static final Logger log;

    public static SpawnPlacementData getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void loadData(final NodeList nList) {
        new NodeForEach(nList.item(0).getChildNodes()) {
            @Override
            protected boolean read(final Node nNode) {
                if (!nNode.getNodeName().equalsIgnoreCase("SpawnPlacement")) {
                    return true;
                }
                final SpawnPlacementT template = new SpawnPlacementT(nNode, false);
                final long key = template.getKey();
                if (key != 0L) {
                    SpawnService.getInstance().addSpawnStatic(key, template);
                }
                else {
                    SpawnService.getInstance().addSpawn(template);
                }
                return true;
            }
        };
    }

    static {
        log = LoggerFactory.getLogger((Class)SpawnPlacementData.class);
    }

    private static class Holder
    {
        static final SpawnPlacementData INSTANCE;

        static {
            INSTANCE = new SpawnPlacementData();
        }
    }
}
