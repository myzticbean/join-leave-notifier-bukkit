package com.github.myzticbean.joinleavenotifier.listener;

import com.github.myzticbean.joinleavenotifier.JoinLeaveNotifier;
import io.myzticbean.mcdevtools.events.RegisterEventHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

@RegisterEventHandler
public class PlayerQuitListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(@NotNull PlayerQuitEvent event) {
        event.setQuitMessage(JoinLeaveNotifier.getMessageProcessor().getRandomLeaveMessage(event.getPlayer()));
    }
}
