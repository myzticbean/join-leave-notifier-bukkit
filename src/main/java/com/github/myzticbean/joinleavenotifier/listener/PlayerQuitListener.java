package com.github.myzticbean.joinleavenotifier.listener;

import com.github.myzticbean.joinleavenotifier.JoinLeaveNotifier;
import io.myzticbean.mcdevtools.events.RegisterEventHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@RegisterEventHandler
public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null); // Suppress default quit message
        JoinLeaveNotifier.getMessageProcessor().processLeaveMessage(event.getPlayer());
    }

}
