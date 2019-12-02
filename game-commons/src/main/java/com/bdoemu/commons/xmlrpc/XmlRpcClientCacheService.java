package com.bdoemu.commons.xmlrpc;

import com.bdoemu.commons.thread.ThreadPool;
import com.bdoemu.commons.xmlrpc.client.XmlRpcCacheEntry;
import com.bdoemu.core.startup.StartupComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName XmlRpcClientCacheService
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/2 11:55
 * VERSION 1.0
 */

@StartupComponent("Service")
public class XmlRpcClientCacheService implements Runnable{

    private static final Logger log;
    private static final AtomicReference<Object> instance;
    private Map<String, Map<Integer, XmlRpcCacheEntry>> cache;
    private static final int CACHE_TIME = 5000;

    public XmlRpcClientCacheService() {
        this.cache = new ConcurrentHashMap<String, Map<Integer, XmlRpcCacheEntry>>();
        ThreadPool.getInstance().scheduleAiAtFixedRate(this, 1L, 1L, TimeUnit.MINUTES);
    }

    @Override
    public void run() {
        int cacheEntriesRemoved = 0;
        for (final Map.Entry<String, Map<Integer, XmlRpcCacheEntry>> entry : this.cache.entrySet()) {
            for (final Map.Entry<Integer, XmlRpcCacheEntry> cacheEntry : entry.getValue().entrySet()) {
                if (cacheEntry.getValue().getValidUntilTimeStamp() < System.currentTimeMillis()) {
                    this.cache.get(entry.getKey()).remove(cacheEntry.getKey());
                    ++cacheEntriesRemoved;
                }
            }
        }
        if (cacheEntriesRemoved > 0) {
            XmlRpcClientCacheService.log.info("Removed {} expired XML-RPC cache entries.", (Object)cacheEntriesRemoved);
        }
    }

    public Object getCache(final String methodName, final Object[] methodArgs) {
        if (this.cache.containsKey(methodName)) {
            final int paramHash = Arrays.hashCode(methodArgs);
            final Map<Integer, XmlRpcCacheEntry> methodCache = this.cache.get(methodName);
            if (methodCache.containsKey(paramHash)) {
                final XmlRpcCacheEntry resultEntry = methodCache.get(paramHash);
                final long currentTime = System.currentTimeMillis();
                if (resultEntry.getValidUntilTimeStamp() > currentTime) {
                    return resultEntry.getResult();
                }
                methodCache.remove(paramHash);
            }
        }
        return null;
    }

    public void addCache(final String methodName, final Object[] methodArgs, final Object result) {
        final XmlRpcCacheEntry xmlRpcCacheEntry = new XmlRpcCacheEntry();
        xmlRpcCacheEntry.setResult(result);
        xmlRpcCacheEntry.setValidUntilTimeStamp(System.currentTimeMillis() + 5000L);
        this.cache.computeIfAbsent(methodName, k -> new ConcurrentHashMap()).put(Arrays.hashCode(methodArgs), xmlRpcCacheEntry);
    }

    public static XmlRpcClientCacheService getInstance() {
        Object value = XmlRpcClientCacheService.instance.get();
        if (value == null) {
            synchronized (XmlRpcClientCacheService.instance) {
                value = XmlRpcClientCacheService.instance.get();
                if (value == null) {
                    final XmlRpcClientCacheService actualValue = new XmlRpcClientCacheService();
                    value = ((actualValue == null) ? XmlRpcClientCacheService.instance : actualValue);
                    XmlRpcClientCacheService.instance.set(value);
                }
            }
        }
        return (XmlRpcClientCacheService)((value == XmlRpcClientCacheService.instance) ? null : value);
    }

    static {
        log = LoggerFactory.getLogger((Class)XmlRpcClientCacheService.class);
        instance = new AtomicReference<Object>();
    }
}
