/*
 * Copyright notice
 * Copyright (c) Nils Körting-Eberhardt 2021
 * Created: 06.01.2021 @ 22:40:50
 *
 * All contents of this source code are protected by copyright. The copyright is owned by Nils Körting-Eberhardt, unless explicitly stated otherwise. All rights reserved.
 *
 * GuildMemberLeaveListener.java is part of the DVBL-Bot which is licensed under the Attribution-NonCommercial-ShareAlike 4.0 International (CC BY-NC-SA 4.0) license.
 */

package de.swausb.copilot.listener;

import de.swausb.copilot.Start;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import de.swausb.copilot.enums.EChannel;

public class GuildMemberLeaveListener extends ListenerAdapter {

    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent event) {
        Member member = event.getMember();
        assert member != null;
        Start.getInstance().getMessageManager().printLeaveMessage(EChannel.WELCOME.getChannelID(), event.getUser());
    }
}