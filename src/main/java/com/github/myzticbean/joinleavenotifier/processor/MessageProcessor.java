package com.github.myzticbean.joinleavenotifier.processor;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MessageProcessor {

    private static final int RECENT_MESSAGE_LIMIT = 10;
    private static final LegacyComponentSerializer COLORS =
            LegacyComponentSerializer.builder().character('&').hexColors().build();

    private final Plugin plugin;
    private final Deque<String> recentJoin = new ArrayDeque<>();
    private final Deque<String> recentFirstJoin = new ArrayDeque<>();
    private final Deque<String> recentLeave = new ArrayDeque<>();

    public MessageProcessor(Plugin plugin) {
        this.plugin = plugin;
    }

    /** @return the join broadcast for this player, or null to suppress it */
    public Component joinMessage(Player player) {
        if (!plugin.getConfig().getBoolean("join-enabled", true) || isSilent(player)) return null;
        List<String> firstJoin = plugin.getConfig().getStringList("player-first-join-messages");
        if (!player.hasPlayedBefore() && !firstJoin.isEmpty()) {
            return format(pick(firstJoin, recentFirstJoin, ThreadLocalRandom.current()), player);
        }
        return format(pick(plugin.getConfig().getStringList("player-join-messages"), recentJoin, ThreadLocalRandom.current()), player);
    }

    /** @return the leave broadcast for this player, or null to suppress it */
    public Component leaveMessage(Player player) {
        if (!plugin.getConfig().getBoolean("leave-enabled", true) || isSilent(player)) return null;
        return format(pick(plugin.getConfig().getStringList("player-leave-messages"), recentLeave, ThreadLocalRandom.current()), player);
    }

    /** Picks a random message not in {@code recent} (any message if all are recent) and records it. */
    static String pick(List<String> messages, Deque<String> recent, Random random) {
        if (messages.isEmpty()) return "";
        List<String> available = new ArrayList<>(messages);
        available.removeAll(recent);
        if (available.isEmpty()) available = messages;
        String chosen = available.get(random.nextInt(available.size()));
        recent.addLast(chosen);
        if (recent.size() > RECENT_MESSAGE_LIMIT) recent.pollFirst();
        return chosen;
    }

    private Component format(String message, Player player) {
        if (message.isEmpty()) return null;
        message = message.replace("%player%", player.getName());
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            message = PlaceholderAPI.setPlaceholders(player, message);
        }
        // PAPI output uses section signs; normalise so one serializer handles both.
        return COLORS.deserialize(message.replace(LegacyComponentSerializer.SECTION_CHAR, '&'));
    }

    // "vanished" metadata is set by SuperVanish, PremiumVanish, EssentialsX and most other vanish plugins.
    private static boolean isSilent(Player player) {
        return player.hasPermission("joinleavenotifier.silent")
                || player.getMetadata("vanished").stream().anyMatch(MetadataValue::asBoolean);
    }
}
