package com.bdoemu.gameserver.model.actions.enums;

/**
 * @ClassName EAttachTerrainType
 * @Description  附加地形类型
 * @Author JiangBangMing
 * @Date 2019/7/11 11:22
 * VERSION 1.0
 */
public enum EAttachTerrainType {
    /**地面*/
    Ground,
    /**空中*/
    Air,
    /**在水面上*/
    OnWater,
    /**在水里*/
    UnderWater,
    /**论水非动力*/
    OnWaterNonDynamic,
    /**向空中*/
    ToAir;

    public boolean isUnderWater() {
        return this == EAttachTerrainType.UnderWater;
    }
}
