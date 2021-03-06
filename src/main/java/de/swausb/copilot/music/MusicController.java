package de.swausb.copilot.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import de.swausb.copilot.Start;
import net.dv8tion.jda.api.entities.Guild;

public class MusicController {
    private Guild guild;
    private AudioPlayer player;

    public MusicController(Guild guild) {
        this.guild = guild;
        this.player = Start.getInstance().audioPlayerManager.createPlayer();

        this.guild.getAudioManager().setSendingHandler(new AudioPlayerSendHandler(player));
        this.player.setVolume(15);
    }

    public Guild getGuild() {
        return guild;
    }

    public AudioPlayer getPlayer() {
        return player;
    }
}
