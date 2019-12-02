package com.bdoemu.login.dataholders.xml;

import com.bdoemu.commons.utils.ServerInfoUtils;
import com.bdoemu.commons.xml.XmlDataHolderRoot;
import com.bdoemu.commons.xml.factory.NodeForEach;
import com.bdoemu.commons.xml.models.XmlDataHolder;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName FirewallData
 * @Description 防火墙数据
 * @Author JiangBangMing
 * @Date 2019/6/26 14:14
 * VERSION 1.0
 */

@XmlDataHolderRoot("firewall")
public class FirewallData extends XmlDataHolder {

    private static final Logger log = LoggerFactory.getLogger(FirewallData.class);;
    private static final AtomicReference<FirewallData> instance = new AtomicReference<FirewallData>();

    private List<String> blockedIps;

    private FirewallData() {
        this.blockedIps = new ArrayList<String>();
        final FileAlterationObserver fileAlterationObserver = new FileAlterationObserver("path");
        final FileAlterationMonitor fileAlterationMonitor = new FileAlterationMonitor(5000L);
        fileAlterationObserver.addListener(new FirewallFileChanged());
        fileAlterationMonitor.addObserver(fileAlterationObserver);
        try {
            fileAlterationMonitor.start();
        }
        catch (Exception ex) {
            FirewallData.log.error("", ex);
        }
    }

    @Override
    public void loadData(NodeList nList) {
        ServerInfoUtils.printSection("FirewallData Loading");
        new NodeForEach(nList.item(0).getChildNodes()){
            @Override
            protected boolean read(Node nNode) {
                if(!"block".equalsIgnoreCase(nNode.getNodeName())){
                    return true;
                }
                NamedNodeMap attributes=nNode.getAttributes();
                Node result=attributes.getNamedItem("ip");
                if(result!=null){
                    blockedIps.add(result.getNodeValue());
                }

                return true;
            }
        };
    }

    public static FirewallData getInstance() {
        return Holder.INSTANCE;
    }

    public List<String> getBlockedIps() {
        return this.blockedIps;
    }
    private static class Holder {
        static final FirewallData INSTANCE = new FirewallData();
    }

    class FirewallFileChanged extends FileAlterationListenerAdaptor{

        @Override
        public void onFileChange(File file) {
            super.onFileChange(file);
        }
    }
}
