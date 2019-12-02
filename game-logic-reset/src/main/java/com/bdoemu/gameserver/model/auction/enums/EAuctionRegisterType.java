package com.bdoemu.gameserver.model.auction.enums;

/**
 * @ClassName EAuctionRegisterType
 * @Description  拍卖注册类型
 * @Author JiangBangMing
 * @Date 2019/7/10 10:14
 * VERSION 1.0
 */
public enum EAuctionRegisterType {
    /**没有*/
    None,
    /**NPC工人*/
    NpcWorkerMarket,
    /**工会拍卖*/
    GuildAuctionMarket,
    /**仆人*/
    ServantMatingMarket,
    /**仆人*/
    ServantMarket;

    public static EAuctionRegisterType valueOf(final int reqType) {
        if (reqType < 0 || reqType > values().length - 1) {
            return null;
        }
        return values()[reqType];
    }

    public boolean isNone() {
        return this == EAuctionRegisterType.None;
    }

    public boolean isServantMatingMarket() {
        return this == EAuctionRegisterType.ServantMatingMarket;
    }

    public boolean isServantMarket() {
        return this == EAuctionRegisterType.ServantMarket;
    }

    public boolean isGuildAuction() {
        return this == EAuctionRegisterType.GuildAuctionMarket;
    }

}
