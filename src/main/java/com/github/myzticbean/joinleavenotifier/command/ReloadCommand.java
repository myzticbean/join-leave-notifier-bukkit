package com.github.myzticbean.joinleavenotifier.command;

import com.github.myzticbean.joinleavenotifier.JoinLeaveNotifier;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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
            sender.sendMessage(Component.text("Usage: /joinleavenotifier reload", NamedTextColor.RED));
            return true;
        }
        if (!sender.hasPermission("joinleavenotifier.admin")) {
            sender.sendMessage(Component.text("[JoinLeaveNotifier] You don't have permission to use this command.", NamedTextColor.RED));
            return true;
        }
        plugin.loadConfig();
        sender.sendMessage(Component.text("[JoinLeaveNotifier] Configuration reloaded successfully!", NamedTextColor.GREEN));
        return true;
    }
}
