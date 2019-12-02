package com.bdoemu.login.service;

import com.bdoemu.commons.model.enums.EBanCriteriaType;
import com.bdoemu.commons.model.enums.EBanType;
import com.bdoemu.commons.thread.ThreadPool;
import com.bdoemu.core.startup.StartupComponent;
import com.bdoemu.login.databaseCollections.BansDbCollection;
import com.bdoemu.login.models.db.Ban;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName BanService
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/26 19:45
 * VERSION 1.0
 */

@StartupComponent("Service")
public class BanService {
    private static final List<Ban> EMPTY_BAN_LIST=new ArrayList<>();

    private BanService() {
        ThreadPool.getInstance().scheduleGeneralAtFixedRate(new BanCleanTask(), 0L, 10L, TimeUnit.MINUTES);
    }

    public String ban(final int n, final EBanCriteriaType eBanCriteriaType, final String s, final EBanType eBanType, final String s2, final String s3, final long n2) {
        return "";
    }

    public String unBan(final int n, final EBanCriteriaType eBanCriteriaType, final String s, final EBanType eBanType) {
        return "";
    }

    public long getBanEndTime(final int n, final EBanCriteriaType eBanCriteriaType, final String s, final EBanType eBanType) {
        return 0L;
    }

    public boolean isBanned(final int n, final EBanCriteriaType eBanCriteriaType, final String s, final EBanType eBanType) {
        return false;
    }

    public List<Ban> getBans(final int n, final EBanCriteriaType eBanCriteriaType, final String s) {
        return BanService.EMPTY_BAN_LIST;
    }

    public static BanService getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        static final BanService INSTANCE = new BanService();
    }

    class BanCleanTask implements Runnable{

        @Override
        public void run() {

        }
    }
}
