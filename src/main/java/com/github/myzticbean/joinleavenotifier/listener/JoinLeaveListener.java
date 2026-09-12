package com.github.myzticbean.joinleavenotifier.listener;

import com.github.myzticbean.joinleavenotifier.processor.MessageProcessor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class JoinLeaveListener implements Listener {

    private final MessageProcessor messages;

    public JoinLeaveListener(MessageProcessor messages) {
        this.messages = messages;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        event.joinMessage(messages.joinMessage(event.getPlayer()));
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(@NotNull PlayerQuitEvent event) {
        event.quitMessage(messages.leaveMessage(event.getPlayer()));
    }
}
