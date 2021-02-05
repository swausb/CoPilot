/*
 * Copyright notice
 * Copyright (c) Nils Körting-Eberhardt 2021
 * Created: 06.01.2021 @ 22:27:28
 *
 * All contents of this source code are protected by copyright. The copyright is owned by Nils Körting-Eberhardt, unless explicitly stated otherwise. All rights reserved.
 *
 * EChannel.java is part of the DVBL-Bot which is licensed under the Attribution-NonCommercial-ShareAlike 4.0 International (CC BY-NC-SA 4.0) license.
 */

package de.swausb.copilot.enums;

import net.dv8tion.jda.api.entities.TextChannel;
import de.swausb.copilot.Start;

public enum EChannel {

    WELCOME ("807231660445270026");

    String channelID;

    EChannel(String channelID) {
        this.channelID = channelID;
    }

    public TextChannel getChannel() {
        return Start.getInstance().getMSFS().getTextChannelById(channelID);
    }

    public String getChannelID() {
        return channelID;
    }
}