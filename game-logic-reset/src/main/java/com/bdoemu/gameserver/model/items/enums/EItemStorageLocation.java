package com.bdoemu.gameserver.model.items.enums;

/**
 * @ClassName EItemStorageLocation
 * @Description  道具存储位置
 * @Author JiangBangMing
 * @Date 2019/7/5 19:54
 * VERSION 1.0
 */
public enum EItemStorageLocation {
    /**库存*/
    Inventory(0),
    /**装备*/
    Equipments(1),
    /**仓库*/
    Warehouse(2),
    /**未使用*/
    Unused3(3),
    /**仆人库存*/
    ServantInventory(4),
    /**仆人装备*/
    ServantEquip(5),
    Unused6(6),
    Unused7(7),
    Unused8(8),
    Unused9(9),
    Unused10(10),
    Unused11(11),
    Unused12(12),
    Unused13(13),
    /**公会仓库*/
    GuildWarehouse(14),
    Unused15(15),
    Unused16(16),
    /**现金库存*/
    CashInventory(17),
    Unused18(18),
    None(19);

    private byte id;

    private EItemStorageLocation(final int id) {
        this.id = (byte) id;
    }

    public static EItemStorageLocation valueOf(final int id) {
        if (id >= 0 && id < values().length) {
            return values()[id];
        }
        return EItemStorageLocation.None;
    }

    public boolean isPlayerInventories() {
        switch (this) {
            case Inventory:
            case CashInventory: {
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public int getId() {
        return this.id;
    }

    public boolean isCashInventory() {
        return this == EItemStorageLocation.CashInventory;
    }

    public boolean isEquipments() {
        return this == EItemStorageLocation.Equipments;
    }

    public boolean isServantEquip() {
        return this == EItemStorageLocation.ServantEquip;
    }

    public boolean isInventory() {
        return this == EItemStorageLocation.Inventory;
    }

    public boolean isServantInventory() {
        return this == EItemStorageLocation.ServantInventory;
    }

    public boolean isWarehouse() {
        return this == EItemStorageLocation.Warehouse;
    }

    public boolean isGuildWarehouse() {
        return this == EItemStorageLocation.GuildWarehouse;
    }
}
