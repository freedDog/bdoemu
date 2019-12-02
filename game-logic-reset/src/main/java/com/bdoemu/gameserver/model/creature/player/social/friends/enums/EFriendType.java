package com.bdoemu.gameserver.model.creature.player.social.friends.enums;

/**
 * @ClassName EFriendType
 * @Description  好友类型
 * @Author JiangBangMing
 * @Date 2019/7/10 14:06
 * VERSION 1.0
 */
public enum EFriendType {

    MyFriend(0),
    Party(1);

    private byte id;

    EFriendType(final int id) {
        this.id = (byte) id;
    }

    public byte getId() {
        return this.id;
    }

    public boolean isMyFriend() {
        return this == EFriendType.MyFriend;
    }

}
