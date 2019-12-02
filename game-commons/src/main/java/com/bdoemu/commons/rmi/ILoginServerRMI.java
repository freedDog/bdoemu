package com.bdoemu.commons.rmi;

import com.bdoemu.commons.model.enums.EBanCriteriaType;
import com.bdoemu.commons.model.enums.EBanType;
import com.bdoemu.commons.rmi.model.BanInfo;
import com.bdoemu.commons.rmi.model.ChannelRegisterResult;
import com.bdoemu.commons.rmi.model.GameChannelInfo;
import com.bdoemu.commons.rmi.model.LoginAccountInfo;
import com.bdoemu.commons.rmi.model.Macros;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @ClassName ILoginServerRMI
 * @Description 登录服务接口
 * @Author JiangBangMing
 * @Date 2019/6/21 20:46
 * VERSION 1.0
 */

public interface ILoginServerRMI  extends Remote {

    boolean testConnection() throws RemoteException;

    ChannelRegisterResult registerChannel(IGameServerRMI paramIGameServerRMI, GameChannelInfo paramGameChannelInfo) throws RemoteException;

    void updateChannel(IGameServerRMI paramIGameServerRMI, GameChannelInfo paramGameChannelInfo) throws RemoteException;

    void updateCookie(long paramLong, int paramInt) throws RemoteException;

    LoginAccountInfo getAccountInfo(long paramLong, int paramInt) throws RemoteException;

    void updateCharacterSlots(long paramLong, int paramInt) throws RemoteException;

    void saveAccountData(long paramLong, String paramString1, String paramString2, Macros[] paramArrayOfMacros) throws RemoteException;

    boolean addCash(long paramLong1, long paramLong2) throws RemoteException;

    long getCash(long paramLong) throws RemoteException;

    boolean changeFamilyName(long paramLong, String paramString) throws RemoteException;

    String ban(int paramInt, EBanCriteriaType paramEBanCriteriaType, String paramString1, EBanType paramEBanType, String paramString2, String paramString3, long paramLong) throws RemoteException;

    String unBan(int paramInt, EBanCriteriaType paramEBanCriteriaType, String paramString, EBanType paramEBanType) throws RemoteException;

    long getBanEndTime(int paramInt, EBanCriteriaType paramEBanCriteriaType, String paramString, EBanType paramEBanType) throws RemoteException;

    List<BanInfo> getBans(int paramInt, EBanCriteriaType paramEBanCriteriaType, String paramString) throws RemoteException;
}
