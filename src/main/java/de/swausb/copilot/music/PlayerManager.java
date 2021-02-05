package de.swausb.copilot.music;

import de.swausb.copilot.Start;

import java.util.concurrent.ConcurrentHashMap;

public class PlayerManager {
    public ConcurrentHashMap<Long, MusicController> controller;

    public PlayerManager(){
        this.controller = new ConcurrentHashMap<Long, MusicController>();
    }
    public MusicController getController(long guildid) {
        MusicController mc = null;

        if(this.controller.containsKey(guildid)) {
            mc = this.controller.get(guildid);
        }
        else {
            mc = new MusicController(Start.getInstance().getMSFS());
            this.controller.put(guildid, mc);
        }

        return mc;
    }
}
