package de.swausb.copilot.music.commands;

import de.swausb.copilot.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class PlayCommand extends ICommand {

    public PlayCommand(String name, String usage, String description, String... roles) {
        super(name, usage, description, roles);
    }

    @Override
    public void onCommand(Member commandSender, TextChannel textChannel, String[] args) {
        if (args.length == 2) {

        } else {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setAuthor("CoPilot");
            builder.setThumbnail("")
            builder.setColor(Color.red);
            builder.setDescription("Bitte benutze .play wenn du Musik abspielen möchtest!");
            builder.setFooter("CoPilot-Bot - Copyright © swausb");
            textChannel.sendMessage(builder.build()).queue();
        }
    }
}
