package com.github.myzticbean.joinleavenotifier.listener;

import com.github.myzticbean.joinleavenotifier.JoinLeaveNotifier;
import io.myzticbean.mcdevtools.events.RegisterEventHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

@RegisterEventHandler
public class PlayerJoinListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        event.setJoinMessage(JoinLeaveNotifier.getMessageProcessor().getRandomJoinMessage(event.getPlayer()));
    }
}
