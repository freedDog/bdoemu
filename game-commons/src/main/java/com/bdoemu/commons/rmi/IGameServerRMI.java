package com.bdoemu.commons.rmi;

import com.bdoemu.commons.rmi.model.GameAccountInfo;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @ClassName IGameServerRMI
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/21 20:38
 * VERSION 1.0
 */

public interface IGameServerRMI extends Remote {

    boolean testConnection() throws RemoteException;

    boolean isAccountOnServer(long paramLong) throws RemoteException;

    void kickByAccountId(long paramLong) throws RemoteException;

    GameAccountInfo getGameAccountInfo(long paramLong) throws RemoteException;

}
