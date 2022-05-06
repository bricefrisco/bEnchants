package com.bfrisco.benchants.utils;

import com.bfrisco.benchants.enchants.TitanShovel.ShovelInfo;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.List;

public class ToggleImbuedItem implements Listener {

/*    @EventHandler
    public static void activateClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Material coolDown = Material.JIGSAW;
        if (!event.getAction().isRightClick()) return;
        ItemStack item = player.getInventory().getItemInMainHand();
        if (!item.hasItemMeta()) return;
        if (!ItemInfo.isTitanTool(item)) return;
        if (!ItemInfo.isImbued(item)) return;
        if (player.hasCooldown(coolDown)) return;
        player.setCooldown(coolDown,25);
        if (!player.isSneaking()) return;
        if (!player.hasPermission("benchants.toggle")) return;
        toggleAncientPower(item,player);
    }*/

    public static void toggleAncientPower(ItemStack item, Player player){
        List<String> loreList = item.getItemMeta().getLore();
        if (loreList == null) return;
        if (ItemInfo.isActiveImbued(item)) {
            disableAncientPower(item);
            new BEnchantEffects().disableEffect(player);

        } else if (!ItemInfo.isActiveImbued(item)) {
            enableAncientPower(item);
            new BEnchantEffects().enableEffect(player);
        }
    }

    public static void imbue(ItemStack item){
        List<String> loreList = item.getItemMeta().getLore();
        Integer index = ItemInfo.getAncientPowerLoreIndex(loreList);
        loreList.set(index, ItemInfo.IMBUED_ONE);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(loreList);
        item.setItemMeta(meta);
    }

    public static void enableAncientPower(ItemStack item) {
        List<String> loreList = item.getItemMeta().getLore();
        Integer index = ItemInfo.getAncientPowerLoreIndex(loreList);
        loreList.set(index,ItemInfo.IMBUED_ONE);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(loreList);
        item.setItemMeta(meta);
    }

    public static void disableAncientPower(ItemStack item) {
        List<String> loreList = item.getItemMeta().getLore();
        Integer index = ItemInfo.getAncientPowerLoreIndex(loreList);
        loreList.set(index,ItemInfo.IMBUED_INACTIVE);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(loreList);
        item.setItemMeta(meta);
    }




}
