package com.bdoemu.core.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName StartupInstance
 * @Description 启动实例
 * @Author JiangBangMing
 * @Date 2019/6/21 20:10
 * VERSION 1.0
 */

public class StartupInstance<SL extends Enum<SL>> {

    private static final Logger logger= LoggerFactory.getLogger(StartupInstance.class);

    private final HashMap<SL, List<StartModule<SL>>> startTable=new HashMap<>();

    public void put(SL level,StartModule<SL> module){
        List<StartModule<SL>> invokes=this.startTable.computeIfAbsent(level,k -> new ArrayList<>());
        invokes.add(module);
    }

    public void runLevel(SL level){
        List<StartModule<SL>> list=(List)this.startTable.get(level);
        if(list==null){
            return;
        }
        for(StartModule<SL> module:list){
            module.init();
        }
    }

    protected Collection<Map.Entry<SL,List<StartModule<SL>>>> getAll(){
        return this.startTable.entrySet();
    }
}
