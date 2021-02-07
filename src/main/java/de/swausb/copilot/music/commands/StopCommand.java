package de.swausb.copilot.music.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import de.swausb.copilot.ICommand;
import de.swausb.copilot.Start;
import de.swausb.copilot.music.AudioLoadResult;
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
                if ((vc = gvs.getChannel()) != null) {
                    MusicController controller = Start.getInstance().playerManager.getController(vc.getGuild().getIdLong());
                    AudioManager manager = vc.getGuild().getAudioManager();
                    AudioPlayer player = controller.getPlayer();

                    player.stopTrack();
                    message.addReaction("U+1F44C").queue();
                }
            }
        }
    }
