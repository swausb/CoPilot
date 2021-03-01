package de.swausb.copilot.music.musiccommands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import de.swausb.copilot.utils.ICommand;
import de.swausb.copilot.Start;
import de.swausb.copilot.music.MusicController;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class StopCommand extends ICommand {

    public StopCommand(String name, String usage, String description, String... roles) {
        super(name, usage, description, roles);

    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        TextChannel txtChannel = event.getChannel();
        User user = event.getAuthor();
        Message message = event.getMessage();
    }

    @Override
    public void onCommand(Member commandSender, TextChannel textChannel, Message message, String[] args) {
            GuildVoiceState gvs;
            if ((gvs = commandSender.getVoiceState()) != null) {
                VoiceChannel vc;
                // Prüfen, ob der Bot im Channel ist
                if ((vc = gvs.getChannel()) != null) {
                    MusicController controller = Start.getInstance().playerManager.getController(vc.getGuild().getIdLong());
                    // Prüfen, ob der Bot aktuell Musik spielt
                    if (controller.getPlayer().getPlayingTrack() != null) {
                        AudioManager manager = vc.getGuild().getAudioManager();
                        AudioPlayer player = controller.getPlayer();

                        player.stopTrack();
                        message.addReaction("U+23F8").queue();
                    } else {
                        Start.getInstance().getMessageManager().printErrorStopCommand(commandSender, textChannel);
                    }
                } else {
                    Start.getInstance().getMessageManager().printErrorVoiceChannel(commandSender, textChannel);
                }
            } else {
                Start.getInstance().getMessageManager().printErrorVoiceChannel(commandSender, textChannel);
            }
        }
    }
