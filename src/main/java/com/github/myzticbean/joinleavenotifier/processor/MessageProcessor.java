package com.github.myzticbean.joinleavenotifier.processor;

import com.github.myzticbean.joinleavenotifier.config.ConfigProvider;
import io.myzticbean.mcdevtools.colors.ColorTranslator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
        String message = getRandomMessage(configProvider.getPlayerJoinMessages(), recentJoinMessages);
        broadcastMessage(formatMessage(message, player));
    }

    public void processLeaveMessage(Player player) {
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
        Bukkit.broadcastMessage(message);
    }

}
