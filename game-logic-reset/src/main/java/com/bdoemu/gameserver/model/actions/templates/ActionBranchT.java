package com.bdoemu.gameserver.model.actions.templates;

import com.bdoemu.commons.io.FileBinaryReader;
import com.bdoemu.commons.utils.HashUtil;

/**
 * @ClassName ActionBranchT
 * @Description  操作分支
 * @Author JiangBangMing
 * @Date 2019/7/11 11:01
 * VERSION 1.0
 */

public class ActionBranchT {

    private final String condition;
    private final float blendTime;
    private final String execute;
    private final String actionFileName;
    private final long actionHash;

    public ActionBranchT(final FileBinaryReader reader) {
        this.condition = reader.readS();
        this.blendTime = reader.readF();
        this.execute = reader.readS();
        this.actionHash = HashUtil.generateHashA(this.execute);
        this.actionFileName = reader.readS();
    }

    public String getExecute() {
        return this.execute;
    }

    public long getActionHash() {
        return this.actionHash;
    }

    public String getCondition() {
        return this.condition;
    }
}
