package com.bdoemu.commons.model.enums;

/**
 * @ClassName EBanCrit
 * @Description 禁止标准类型
 * @Author JiangBangMing
 * @Date 2019/6/22 11:03
 * VERSION 1.0
 */
public enum EBanCriteriaType {
    //
    ACCOUNT_ID("account_id"),
    PLAYER_ID("player_id"),
    HWID("hwid"),
    IP("ip");


    private final String fieldName;


    EBanCriteriaType(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return this.fieldName;
    }
}
