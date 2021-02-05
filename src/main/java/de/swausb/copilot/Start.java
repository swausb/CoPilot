package de.swausb.copilot;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.security.auth.login.LoginException;

import de.swausb.copilot.utils.Property;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.Compression;

public class Start {

    public static JDA jda;
    public static Property property;

    public static void main(String[] args) {
        property = new Property();
        property.setDefaultProps();
        try {
            jda = JDABuilder.createDefault(property.get("cfg", "token"))
                    .setActivity(Activity.listening("Matthias Carras - Ich bin dein CoPilot"))
                    .setAutoReconnect(true)
                    .setStatus(OnlineStatus.ONLINE)
                    .setCompression(Compression.NONE)
                    .setBulkDeleteSplittingEnabled(false)
                    .enableIntents(Arrays.stream(GatewayIntent.values()).collect(Collectors.toList()))
                    .build().awaitReady();
        } catch (InterruptedException | LoginException e) {
            e.printStackTrace();
        }
    }
}