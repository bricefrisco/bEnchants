package com.bfrisco.benchants.enchants.TitanShovel;

import com.bfrisco.benchants.utils.ItemInfo;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import java.util.List;

public class ShovelInfo {


    public static int getChargedShovel(ItemStack item){
        Bukkit.getServer().getConsoleSender().sendMessage("Inside AncientChargeActive check");
        List<String> loreList = item.getItemMeta().getLore();
        for (String lore : loreList) {
            if (ItemInfo.CHARGED_ONE.equalsIgnoreCase(lore)) {
                Bukkit.getServer().getConsoleSender().sendMessage("Inside successful AncientChargeActive check");
                return 1;
            } else if (ItemInfo.CHARGED_TWO.equalsIgnoreCase(lore)) {
                Bukkit.getServer().getConsoleSender().sendMessage("Inside successful AncientChargeActive check");
                return 2;
            } else if (ItemInfo.CHARGED_THREE.equalsIgnoreCase(lore)) {
                Bukkit.getServer().getConsoleSender().sendMessage("Inside successful AncientChargeActive check");
                return 3;
            }
        }
        return -1;
    }

    public static int getImbuedState(ItemStack item){
        Bukkit.getServer().getConsoleSender().sendMessage("Inside AncientChargeActive check");
        List<String> loreList = item.getItemMeta().getLore();
        for (String lore : loreList) {
            if (ItemInfo.IMBUED_ONE.equalsIgnoreCase(lore)) {
                Bukkit.getServer().getConsoleSender().sendMessage("Inside successful AncientChargeActive check");
                return 1;
            } else if (ItemInfo.IMBUED_TWO.equalsIgnoreCase(lore)) {
                Bukkit.getServer().getConsoleSender().sendMessage("Inside successful AncientChargeActive check");
                return 2;
            } else if (ItemInfo.IMBUED_THREE.equalsIgnoreCase(lore)) {
                Bukkit.getServer().getConsoleSender().sendMessage("Inside successful AncientChargeActive check");
                return 3;
            }
        }
        return -1;
    }

    public static boolean isRunnableShovel(ItemStack item) {
        if (item.getType() != Material.DIAMOND_SHOVEL) return false;
        if (item.getItemMeta() == null) return false;
        List<String> loreList = item.getItemMeta().getLore();
        if (loreList == null) return false;
        for (String lore : loreList) {
            if (ItemInfo.ACTIVE_LORE.contains(lore)) {
                return true;
            }
        }
        return false;
    }

}
