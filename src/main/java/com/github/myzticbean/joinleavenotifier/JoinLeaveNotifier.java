package com.github.myzticbean.joinleavenotifier;

import com.github.myzticbean.joinleavenotifier.command.ReloadCommand;
import com.github.myzticbean.joinleavenotifier.config.ConfigLoader;
import com.github.myzticbean.joinleavenotifier.config.ConfigProvider;
import com.github.myzticbean.joinleavenotifier.processor.MessageProcessor;
import io.myzticbean.mcdevtools.MCDevTools;
import io.myzticbean.mcdevtools.events.processor.EventRegistrar;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

public final class JoinLeaveNotifier extends JavaPlugin {

    @Getter
    @Setter
    private static ConfigProvider configProvider;

    @Getter
    @Setter
    private static MessageProcessor messageProcessor;

    @Override
    public void onEnable() {
        // Plugin startup logic
        // Initialize MCDevTools
        MCDevTools.initialize(this);
        setupConfig(this);
        // Setup message processor
        setupMessageProcessor();
        // Register event listeners
        EventRegistrar.registerEvents(this, "com.github.myzticbean.joinleavenotifier.listener");
        // Register the reload command
        getCommand("joinleavenotifier").setExecutor(new ReloadCommand(this));
    }

    private static void setupMessageProcessor() {
        messageProcessor = new MessageProcessor(configProvider);
    }

    /**
     * Setup the config for the plugin
     * 
     * @param plugin The plugin instance
     */
    private static void setupConfig(JavaPlugin plugin) {
        ConfigLoader configLoader = new ConfigLoader(plugin);
        configLoader.loadConfig();
        configProvider = new ConfigProvider(configLoader);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
