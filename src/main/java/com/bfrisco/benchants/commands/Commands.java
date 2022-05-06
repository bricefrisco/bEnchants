package com.bfrisco.benchants.commands;

import com.bfrisco.benchants.BEnchants;
import com.bfrisco.benchants.enchants.Durability;
import com.bfrisco.benchants.enchants.Trench;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) return false;
        if (!player.hasPermission("benchants.enchant")) {
            player.sendMessage(ChatColor.RED + "No permission.");
            return true;
        }
        if (args.length == 0) return false;
        if ("reload".equalsIgnoreCase(args[0])) {
            BEnchants.LOGGER.info("Reloading config...");
            BEnchants.PLUGIN.reloadConfig();
            Trench.loadConfig();
            Durability.loadConfig();
            player.sendMessage(ChatColor.GREEN + "Successfully reloaded config.");
            return true;
        }

        if (args.length != 2) return false;

        return true;
    }



}
