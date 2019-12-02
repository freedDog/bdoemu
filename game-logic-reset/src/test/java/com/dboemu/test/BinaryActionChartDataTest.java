package com.dboemu.test;

import com.bdoemu.commons.io.FileBinaryReader;
import com.bdoemu.gameserver.dataholders.binary.BinaryActionChartData;
import com.bdoemu.gameserver.model.actions.templates.ActionChartActionT;
import com.bdoemu.gameserver.model.actions.templates.ActionChartPackageMapT;
import com.bdoemu.gameserver.model.actions.templates.ActionScriptT;
import org.omg.PortableInterceptor.LOCATION_FORWARD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName BinaryActionChartDataTest
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/16 20:39
 * VERSION 1.0
 */

public class BinaryActionChartDataTest {

    public static void main(String[] args){
        BinaryActionChartDataTest.getInstance().load();
    }

    private static final Logger log = LoggerFactory.getLogger(BinaryActionChartDataTest.class);
    private final Map<String, ActionScriptT> actionScripts;
    private BinaryActionChartDataTest() {
        this.actionScripts = new HashMap<>();
        this.load();
    }

    public static BinaryActionChartDataTest getInstance() {
        return BinaryActionChartDataTest.Holder.INSTANCE;
    }

    private void load() {
        try (final FileBinaryReader reader = new FileBinaryReader("./data/static_data/binary/binaryactionchart.bin")) {
            for (int actionScriptCount = reader.readD(), actionScriptIndex = 0; actionScriptIndex < actionScriptCount; ++actionScriptIndex) {
                final ActionScriptT actionScript = new ActionScriptT(reader);
                this.actionScripts.put(actionScript.getName(), actionScript);
            }
            BinaryActionChartDataTest.log.info("Loaded {} ActionScript packages.", this.actionScripts.size());
        } catch (Exception e) {
            BinaryActionChartDataTest.log.error("Error while reading BinaryActionChart", e);
        }
        for(ActionScriptT actionScriptT:this.actionScripts.values()){
            log.info("ActionScriptT name: "+actionScriptT.getName());
            for(ActionChartPackageMapT actionChartPackageMapT:actionScriptT.getActionChart().getActionPackagesMap().values()){
                log.info("=============== packageName:"+actionChartPackageMapT.getPackageName());
                Iterator<Map.Entry<Integer,Map<Long,Integer>>> iterator= actionChartPackageMapT.getAiParams().entrySet().iterator();
                while(iterator.hasNext()){
                    Map.Entry<Integer,Map<Long,Integer>> entry=iterator.next();
                    log.info("=============== fromIndex:"+entry.getValue());
                    Iterator<Map.Entry<Long,Integer>> iterator1=entry.getValue().entrySet().iterator();
                    while(iterator1.hasNext()){
                        Map.Entry<Long,Integer> entry1=iterator1.next();
                        log.info("========== lower case: "+entry1.getKey()+"  value:"+entry1.getValue());
                    }
                }
                Iterator<Map.Entry<Long, ActionChartActionT>> iterator1=actionChartPackageMapT.getActions().entrySet().iterator();
                while (iterator1.hasNext()){
                    Map.Entry<Long,ActionChartActionT> entry=iterator1.next();
                    log.info("================ actionHash:"+entry.getKey()+"  value:"+entry.getValue().getActionName()+"  skillId:"+entry.getValue().getSkillId());
                }
            }
        }
    }

    public ActionScriptT getActionScript(final String actionScriptName) {
        return this.actionScripts.get(actionScriptName);
    }

    private static class Holder {
        static final BinaryActionChartDataTest INSTANCE = new BinaryActionChartDataTest();
    }
}
