package de.swausb.copilot.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class EmbedMessage {

    private String title;
    private String author;
    private String description;
    private String imagename;
    private MessageEmbed.Field[] fields;

    public EmbedMessage(String title, String author, String description, String imagename, MessageEmbed.Field... fields) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.imagename = imagename;
        this.fields = fields;
    }

    public net.dv8tion.jda.api.EmbedBuilder raw() {
        net.dv8tion.jda.api.EmbedBuilder builder = new EmbedBuilder().setAuthor(author).setDescription(description).setTitle(title).setColor(Color.orange).setFooter("CoPilot-Bot - Copyright © swausb || realEntwickler");

        if (imagename != null)
            builder.setImage("https://github.com/swausb/CoPilot/tree/master/images" + imagename + ".jpg?raw=true");

        if (fields != null && fields.length > 0) {
            for (MessageEmbed.Field field : fields) {
                builder.addField(field);
            }
        }

        return builder;
    }

    public MessageEmbed build(){
        EmbedBuilder builder = new EmbedBuilder().setAuthor(author).setDescription(description).setTitle(title).setColor(Color.orange).setFooter("CoPilot-Bot - Copyright © swausb || realEntwickler");

        if (imagename != null)
            builder.setImage("https://github.com/swausb/CoPilot/tree/master/images" + imagename + ".jpg?raw=true");

        if (fields != null && fields.length > 0) {
            for (MessageEmbed.Field field : fields) {
                builder.addField(field);
            }
        }

        return builder.build();
    }
}
