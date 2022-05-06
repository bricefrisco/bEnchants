package com.bfrisco.benchants.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ToggleChargedItem implements Listener {

/*    @EventHandler
    public static void activateClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Material coolDown = Material.JIGSAW;
        if (!event.getAction().isRightClick()) return;
        ItemStack item = player.getInventory().getItemInMainHand();
        if (!item.hasItemMeta()) return;
        if (!ItemInfo.isTitanTool(item)) return;
        if (!ItemInfo.hasCharge(item)) return;
        if (player.hasCooldown(coolDown)) return;
        player.setCooldown(coolDown,25);
        if (!player.isSneaking()) return;
        if (!player.hasPermission("benchants.charge.toggle")) return;
        toggleChargedItem(item,player);
    }

    public static void toggleChargedItem(ItemStack item, Player player){
        List<String> loreList = item.getItemMeta().getLore();
        if (loreList == null) return;
        if (ItemInfo.isActiveCharge(item)) {
            disableChargedItem(item);
            new BEnchantEffects().disableEffect(player);

        } else if (!ItemInfo.isActiveCharge(item)) {
            enableChargedItem(item);
            new BEnchantEffects().enableEffect(player);
        }
    }

    public static void enableChargedItem(ItemStack item) {
        List<String> loreList = item.getItemMeta().getLore();
        Integer index = ItemInfo.getAncientPowerLoreIndex(loreList);
        loreList.set(index,ItemInfo.ANCIENT_CHARGE_ACTIVE);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(loreList);
        item.setItemMeta(meta);
    }

    public static void disableChargedItem(ItemStack item) {
        List<String> loreList = item.getItemMeta().getLore();
        Integer index = ItemInfo.getAncientPowerLoreIndex(loreList);
        loreList.set(index,ItemInfo.ANCIENT_CHARGE_INACTIVE);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(loreList);
        item.setItemMeta(meta);
    }*/

}
