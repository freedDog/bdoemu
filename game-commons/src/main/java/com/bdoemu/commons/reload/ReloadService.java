package com.bdoemu.commons.reload;

import com.bdoemu.commons.utils.ClassUtils;
import com.bdoemu.core.startup.StartupComponent;
import org.atteo.classindex.ClassIndex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @ClassName ReloadService
 * @Description 重载服务
 * @Author JiangBangMing
 * @Date 2019/6/24 20:20
 * VERSION 1.0
 */

@StartupComponent("Service")
public class ReloadService {
    private static final Logger log= LoggerFactory.getLogger((Class)ReloadService.class);
    private static final AtomicReference<Object> instance = new AtomicReference<Object>();
    private List<Class<?>> classes;
    private TreeMap<String, TreeMap<String, Class<?>>> classTree;

    private ReloadService() {
        this.classes = new ArrayList<>();
        this.classTree = new TreeMap<>();
        ClassIndex.getAnnotated(Reloadable.class).forEach(item -> {
            Reloadable annotation = item.getAnnotation(Reloadable.class);
            if (annotation != null) {
             String  name = annotation.name();
             String  group = annotation.group();
                this.classTree.computeIfAbsent(group, m -> new TreeMap()).putIfAbsent(name, item);
            }
            return;
        });
        ReloadService.log.info("Loaded {} reloadable instances.", (Object)this.classes.size());
    }

    private void callReload(final Class<?> clazz) {
        final IReloadable object = (IReloadable) ClassUtils.singletonInstance(clazz);
        if (object != null) {
            object.reload();
        }
    }

    public int reloadByName(final String name) {
        int count = 0;
        Collection<Class<?>> classes = this.classTree.values().stream().filter(item -> item.containsKey(name)).flatMap(
                item -> item.values().stream()).collect(Collectors.toList());
        if (!classes.isEmpty()) {
            for (final Class<?> clazz : classes) {
                this.callReload(clazz);
                ++count;
            }
        }
        return count;
    }

    public int reloadByGroup(final String group) {
        int count = 0;
        if (this.classTree.containsKey(group)) {
            final Collection<Class<?>> classes = this.classTree.get(group).values();
            for (final Class<?> clazz : classes) {
                this.callReload(clazz);
                ++count;
            }
        }
        return count;
    }

    public static ReloadService getInstance() {
        Object value = ReloadService.instance.get();
        if (value == null) {
            synchronized (ReloadService.instance) {
                value = ReloadService.instance.get();
                if (value == null) {
                    final ReloadService actualValue = new ReloadService();
                    value = ((actualValue == null) ? ReloadService.instance : actualValue);
                    ReloadService.instance.set(value);
                }
            }
        }
        return (ReloadService)((value == ReloadService.instance) ? null : value);
    }


}
