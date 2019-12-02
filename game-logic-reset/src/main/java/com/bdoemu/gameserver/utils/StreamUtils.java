package com.bdoemu.gameserver.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @ClassName StreamUtils
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 11:43
 * VERSION 1.0
 */

public class StreamUtils {

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
