package com.bdoemu.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName ClassUtils
 * @Description class 工具类
 * @Author JiangBangMing
 * @Date 2019/6/21 20:16
 * VERSION 1.0
 */

public class ClassUtils {
    private static final Logger logger= LoggerFactory.getLogger(ClassUtils.class);

    /**
     * 创建单一实例
     * @param clazz
     * @return
     */
    public static Object singletonInstance(Class<?> clazz){
        try{
            Method method = clazz.getDeclaredMethod("getInstance", new Class[0]);
            return method.invoke(null, new Object[0]);
        }catch (Exception e){
            logger.error("Error while calling singleton instance of " + clazz.getSimpleName(), e);
            return null;
        }
    }

    /**
     * 获取带注释的方法
     * @param type
     * @param annotation
     * @return
     */
    public static List<Method> getMethodsAnnotatedWith(Class<?> type, Class<? extends Annotation> annotation) {
        List<Method> methods = new ArrayList<Method>();
        Class<?> klass = type;
        while (klass != Object.class) {
            List<Method> allMethods = new ArrayList<Method>(Arrays.asList(klass.getDeclaredMethods()));
            methods.addAll((Collection)allMethods.stream().filter(method -> method.isAnnotationPresent(annotation)).collect(Collectors.toList()));
            klass = klass.getSuperclass();
        }
        return methods;
    }
}
