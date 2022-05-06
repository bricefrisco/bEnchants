package com.bfrisco.benchants.enchants.TitanShovel;

import com.bfrisco.benchants.utils.BEnchantEffects;
import com.bfrisco.benchants.utils.ItemInfo;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ToggleImbuedShovel implements Listener {
    public static Material shovel = Material.DIAMOND_SHOVEL;

    @EventHandler
    public static void activateClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Material coolDown = Material.JIGSAW;
        if (!event.getAction().isRightClick()) return;
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType() != shovel) return;
        player.sendMessage("is shovel");
        if (!item.hasItemMeta()) return;
        if (!ItemInfo.isTitanTool(item)) return;
        if (!ItemInfo.isImbued(item)) return;
        if (player.hasCooldown(coolDown)) return;
        player.setCooldown(coolDown,25);
        if (!player.isSneaking()) return;
        if (!player.hasPermission("benchants.toggle")) return;
        toggleImbuedShovelEnchant(item,player);
    }

    public static void toggleImbuedShovelEnchant(ItemStack item, Player player){
        List<String> loreList = item.getItemMeta().getLore();
        if (loreList == null) return;
        if (ShovelInfo.getImbuedState(item) == 1) {
            shovel1ToShovel2(item);
            new BEnchantEffects().disableEffect(player);
            player.sendActionBar("Shovel set to Enchant2");
        } else if (ShovelInfo.getImbuedState(item) == 2) {
            shovel2ToShovel3(item);
            new BEnchantEffects().enableEffect(player);
            player.sendActionBar("Shovel set to Enchant3");
        } else if (ShovelInfo.getImbuedState(item) == 3) {
            disableImbuedItem(item);
            new BEnchantEffects().enableEffect(player);
            player.sendActionBar("Shovel set to dormant");
        } else if (ItemInfo.isDormantCharged(item)) {
            enableImbuedItem(item);
            new BEnchantEffects().enableEffect(player);
            player.sendActionBar("Shovel set to Enchant1");
        }

    }

    public static void enableImbuedItem(ItemStack item) {
        List<String> loreList = item.getItemMeta().getLore();
        Integer index = ItemInfo.getAncientPowerLoreIndex(loreList);
        loreList.set(index,ItemInfo.IMBUED_ONE);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(loreList);
        item.setItemMeta(meta);
    }

    public static void disableImbuedItem(ItemStack item) {
        List<String> loreList = item.getItemMeta().getLore();
        Integer index = ItemInfo.getAncientPowerLoreIndex(loreList);
        loreList.set(index,ItemInfo.IMBUED_INACTIVE);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(loreList);
        item.setItemMeta(meta);
    }
    public static void shovel1ToShovel2(ItemStack item) {
        List<String> loreList = item.getItemMeta().getLore();
        Integer index = ItemInfo.getAncientPowerLoreIndex(loreList);
        loreList.set(index,ItemInfo.IMBUED_TWO);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(loreList);
        item.setItemMeta(meta);
    }
    public static void shovel2ToShovel3(ItemStack item) {
        List<String> loreList = item.getItemMeta().getLore();
        Integer index = ItemInfo.getAncientPowerLoreIndex(loreList);
        loreList.set(index,ItemInfo.IMBUED_THREE);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(loreList);
        item.setItemMeta(meta);
    }



}
