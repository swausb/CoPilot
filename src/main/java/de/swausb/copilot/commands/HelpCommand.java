package de.swausb.copilot.commands;

import de.swausb.copilot.ICommand;
import de.swausb.copilot.Start;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class HelpCommand extends ICommand {
    public HelpCommand(String name, String usage, String description, String... roles) {
        super(name, usage, description, roles);
    }

    @Override
    public void onCommand(Member commandSender, TextChannel textChannel, Message message, String[] args) {
        Start.getInstance().getMessageManager().printHelpCommand(commandSender, textChannel);
    }
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        TextChannel txtChannel = event.getChannel();
        User user = event.getAuthor();
        Message message = event.getMessage();
    }
}
