package com.github.myzticbean.joinleavenotifier.command;

import com.github.myzticbean.joinleavenotifier.JoinLeaveNotifier;
import com.github.myzticbean.joinleavenotifier.config.ConfigLoader;
import com.github.myzticbean.joinleavenotifier.config.ConfigProvider;
import com.github.myzticbean.joinleavenotifier.processor.MessageProcessor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {

    private final JoinLeaveNotifier plugin;

    public ReloadCommand(JoinLeaveNotifier plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length != 1 || !args[0].equalsIgnoreCase("reload")) {
            sender.sendMessage(ChatColor.RED + "Usage: /joinleavenotifier reload");
            return true;
        }

        if (!sender.hasPermission("joinleavenotifier.admin")) {
            sender.sendMessage(ChatColor.RED + "[JoinLeaveNotifier] You don't have permission to use this command.");
            return true;
        }

        // Reload the configuration
        plugin.reloadConfig();

        // Reinitialize ConfigLoader and ConfigProvider
        ConfigLoader configLoader = new ConfigLoader(plugin);
        configLoader.loadConfig();
        JoinLeaveNotifier.setConfigProvider(new ConfigProvider(configLoader));

        // Reinitialize MessageProcessor
        JoinLeaveNotifier.setMessageProcessor(new MessageProcessor(JoinLeaveNotifier.getConfigProvider()));

        sender.sendMessage(ChatColor.GREEN + "[JoinLeaveNotifier] Configuration reloaded successfully!");
        return true;
    }
}
