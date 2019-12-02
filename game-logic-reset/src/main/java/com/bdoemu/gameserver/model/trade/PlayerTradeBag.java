package com.bdoemu.gameserver.model.trade;

import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.items.Item;

/**
 * @ClassName PlayerTradeBag
 * @Description  玩家贸易背包
 * @Author JiangBangMing
 * @Date 2019/7/10 17:03
 * VERSION 1.0
 */

public class PlayerTradeBag {
    private Player player;
    private Item[] items;
    private boolean lock;
    private boolean assure;

    public PlayerTradeBag(final Player player) {
        this.items = new Item[12];
        this.lock = false;
        this.assure = false;
        this.player = player;
    }

    public Item[] getItems() {
        return this.items;
    }

    public Player getPlayer() {
        return this.player;
    }

    public boolean lock() {
        if (this.lock) {
            this.lock = false;
        } else {
            this.lock = true;
        }
        return this.lock;
    }

    public boolean assure() {
        this.assure = !this.assure;
        return this.assure;
    }

    public boolean isAssure() {
        return this.assure;
    }

    public boolean isLock() {
        return this.lock;
    }
}
