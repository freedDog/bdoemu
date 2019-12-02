package com.bdoemu.gameserver.model.conditions.accept;

import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.team.guild.guildquests.GuildQuest;

/**
 * @ClassName ProgressGuildQuestACondition
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/12 16:03
 * VERSION 1.0
 */

public class ProgressGuildQuestACondition implements IAcceptConditionHandler{

    private boolean hasExclamation;
    private int guildQuestNo;

    @Override
    public void load(final String conditionValue, final String operator, final String operatorValue, final boolean hasExclamation) {
        this.guildQuestNo = Integer.parseInt(conditionValue.trim());
        this.hasExclamation = hasExclamation;
    }

    @Override
    public boolean checkCondition(final Player player) {
        final GuildQuest guildQuest = player.getGuild().getGuildQuest();
        return this.hasExclamation != (guildQuest != null && guildQuest.getQuestId() == this.guildQuestNo);
    }
}
