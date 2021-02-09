package de.swausb.copilot.manager;

import de.swausb.copilot.Start;
import net.dv8tion.jda.api.EmbedBuilder;
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

    public void printCommandNotFoundMessage (Member commandSender, TextChannel textChannel) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor("CoPilot");
        builder.setThumbnail("https://raw.githubusercontent.com/swausb/CoPilot/master/images/CoPilot.jpg");
        builder.setColor(Color.red);
        builder.setTitle("Fehler [ERROR 001]");
        builder.setDescription("Dieser Command wurde nicht gefunden!");
        builder.setFooter("CoPilot-Bot - Copyright © swausb");
        textChannel.sendMessage(builder.build()).queue(exitMessage -> exitMessage.addReaction("❌").queue());
    }
    public void printErrorPlayCommand (Member commandSender, TextChannel textChannel) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor("CoPilot - " + commandSender.getEffectiveName());
        builder.setThumbnail("https://raw.githubusercontent.com/swausb/CoPilot/master/images/CoPilot.jpg");
        builder.setColor(Color.red);
        builder.setTitle("Fehler [ERROR 002]");
        builder.setDescription("Bitte benutze .play (Songlink) wenn du Musik abspielen möchtest!");
        builder.setFooter("CoPilot-Bot - Copyright © swausb");
        textChannel.sendMessage(builder.build()).queue(exitMessage -> exitMessage.addReaction("❌").queue());
    }
    public void printErrorVoiceChannel (Member commandSender, TextChannel textChannel) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor("CoPilot - " + commandSender.getEffectiveName());
        builder.setThumbnail("https://raw.githubusercontent.com/swausb/CoPilot/master/images/CoPilot.jpg");
        builder.setColor(Color.red);
        builder.setTitle("Fehler [ERROR 003]");
        builder.setDescription("Huch, du bist wohl in keinem Voicechannel!");
        builder.setFooter("CoPilot-Bot - Copyright © swausb");
        textChannel.sendMessage(builder.build()).queue(exitMessage -> exitMessage.addReaction("❌").queue());
    }

    public void printErrorStopCommand (Member commandSender, TextChannel textChannel) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor("CoPilot - " + commandSender.getEffectiveName());
        builder.setThumbnail("https://raw.githubusercontent.com/swausb/CoPilot/master/images/CoPilot.jpg");
        builder.setColor(Color.red);
        builder.setTitle("Fehler [ERROR 004]");
        builder.setDescription("Aktuell spielt kein Song!");
        builder.setFooter("CoPilot-Bot - Copyright © swausb");
        textChannel.sendMessage(builder.build()).queue(exitMessage -> exitMessage.addReaction("❌").queue());
    }

    public void printHelpCommand(Member commandSender, TextChannel textChannel) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor("CoPilot - " + commandSender.getEffectiveName());
        builder.setTitle("Help Command");
        builder.setColor(Color.red);
        builder.setDescription("Hier gibt es eine Übersicht aller Bot-Befehle!");
        builder.addField("Error Commands", "[001] = Command nicht gefunden", true);
        builder.addField("","[002] = Command nicht vollständig",  false);
        builder.addField("", "[003] = Voicechannel Fehler", false);
        builder.setFooter("CoPilot-Bot - Copyright © swausb");
        textChannel.sendMessage(builder.build()).queue(exitMessage -> exitMessage.addReaction("U+2753").queue());
    }

    public void printCoPilotSong (Member commandSender, TextChannel textChannel) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor("Copilot - " +  commandSender.getEffectiveName());
        builder.setTitle("CoPilot Song");
        builder.setThumbnail("https://raw.githubusercontent.com/swausb/CoPilot/master/images/CoPilot.jpg");
        builder.setDescription("Viel Spaß mit dem besten Song aller Zeiten! <3");
        builder.setFooter("CoPilot-Bot - Copyright © swausb");
        textChannel.sendMessage(builder.build()).queue(exitMessage -> exitMessage.addReaction("U+1F60D").queue());
        textChannel.addReactionById(textChannel.getLatestMessageId(), "U+1F3B6");
    }
}

