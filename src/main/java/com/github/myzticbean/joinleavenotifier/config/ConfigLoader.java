package com.github.myzticbean.joinleavenotifier.config;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public class ConfigLoader {

    private final JavaPlugin plugin;
    @Getter
    private FileConfiguration config;

    public ConfigLoader(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        config = plugin.getConfig();

        // Set default values if they don't exist
        setDefaultIfNotExists("config-version", 1);
        setDefaultIfNotExists("player-join-messages", getDefaultJoinMessages());
        setDefaultIfNotExists("player-leave-messages", getDefaultLeaveMessages());

        plugin.saveConfig();
    }

    private void setDefaultIfNotExists(String path, Object value) {
        if (!config.contains(path)) {
            config.set(path, value);
        }
    }

    private List<String> getDefaultJoinMessages() {
        return Arrays.asList(
                "Welcome back, %player%! We missed you!",
                "Look who decided to grace us with their presence! It's %player%!",
                "%player% has joined the party! Let the fun begin!",
                "Guess who's back? Back again? %player%'s back! Tell a friend!"
        );
    }

    private List<String> getDefaultLeaveMessages() {
        return Arrays.asList(
                "%player% has left the building!",
                "Farewell, %player%! We'll miss your shenanigans!",
                "%player% vanished into thin air! Magic or rage quit?",
                "And just like that, %player% was gone... Until next time!"
        );
    }

}
