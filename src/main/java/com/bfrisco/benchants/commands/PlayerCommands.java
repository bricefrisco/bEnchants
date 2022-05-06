package com.bfrisco.benchants.commands;

import com.bfrisco.benchants.BEnchants;
import com.bfrisco.benchants.utils.BEnchantEffects;
import com.bfrisco.benchants.utils.ItemInfo;
import com.bfrisco.benchants.utils.ToggleImbuedItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class PlayerCommands implements CommandExecutor{

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

        if ("imbue".equalsIgnoreCase(args[0]) ){
            Material coolDown = Material.JIGSAW;
            if (!(sender instanceof Player player)) return false;
            player.sendMessage(ChatColor.RED + "------Debug------");
            ItemStack item = player.getInventory().getItemInMainHand();
            if (!ItemInfo.isTitanTool(item)) return false;
            if (!player.hasPermission("benchants.imbue")) {
                player.sendMessage(ChatColor.RED + "No permission.");
                return false;
            }
            if (ItemInfo.isImbued(item)) {
                player.sendMessage(ChatColor.GREEN + "That item is already imbued!");
                return false;
            }
            if (!player.hasCooldown(coolDown)) {
                player.sendMessage(ChatColor.GREEN + "Are you sure you want to imbue this tool for $1,000,000?");
                player.sendMessage(ChatColor.GREEN + "Retype the command to confirm");
                player.setCooldown(coolDown, 200);
                return false;
            }
            List<String> loreList = item.getItemMeta().getLore();
            if (loreList == null) return false;
            for (String lore : loreList) {
                if (ItemInfo.UNIMBUED_LORE.contains(lore)) {
                    ToggleImbuedItem.imbue(item);
                    new BEnchantEffects().enableEffect(player);
                    BEnchants.LOGGER.info(player.getName() + " has imbued a titan tool...");
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}
