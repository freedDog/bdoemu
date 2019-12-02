package com.bdoemu.gameserver.model.creature.player.knowledge.templates;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName KnowledgeLearningT
 * @Description  知识的学习
 * @Author JiangBangMing
 * @Date 2019/7/10 11:37
 * VERSION 1.0
 */

public class KnowledgeLearningT {

    private int creatureId;
    private int selectRate;
    private int knowledgeIndex;

    public KnowledgeLearningT(final ResultSet rs) throws SQLException {
        this.creatureId = rs.getInt("Key");
        this.selectRate = rs.getInt("SelectRate");
        this.knowledgeIndex = rs.getInt("KnowledgeIndex");
    }

    public int getCreatureId() {
        return this.creatureId;
    }

    public int getKnowledgeIndex() {
        return this.knowledgeIndex;
    }

    public int getSelectRate() {
        return this.selectRate;
    }

}
