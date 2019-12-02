package com.bdoemu.gameserver.model.misc.enums;

/**
 * @ClassName EDialogButtonType
 * @Description   对话框按钮类型
 * @Author JiangBangMing
 * @Date 2019/7/9 15:21
 * VERSION 1.0
 */
public enum EDialogButtonType {
    /**正常的*/
    Normal(0),
    /**知识*/
    Knowledge(1),
    /**功能*/
    Function(2),
    /**减少现场*/
    CutScene(3),
    /**交换*/
    Exchange(4),
    /**除了交换*/
    ExceptExchange(5),
    Unknown6(6),
    Unknown(99);

    private byte id;

    EDialogButtonType(final int id) {
        this.id = (byte) id;
    }

    public static EDialogButtonType valueOf(final int id) {
        for (final EDialogButtonType buttonType : values()) {
            if (buttonType.id == id) {
                return buttonType;
            }
        }
        throw new IllegalArgumentException("Invalid EDialogButtonType id: " + id);
    }
}
