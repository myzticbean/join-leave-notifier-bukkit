package com.github.myzticbean.joinleavenotifier.listener;

import com.github.myzticbean.joinleavenotifier.JoinLeaveNotifier;
import io.myzticbean.mcdevtools.events.RegisterEventHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@RegisterEventHandler
public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null); // Suppress default join message
        JoinLeaveNotifier.getMessageProcessor().processJoinMessage(event.getPlayer());
    }

}
