package com.bdoemu.core.startup;

import com.bdoemu.commons.utils.ServerInfoUtils;
import org.atteo.classindex.ClassIndex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName  StartupManager
 * @Description 启动管理器
 * @Author JiangBangMing
 * @Date 2019/6/21 20:00
 * VERSION 1.0
 */

public class StartupManager {
    private static final Logger   log = LoggerFactory.getLogger(StartupManager.class);
    private static final AtomicReference<Object> instance = new AtomicReference<Object>();

    public <SL extends Enum<SL>> void startup(final Class<SL> startLevel) {
        final StartupInstance<SL> startup = new StartupInstance<>();
        /**获所有的启动项*/
        for (Class<?> clazz : ClassIndex.getAnnotated(StartupComponent.class)) {
            final StartupComponent startupAnnotation = clazz.getAnnotation(StartupComponent.class);
            try {
                final SL key = Enum.valueOf(startLevel, startupAnnotation.value());
                final StartModule<SL> module = new StartModule<>(key, clazz);
                startup.put(key, module);
            }
            catch (Exception e) {
                StartupManager.log.error("Error while initializing StartupManager  "+startupAnnotation.value()+"  clazz "+clazz.getSimpleName() , (Throwable)e);
            }
        }
        /**解析所有模块的依赖关系*/
        for (final Map.Entry<SL, List<StartModule<SL>>> entry : startup.getAll()) {
            final List<StartModule<SL>> invalidModules = new ArrayList<>();
            final List<StartModule<SL>> modules = entry.getValue();
            for (final StartModule<SL> module2 : modules) {
                final Class<?> clazz2 = module2.getClazz();
                final Class<?>[] dependency2= clazz2.getAnnotation(StartupComponent.class).dependency();
                for (final Class<?> dep : dependency2) {
                    final Optional<StartModule<SL>> dependencyModule = modules.stream().filter(m -> m.getClazz().getCanonicalName().equals(dep.getCanonicalName())).findAny();
                    if (dependencyModule.isPresent()) {
                        module2.addDependency(dependencyModule.get());
                    }
                    else {
                        invalidModules.add(module2);
                        StartupManager.log.warn("Not found dependency ({}) for {} on {} start level.", new Object[] { dep.getCanonicalName(), clazz2.getCanonicalName(), module2.getStartLevel().name() });
                    }
                }
            }
            modules.removeAll(invalidModules);
        }
        for (final SL startupLevel : startLevel.getEnumConstants()) {
            ServerInfoUtils.printSection(startupLevel.name());
            ((IStartupLevel)startupLevel).invokeDepends();
            startup.runLevel(startupLevel);
        }
    }

    public static StartupManager getInstance() {
        Object value = StartupManager.instance.get();
        if (value == null) {
            synchronized (StartupManager.instance) {
                value = StartupManager.instance.get();
                if (value == null) {
                    final StartupManager actualValue = new StartupManager();
                    value = ((actualValue == null) ? StartupManager.instance : actualValue);
                    StartupManager.instance.set(value);
                }
            }
        }
        return (StartupManager)((value == StartupManager.instance) ? null : value);
    }

}
