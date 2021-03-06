package de.swausb.copilot.listener;

import de.swausb.copilot.utils.ICommand;
import de.swausb.copilot.Start;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class GuildMessageReceivedListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        TextChannel txtChannel = event.getChannel();
        User user = event.getAuthor();
        Message message = event.getMessage();

        /**
         * Überprüfen, ob die Nachricht mit einem Punkt beginnt ==> Command
         */

        if (message.getContentRaw().startsWith(".")) {

            //Bots sollen unsere Kommandos nicht ausführen! Böse! aus!!!
            if (user.isBot())
                return;
            //Wir zerteilen die Nachricht in mehrere Stücke und teilen sie immer dann, wenn ein Leerzeichen kommt
            String[] arguments = message.getContentDisplay().split(" ");

            /*
            1. Wir holen uns alle registrierten Kommandos aus unserem Command Manager
            2. Wir filtern sie danach, ob der aktuelle Kommand in der Liste den Namen hat
                -> Hierbei ignorieren wir das erste Zeichen des 1. Werts aus unserem StringArray arguments (siehe oben), damit wir wissen, was der User ohne den Punkt angegeben hat
            3. Wir holen uns das erste, was dem Filter entspricht
            4. Wenn der Filter auf keinen Kommand zutrifft, lassen wir uns null zurückgeben

             */
            ICommand iCommand = Start.getInstance().getCommandManager().getRegisteredCommands().stream().filter(filter -> filter.getName().equalsIgnoreCase(arguments[0].substring(1))).findFirst().orElse(null);

            //Wir überprüfen, ob iCommand einen Wert hat oder nicht
            if (iCommand != null) {

                //Wir rufen die Methode onCommand von ICommand.java auf, die quasi dann ausgeführt wird, wenn ein User das Kommando im Textkanal eingegeben hat

                if (iCommand.getRoles().length > 0) {
                    iCommand.onCommand(event.getMember(), txtChannel, message, arguments);
                } else {
                    iCommand.onCommand(event.getMember(), txtChannel, message, arguments);
                }
            } else {
                Start.getInstance().getMessageManager().printCommandNotFoundMessage(event.getMember(), txtChannel);
            }
        }
    }
}
