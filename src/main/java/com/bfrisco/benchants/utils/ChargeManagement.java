package com.bfrisco.benchants.utils;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;


public class ChargeManagement implements Listener {


    @EventHandler
    public static void applyCharge(InventoryClickEvent event){


        if (event.isLeftClick()) {
            Player player = (Player) event.getWhoClicked();
            ItemStack itemOnCursor = player.getItemOnCursor();
            ItemStack itemClicked = event.getCurrentItem();
            int numberOfCharge = itemOnCursor.getAmount();
            if (!ItemInfo.isPowerCrystal(itemOnCursor)) return;
            if (event.getCurrentItem() == null) return;
            if (!ItemInfo.isTitanTool(itemClicked)) return;
            if (ItemInfo.isImbued(itemClicked)) return;
            addChargeLore(itemClicked,numberOfCharge * 100);
            player.getItemOnCursor().setAmount(0);
            player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK,player.getEyeLocation(),100);
            player.getWorld().playSound(player.getLocation(), Sound.BLOCK_CONDUIT_ACTIVATE,10, 1);
            event.setCancelled(true);
        }
    }

    public static void addChargeLore(ItemStack item, Integer amount){
        List<String> loreList = item.getItemMeta().getLore();
        Integer index = ItemInfo.getAncientPowerLoreIndex(loreList);
        int chargeIndex = index + 1;

        if (ItemInfo.hasCharge(item)) {
            String string = loreList.get(chargeIndex);
            String string1 = string.substring(24);
            int previousCharge = Integer.parseInt(string1);
            int newCharge = previousCharge + (amount);
            loreList.set(chargeIndex,ItemInfo.ANCIENT_CHARGE + " " + newCharge);
            ItemMeta meta = item.getItemMeta();
            meta.setLore(loreList);
            item.setItemMeta(meta);
            return;
        } else
        loreList.set(chargeIndex,ItemInfo.ANCIENT_CHARGE + " " + (amount));
        loreList.set(index,ItemInfo.CHARGED_ONE);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(loreList);
        item.setItemMeta(meta);
    }

    public static void decreaseChargeLore(ItemStack item, Player player){
        List<String> loreList = item.getItemMeta().getLore();
        Integer index = ItemInfo.getAncientPowerLoreIndex(loreList);
        Integer chargeIndex = index + 1;
        if (ItemInfo.hasCharge(item)) {
            String string = loreList.get(chargeIndex);
            String string1 = string.substring(24);
            player.sendMessage(string1);
            int previousCharge = Integer.parseInt(string1);
            int remainingCharge = previousCharge - 1;
            if (remainingCharge < 1 && ItemInfo.getColor(item).equals("RED")) {
                loreList.set(index,ItemInfo.ANCIENT_RED);
                loreList.set(chargeIndex,ItemInfo.ANCIENT_DEPLETED);
                new BEnchantEffects().depletedChargeEffect(player);
                ItemMeta meta = item.getItemMeta();
                meta.setLore(loreList);
                item.setItemMeta(meta);
            } else if (remainingCharge < 1 && ItemInfo.getColor(item).equals("YELLOW")) {
                loreList.set(index, ItemInfo.ANCIENT_YELLOW);
                loreList.set(chargeIndex,ItemInfo.ANCIENT_DEPLETED);
                new BEnchantEffects().depletedChargeEffect(player);
                ItemMeta meta = item.getItemMeta();
                meta.setLore(loreList);
                item.setItemMeta(meta);
            } else if (remainingCharge < 1 && ItemInfo.getColor(item).equals("BLUE")) {
                loreList.set(index, ItemInfo.ANCIENT_BLUE);
                loreList.set(chargeIndex, ItemInfo.ANCIENT_DEPLETED);
                new BEnchantEffects().depletedChargeEffect(player);
                ItemMeta meta = item.getItemMeta();
                meta.setLore(loreList);
                item.setItemMeta(meta);
            } else
            loreList.set(chargeIndex,ItemInfo.ANCIENT_CHARGE + " " + remainingCharge);
            ItemMeta meta = item.getItemMeta();
            meta.setLore(loreList);
            item.setItemMeta(meta);
        }
    }
}
