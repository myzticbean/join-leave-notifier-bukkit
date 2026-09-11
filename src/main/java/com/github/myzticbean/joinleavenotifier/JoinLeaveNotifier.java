package com.github.myzticbean.joinleavenotifier;

import com.github.myzticbean.joinleavenotifier.command.ReloadCommand;
import com.github.myzticbean.joinleavenotifier.listener.JoinLeaveListener;
import com.github.myzticbean.joinleavenotifier.processor.MessageProcessor;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public final class JoinLeaveNotifier extends JavaPlugin {

    // Register at https://bstats.org/getting-started and paste the id here; 0 = metrics off.
    private static final int BSTATS_PLUGIN_ID = 0;

    @Override
    public void onEnable() {
        loadConfig();
        getServer().getPluginManager().registerEvents(new JoinLeaveListener(new MessageProcessor(this)), this);
        getCommand("joinleavenotifier").setExecutor(new ReloadCommand(this));
        if (BSTATS_PLUGIN_ID > 0) new Metrics(this, BSTATS_PLUGIN_ID);
    }

    /** Loads config.yml, filling in any keys missing from an older config with the jar defaults. */
    public void loadConfig() {
        saveDefaultConfig();
        reloadConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
