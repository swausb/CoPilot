package de.swausb.copilot;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.security.auth.login.LoginException;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import de.swausb.copilot.listener.GuildMemberJoinListener;
import de.swausb.copilot.listener.GuildMemberLeaveListener;
import de.swausb.copilot.listener.GuildMessageReactionAddListener;
import de.swausb.copilot.listener.GuildMessageReceivedListener;
import de.swausb.copilot.manager.CommandManager;
import de.swausb.copilot.manager.MessageManager;
import de.swausb.copilot.music.PlayerManager;
import de.swausb.copilot.music.commands.PlayCommand;
import de.swausb.copilot.music.commands.StopCommand;
import de.swausb.copilot.utils.Property;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.Compression;

public class Start {
    private MessageManager messageManager;
    private static Start instance;
    public static JDA jda;
    public static Property property;
    public AudioPlayerManager audioPlayerManager;
    public PlayerManager playerManager;

    public CommandManager getCommandManager() {
        return commandManager;
    }

    private CommandManager commandManager;

    public static void main(String[] args) {
        new Start();
    }

    public Start() {
        property = new Property();
        property.setDefaultProps();
        instance = this;
        property = new Property();
        property.setDefaultProps();
        try {
            jda = JDABuilder.createDefault(property.get("cfg", "token"))
                    .setActivity(Activity.listening("Matthias Carras - Ich bin dein CoPilot"))
                    .setAutoReconnect(true)
                    .setStatus(OnlineStatus.DO_NOT_DISTURB)
                    .setCompression(Compression.NONE)
                    .setBulkDeleteSplittingEnabled(false)
                    .enableIntents(Arrays.stream(GatewayIntent.values()).collect(Collectors.toList()))
                    .build().awaitReady();
        } catch (InterruptedException | LoginException e) {
            e.printStackTrace();
        }
        this.audioPlayerManager = new DefaultAudioPlayerManager();
        this.playerManager = new PlayerManager();
        this.messageManager = new MessageManager();
        this.commandManager = new CommandManager();
        jda.addEventListener(new GuildMemberJoinListener());
        jda.addEventListener(new GuildMemberLeaveListener());
        jda.addEventListener(new GuildMessageReactionAddListener());
        jda.addEventListener(new GuildMessageReceivedListener());

        AudioSourceManagers.registerRemoteSources(audioPlayerManager);
        audioPlayerManager.getConfiguration().setFilterHotSwapEnabled(true);

        commandManager.registerCommand(new PlayCommand("play", "play <Songlink>", "Plays a given song from youtube or spotify", "First-Officer"));
        commandManager.registerCommand(new StopCommand("stop", "stop Song", "stops a playing song", "First-Officer"));
    }


    public static Start getInstance() {
        return instance;
    }

    public Guild getMSFS() {
        return jda.getGuilds().get(0);
    }

    public JDA getJda() {
        return jda;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }
}