package com.bdoemu.login.service;

import com.bdoemu.commons.utils.Rnd;
import com.bdoemu.core.startup.StartupComponent;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CookieService
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/26 19:46
 * VERSION 1.0
 */

@StartupComponent("Service")
public class CookieService {
    private Map<Long, Integer> cookies;

    public CookieService() {
        this.cookies = new HashMap<Long, Integer>();
    }

    public int generateCookie(final long n) {
        final Integer n2 = Rnd.get(1,Integer.MAX_VALUE);
        this.cookies.put(n,n2);
        return n2;
    }

    public boolean updateCookie(final long n, final int n2) {
        return true;
    }

    public Integer getCookie(final long n) {
        return this.cookies.get(n);
    }

    public static CookieService getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        static final CookieService INSTANCE = new CookieService();
    }
}
