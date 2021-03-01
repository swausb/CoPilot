package de.swausb.copilot.music.musiccommands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import de.swausb.copilot.utils.ICommand;
import de.swausb.copilot.Start;
import de.swausb.copilot.music.AudioLoadResult;
import de.swausb.copilot.music.MusicController;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.managers.AudioManager;


public class CopilotCommand extends ICommand {

    public CopilotCommand(String name, String usage, String description, String... roles) {
        super(name, usage, description, roles);

    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        TextChannel txtChannel = event.getChannel();
        User user = event.getAuthor();
        Message message = event.getMessage();
    }

    @Override
    public void onCommand(Member commandSender, TextChannel textChannel, Message message, String[] args) {
        if (args.length == 1) {
            GuildVoiceState gvs;
            if ((gvs = commandSender.getVoiceState()) != null) {
                VoiceChannel vc;
                if ((vc = gvs.getChannel()) != null) {
                    MusicController controller = Start.getInstance().playerManager.getController(vc.getGuild().getIdLong());
                    AudioPlayer player = controller.getPlayer();
                    AudioPlayerManager apm = Start.getInstance().audioPlayerManager;
                    AudioManager manager = vc.getGuild().getAudioManager();
                    manager.openAudioConnection(vc);
                    Start.getInstance().getMessageManager().printCoPilotSong(commandSender, textChannel);
                    apm.loadItem("https://www.youtube.com/watch?v=gr9J3BLxgeo", new AudioLoadResult(controller, "https://www.youtube.com/watch?v=gr9J3BLxgeo"));
                } else {
                    Start.getInstance().getMessageManager().printErrorVoiceChannel(commandSender, textChannel);
                }
            } else {
                Start.getInstance().getMessageManager().printErrorVoiceChannel(commandSender, textChannel);
            }
        } else {
            Start.getInstance().getMessageManager().printErrorStopCommand(commandSender, textChannel);
        }
    }
}
