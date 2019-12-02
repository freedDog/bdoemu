package com.bdoemu.gameserver.model.ai.deprecated;

/**
 * @ClassName EAITurnMethod
 * @Description  ai 转向方法
 * @Author JiangBangMing
 * @Date 2019/7/6 15:48
 * VERSION 1.0
 */
public enum EAITurnMethod {
    /**左*/
    Left,
    /**右*/
    Right,
    ToParent,
    /**朝目标*/
    ToTarget,
    Relative,
    Formation,
    Absolute,
    ToPathCurve,
    ToSettedPosition;
}
