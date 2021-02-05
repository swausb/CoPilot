package de.swausb.copilot.manager;

import de.swausb.copilot.Start;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

public class MessageManager {

    public void printJoinMessage (String channelID, Member member) {
        TextChannel textChannel = Start.getInstance().getJda().getTextChannelById(channelID);
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.green);
        builder.setTitle(":soccer:  Es ist jemand neues auf Mods MSFS!  :soccer:");
        builder.addField("User • 👤", "» " + member.getAsMention(), false);
        builder.addField("Hinweis • ❗", "» Viel Spaß auf unserem Server!", false);
        builder.setThumbnail(member.getUser().getAvatarUrl());
        builder.setFooter("CoPilot-Bot - Copyright © swausb 2021");
        assert textChannel != null;
        textChannel.sendMessage(builder.build()).queue(message -> message.addReaction("✈").queue());
    }

    public void printLeaveMessage (String channelID, User user) {
        TextChannel textChannel = Start.getInstance().getJda().getTextChannelById(channelID);
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.red);
        builder.setTitle("Auf Wiedersehen!");
        builder.addField("User • 👤", "» " + user.getAsMention(), false);
        builder.setFooter("CoPilot-Bot - Copyright © swausb 2021");
        assert textChannel != null;
        textChannel.sendMessage(builder.build()).queue(message -> message.addReaction("👋").queue());
    }

    public void printCommandNotFoundMessage (String channelID, User user) {
        Start.getInstance().getJda().getTextChannelById(channelID).sendMessage(new MessageBuilder("Dieses Kommando wurde nicht gefunden!").build()).queue();
    }
}