package com.bdoemu.login.service;

import com.bdoemu.commons.model.enums.EBanCriteriaType;
import com.bdoemu.commons.model.enums.EBanType;
import com.bdoemu.commons.model.enums.EServerBusyState;
import com.bdoemu.commons.rmi.IGameServerRMI;
import com.bdoemu.commons.rmi.ILoginServerRMI;
import com.bdoemu.commons.rmi.SocketFactory;
import com.bdoemu.commons.rmi.model.BanInfo;
import com.bdoemu.commons.rmi.model.ChannelRegisterResult;
import com.bdoemu.commons.rmi.model.GameChannelInfo;
import com.bdoemu.commons.rmi.model.LoginAccountInfo;
import com.bdoemu.commons.rmi.model.Macros;
import com.bdoemu.commons.thread.ThreadPool;
import com.bdoemu.core.configs.NetworkConfig;
import com.bdoemu.login.databaseCollections.AccountsDbCollection;
import com.bdoemu.login.dataholders.xml.GameserversData;
import com.bdoemu.login.models.db.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RMISocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @ClassName LoginServerRMI
 * @Description 登录服务RMI
 * @Author JiangBangMing
 * @Date 2019/6/26 13:55
 * VERSION 1.0
 */

public class LoginServerRMI extends UnicastRemoteObject implements ILoginServerRMI {
    private static final Logger log=LoggerFactory.getLogger(LoginServerRMI.class);
    private Map<Integer, Map<Integer, GameChannelInfo>> channels;

    public LoginServerRMI() throws RemoteException {
        this.channels = new ConcurrentHashMap<>();
        this.channels= GameserversData.getInstance().getChannels();
        try {
            RMISocketFactory.setSocketFactory(new SocketFactory());
            LocateRegistry.createRegistry(NetworkConfig.RMI_PORT,new SocketFactory(),new SocketFactory()).bind("bdo_login_server",this);
            LoginServerRMI.log.info("loginServerRMI bin  {}", NetworkConfig.RMI_PORT);
        }
        catch (Exception ex) {
            LoginServerRMI.log.error("", ex);
        }
        finally {
            ThreadPool.getInstance().scheduleGeneralAtFixedRate(new GameChannelsStatusWatcher(), 0L, 1L, TimeUnit.SECONDS);
        }
    }
    @Override
    public ChannelRegisterResult registerChannel(final IGameServerRMI gameServerRMI, final GameChannelInfo gameChannelInfo) throws RemoteException {
        if (this.channels.containsKey( gameChannelInfo.getServerId())) {
            final GameChannelInfo gameChannelInfo2 = this.channels.get(gameChannelInfo.getServerId()).get(gameChannelInfo.getChannelId());
            if (gameChannelInfo2 != null && gameChannelInfo2.getPort() == gameChannelInfo.getPort() &&  gameChannelInfo.getPassword().equals(NetworkConfig.RMI_PASSWORD)) {
                if (gameChannelInfo2.getServerBusyState() != EServerBusyState.INSPECTION) {
                    LoginServerRMI.log.warn(" registerChannel {} -- {}", gameChannelInfo.getServerId(), gameChannelInfo.getChannelId());
                    return ChannelRegisterResult.ALREADY_REGISTERED;
                }
                gameChannelInfo2.update(gameServerRMI, gameChannelInfo);
                LoginServerRMI.log.info("update  {}----{}",gameChannelInfo.getServerId(), gameChannelInfo.getChannelId());
                return ChannelRegisterResult.SUCCESS;
            }
        }
        LoginServerRMI.log.warn("unknown server {}-----{}",gameChannelInfo.getServerId(), gameChannelInfo.getChannelId());
        return ChannelRegisterResult.UNKNOWN_SERVER;
    }
    @Override
    public void updateChannel(final IGameServerRMI gameServerRMI, final GameChannelInfo gameChannelInfo) {
        if (this.channels.containsKey(gameChannelInfo.getServerId())) {
            final GameChannelInfo gameChannelInfo2 = this.channels.get(gameChannelInfo.getServerId()).get(gameChannelInfo.getChannelId());
            if (gameChannelInfo2 != null) {
                gameChannelInfo2.update(gameServerRMI, gameChannelInfo);
            }
        }
    }
    @Override
    public void updateCookie(final long n, final int n2) throws RemoteException {
    }

