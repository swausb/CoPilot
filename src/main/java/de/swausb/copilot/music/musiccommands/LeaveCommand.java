package de.swausb.copilot.music.musiccommands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import de.swausb.copilot.ICommand;
import de.swausb.copilot.Start;
import de.swausb.copilot.music.MusicController;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.managers.AudioManager;

public class LeaveCommand extends ICommand {
    public LeaveCommand(String name, String usage, String description, String... roles) {
        super(name, usage, description, roles);
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

                    if(controller.getGuild().getAudioManager().isConnected()) {
                        manager.closeAudioConnection();
                        message.addReaction("U+1F44B").queue();
                    } else {
                        Start.getInstance().getMessageManager().printBotErrorVoiceChannel(commandSender, textChannel);
                    }
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
