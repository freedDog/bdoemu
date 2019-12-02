package com.bdoemu.core.startup;

import com.bdoemu.commons.utils.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName StartModule
 * @Description 启动模块
 * @Author JiangBangMing
 * @Date 2019/6/21 20:11
 * VERSION 1.0
 */

public class StartModule <SL extends Enum<SL>>{

    private static final Logger logger= LoggerFactory.getLogger(StartModule.class);
    private final SL startLevel;
    private final Class<?> clazz;
    private Object instance;
    private final List<StartModule<SL>> dependency=new ArrayList<>();

    public StartModule(SL startLevel,Class<?> clazz){
        this.startLevel=startLevel;
        this.clazz=clazz;
    }

    public void init(){
        logger.info("==============================================startup module "+this.clazz.getSimpleName());
        if(this.instance!=null){
            return;
        }
        long startTime=System.currentTimeMillis();
        for(StartModule<SL> depend:this.dependency){
            depend.init();
        }
        this.instance= ClassUtils.singletonInstance(this.clazz);
        logger.info("==============================================startup end  module "+this.clazz.getSimpleName()+"  start time "+(System.currentTimeMillis()-startTime)+"(milliscond)");
    }

    /**
     * 添加依赖关系
     * @param module
     */
    public void addDependency(StartModule<SL> module) {
        this.dependency.add(module);
    }

    public SL getStartLevel(){
        return this.startLevel;
    }

    public Class<?> getClazz(){
        return this.clazz;
    }

    public Object getInstance(){
        return this.instance;
    }

}