    @Override
    public LoginAccountInfo getAccountInfo(final long accountId, final int n) throws RemoteException {
        final Integer n2 = CookieService.getInstance().getCookie(accountId);
        if (n2 != null && n2.intValue() == n) {
            final Account account = AccountsDbCollection.getInstance().load(accountId,new Account[0]);
            if (account != null) {
                final LoginAccountInfo loginAccountInfo = new LoginAccountInfo();
                loginAccountInfo.setAccountId(accountId);
                loginAccountInfo.setCookie(n2);
                loginAccountInfo.setAccessLevel(account.getAccessLvl());
                loginAccountInfo.setCash(account.getCash());
                loginAccountInfo.setCharacterSlots(account.getCharacterSlots());
                loginAccountInfo.setFamily(account.getFamily());
                loginAccountInfo.setMacroses(account.getMacroses());
                loginAccountInfo.setUiData(account.getUiInfo());
                loginAccountInfo.setSaveGameOptions(account.getGameOption());
                return loginAccountInfo;
            }
        }
        return null;
    }
    @Override
    public void saveAccountData(final long n, final String s, final String s2, final Macros[] array) throws RemoteException {

    }

    @Override
    public long getCash(final long n) throws RemoteException {
        return AccountsDbCollection.getInstance().getCash(n);
    }

    @Override
    public boolean addCash(final long n, final long n2) throws RemoteException {

        if (n2 < 0L && AccountsDbCollection.getInstance().getCash(n)<n2) {
            return false;
        }
        AccountsDbCollection.getInstance().addCash(n,n2);
        return true;
    }

    @Override
    public void updateCharacterSlots(final long n, final int n2) throws RemoteException {
    }

    @Override
    public boolean changeFamilyName(final long n, final String s) throws RemoteException {
       return AccountsDbCollection.getInstance().updateFamily(n,s);
    }

    @Override
    public boolean testConnection() throws RemoteException {
        return true;
    }

    public boolean isAccountOnServer(final long n) {
        final Iterator<Map.Entry<Integer, Map<Integer, GameChannelInfo>>> iterator = this.channels.entrySet().iterator();
        while (iterator.hasNext()) {
            for (final GameChannelInfo gameChannelInfo : iterator.next().getValue().values()) {
                try {
                    if (gameChannelInfo.getConnection() != null && gameChannelInfo.getConnection().isAccountOnServer(n)) {
                        return true;
                    }
                    continue;
                }
                catch (RemoteException ex) {
                    LoginServerRMI.log.error("isAccountOnServer Error", ex );
                }
            }
        }
        return false;
    }

    public void kickPlayerByAccount(final Long n) {
        final Iterator<Map.Entry<Integer, Map<Integer, GameChannelInfo>>> iterator = this.channels.entrySet().iterator();
        while (iterator.hasNext()) {
            for (final GameChannelInfo gameChannelInfo : iterator.next().getValue().values()) {
                try {
                    if (gameChannelInfo.getConnection() == null) {
                        continue;
                    }
                    gameChannelInfo.getConnection().kickByAccountId(n);
                }
                catch (RemoteException ex) {
                    LoginServerRMI.log.error("kick Player by account :",ex);
                }
            }
        }
    }

    public List<GameChannelInfo> getChannels() {
        return this.channels.values().stream().flatMap(map -> map.values().stream()).collect(Collectors.toList());
    }
    @Override
    public String ban(final int n, final EBanCriteriaType eBanCriteriaType, final String s, final EBanType eBanType, final String s2, final String s3, final long n2) {
       return BanService.getInstance().ban(n,eBanCriteriaType,s,eBanType,s2,s3,n2);
    }

    @Override
    public String unBan(final int n, final EBanCriteriaType eBanCriteriaType, final String s, final EBanType eBanType) {
        return BanService.getInstance().unBan(n,eBanCriteriaType,s,eBanType);
    }

    @Override
    public long getBanEndTime(final int n, final EBanCriteriaType eBanCriteriaType, final String s, final EBanType eBanType) {
        return BanService.getInstance().getBanEndTime(n,eBanCriteriaType,s,eBanType);
    }
    @Override
    public List<BanInfo> getBans(final int n, final EBanCriteriaType eBanCriteriaType, final String s) {
        return BanService.getInstance().getBans(n,eBanCriteriaType,s).stream().map(ban -> new BanInfo(ban.getBanType(),ban.getBanEnd())).collect(Collectors.toList());
    }

    /**
     * 游戏频道状态监视器
     */
    class GameChannelsStatusWatcher implements Runnable{

        @Override
        public void run() {

        }
    }

}
