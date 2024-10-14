package com.github.myzticbean.joinleavenotifier.processor;

import com.github.myzticbean.joinleavenotifier.config.ConfigProvider;
import io.myzticbean.mcdevtools.colors.ColorTranslator;
import io.myzticbean.mcdevtools.log.Logger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

import java.util.*;

public class MessageProcessor {

    private final ConfigProvider configProvider;
    private final Random random;
    private final Queue<String> recentJoinMessages;
    private final Queue<String> recentLeaveMessages;
    private static final int RECENT_MESSAGE_LIMIT = 10;

    public MessageProcessor(ConfigProvider configProvider) {
        this.configProvider = configProvider;
        this.random = new Random();
        this.recentJoinMessages = new LinkedList<>();
        this.recentLeaveMessages = new LinkedList<>();
    }

    public void processJoinMessage(Player player) {
        Logger.info("Primary: " + Bukkit.isPrimaryThread());
        if(isPlayerVanished(player)) return;
        String message = getRandomMessage(configProvider.getPlayerJoinMessages(), recentJoinMessages);
        broadcastMessage(formatMessage(message, player));
    }

    public String getRandomJoinMessage(Player player) {
        return formatMessage(getRandomMessage(configProvider.getPlayerJoinMessages(), recentJoinMessages), player);
    }

    public String getRandomLeaveMessage(Player player) {
        return formatMessage(getRandomMessage(configProvider.getPlayerLeaveMessages(), recentLeaveMessages), player);
    }

    public void processLeaveMessage(Player player) {
        Logger.info("Primary: " + Bukkit.isPrimaryThread());
        if(isPlayerVanished(player)) return;
        String message = getRandomMessage(configProvider.getPlayerLeaveMessages(), recentLeaveMessages);
        broadcastMessage(formatMessage(message, player));
    }

    private String getRandomMessage(List<String> messages, Queue<String> recentMessages) {
        List<String> availableMessages = new ArrayList<>(messages);
        availableMessages.removeAll(recentMessages);

        if (availableMessages.isEmpty()) {
            availableMessages = new ArrayList<>(messages);
        }

        String chosenMessage = availableMessages.get(random.nextInt(availableMessages.size()));

        recentMessages.offer(chosenMessage);
        if (recentMessages.size() > RECENT_MESSAGE_LIMIT) {
            recentMessages.poll();
        }

        return chosenMessage;
    }

    private String formatMessage(String message, Player player) {
        return ColorTranslator.translateColorCodes(message.replace("%player%", player.getName()));
    }

    private void broadcastMessage(String message) {
        Bukkit.getServer().getOnlinePlayers().forEach(p -> p.sendMessage(message));
    }

    // Check vanished players from SuperVanish
    // https://www.spigotmc.org/resources/supervanish-be-invisible.1331/
    private boolean isPlayerVanished(Player player) {
        try {
            player.getMetadata("vanished").forEach(i -> Logger.info(i.asString()));
        } finally {
        }
        for (MetadataValue meta : player.getMetadata("vanished")) {
            if (meta.asBoolean()) return true;
        }
        return false;
    }

}
