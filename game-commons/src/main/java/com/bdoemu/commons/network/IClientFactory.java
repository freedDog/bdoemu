package com.bdoemu.commons.network;

/**
 * @ClassName IClientFactory
 * @Description 客户端工厂
 * @Author JiangBangMing
 * @Date 2019/6/22 15:56
 * VERSION 1.0
 */

public interface IClientFactory<TClient extends Client<TClient>> {

    TClient createClient(Connection<TClient> paramConnection);
}
