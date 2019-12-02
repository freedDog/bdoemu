package com.bdoemu.gameserver.model.team.guild.events;

import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.team.guild.Guild;
import com.bdoemu.gameserver.model.team.guild.GuildMember;
import com.bdoemu.gameserver.model.team.guild.services.GuildService;
import com.bdoemu.gameserver.service.GameTimeService;

/**
 * @ClassName LogoutGuildEvent
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/10 17:04
 * VERSION 1.0
 */

public class LogoutGuildEvent implements IGuildEvent {

    private Guild guild;
    private Player player;
    private GuildMember member;

    public LogoutGuildEvent(final Guild guild, final Player player) {
        this.guild = guild;
        this.player = player;
    }

    @Override
    public void onEvent() {
//        this.player.sendBroadcastPacket(new SMSetCharacterTeamAndGuild(this.player));
        this.guild.removeOnlineMember(this.player.getAccountId());
        this.member.setLastLogoutDate(GameTimeService.getServerTimeInMillis());
//        this.guild.sendBroadcastPacket(new SMLogoutGuild(this.member, this.guild.getObjectId()));
        this.member.updateLogoutInfo();
        if (this.guild != null && this.guild.getGuildQuest() != null)
            this.guild.getGuildQuest().deregisterObserverPlayer(this.player);
    }

    @Override
    public boolean canAct() {
        this.member = this.guild.getMember(this.player.getAccountId());
        return GuildService.getInstance().containsGuild(this.guild) && this.member != null && this.player.getGuild() == this.guild;
    }
}
