package com.bfrisco.benchants.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.List;


public class ItemInfo {

    public static final String ANCIENT_RED = "§8Ancient Power§x§F§F§0§0§0§0 ♆";
    public static final String ANCIENT_YELLOW = "§8Ancient Power§x§F§F§E§C§2§7 ♆";
    public static final String ANCIENT_BLUE = "§8Ancient Power§x§6§D§5§E§F§F ♆";

    public static final String ANCIENT_CHARGE = "§8Charge:§x§F§F§0§0§4§C";
    public static final String POWER_CRYSTAL = "§x§F§F§0§0§4§CAncient Charge";
    public static final String ANCIENT_DEPLETED = " ";

    public static final String RED = "§x§F§F§0§0§0§0";
    public static final String YELLOW = "§x§F§F§E§C§2§7";
    public static final String BLUE = "§x§6§D§5§E§F§F";

    public static final String CHARGED_INACTIVE = "§8Ancient Power ♆";
    public static final String CHARGED_ONE = "§8Ancient Power §x§F§F§0§0§4§C♆ I";
    public static final String CHARGED_TWO = "§8Ancient Power §x§F§F§0§0§4§C♆ II";
    public static final String CHARGED_THREE = "§8Ancient Power §x§F§F§0§0§4§C♆ III";

    public static final String IMBUED_INACTIVE = "§8Ancient Power Ω";
    public static final String IMBUED_ONE = "§8Ancient Power §x§F§F§0§0§4§CΩ I";
    public static final String IMBUED_TWO = "§8Ancient Power §x§F§F§0§0§4§CΩ II";
    public static final String IMBUED_THREE = "§8Ancient Power §x§F§F§0§0§4§CΩ III";

    public static final List<String> TITAN_LORE = new ArrayList<>(){
        {
            add(ANCIENT_RED);
            add(ANCIENT_YELLOW);
            add(ANCIENT_BLUE);

            add(CHARGED_INACTIVE);
            add(CHARGED_ONE);
            add(CHARGED_TWO);
            add(CHARGED_THREE);

            add(IMBUED_INACTIVE);
            add(IMBUED_ONE);
            add(IMBUED_TWO);
            add(IMBUED_THREE);
        }
    };

    public static final List<String> UNIMBUED_LORE = new ArrayList<>(){
        {
            add(ANCIENT_RED);
            add(ANCIENT_YELLOW);
            add(ANCIENT_BLUE);
        }
    };

    public static final List<String> ACTIVE_LORE = new ArrayList<>(){
        {
            add(CHARGED_ONE);
            add(CHARGED_TWO);
            add(CHARGED_THREE);

            add(IMBUED_ONE);
            add(IMBUED_TWO);
            add(IMBUED_THREE);
        }
    };

    public static final List<String> IMBUED_LORE = new ArrayList<>(){
        {
            add(IMBUED_ONE);
            add(IMBUED_TWO);
            add(IMBUED_THREE);
            add(IMBUED_INACTIVE);
        }
    };

    public static final List<String> ACTIVE_IMBUED_LORE = new ArrayList<>(){
        {
            add(IMBUED_ONE);
            add(IMBUED_TWO);
            add(IMBUED_THREE);

        }
    };

    public static final List<String> CHARGED_LORE = new ArrayList<>(){
        {
            add(CHARGED_ONE);
            add(CHARGED_TWO);
            add(CHARGED_THREE);
            add(CHARGED_INACTIVE);
        }
    };

    public static final List<String> ACTIVE_CHARGED_LORE = new ArrayList<>(){
        {
            add(CHARGED_ONE);
            add(CHARGED_TWO);
            add(CHARGED_THREE);

        }
    };

    public static final List<String> INACTIVE_LORE = new ArrayList<>(){
        {
            add(IMBUED_INACTIVE);
            add(CHARGED_INACTIVE);
        }
    };

    public static final List<String> LEVEL_ONE = new ArrayList<>(){
        {
            add(IMBUED_ONE);
            add(CHARGED_ONE);
        }
    };

    public static final List<String> LEVEL_TWO = new ArrayList<>(){
        {
            add(IMBUED_TWO);
            add(CHARGED_TWO);
        }
    };

    public static final List<String> LEVEL_THREE = new ArrayList<>(){
        {
            add(IMBUED_THREE);
            add(CHARGED_THREE);
        }
    };

    public static boolean isTitanTool(ItemStack item){
        if (!item.hasItemMeta()) return false;
        List<String> loreList = item.getItemMeta().getLore();
        if (loreList == null) return false;
        for (String lore : loreList) {
            if (TITAN_LORE.contains(lore)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isLevelOne(ItemStack item){
        if (!item.hasItemMeta()) return false;
        List<String> loreList = item.getItemMeta().getLore();
        if (loreList == null) return false;
        for (String lore : loreList) {
            if (LEVEL_ONE.contains(lore)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isLevelTwo(ItemStack item){
        if (!item.hasItemMeta()) return false;
        List<String> loreList = item.getItemMeta().getLore();
        if (loreList == null) return false;
        for (String lore : loreList) {
            if (LEVEL_TWO.contains(lore)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isLevelThree(ItemStack item){
        if (!item.hasItemMeta()) return false;
        List<String> loreList = item.getItemMeta().getLore();
        if (loreList == null) return false;
        for (String lore : loreList) {
            if (LEVEL_THREE.contains(lore)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isActiveImbued(ItemStack item){

        List<String> loreList = item.getItemMeta().getLore();
        for (String lore : loreList) {
            if (ACTIVE_IMBUED_LORE.contains(lore)) {
                Bukkit.getServer().getConsoleSender().sendMessage("Inside successful AncientPowerActive check");
                return true;
            }
        }
        return false;
    }

    public static boolean isActiveCharged(ItemStack item){
        Bukkit.getServer().getConsoleSender().sendMessage("Inside AncientChargeActive check");
        List<String> loreList = item.getItemMeta().getLore();
        for (String lore : loreList) {
            if (ACTIVE_CHARGED_LORE.contains(lore)) {
                Bukkit.getServer().getConsoleSender().sendMessage("Inside successful AncientChargeActive check");
                return true;
            }
        }
        return false;
    }

    public static boolean isDormantCharged(ItemStack item){
        Bukkit.getServer().getConsoleSender().sendMessage("Inside AncientChargeActive check");
        List<String> loreList = item.getItemMeta().getLore();
        for (String lore : loreList) {
            if (INACTIVE_LORE.contains(lore)) {
                Bukkit.getServer().getConsoleSender().sendMessage("Inside successful AncientChargeActive check");
                return true;
            }
        }
        return false;
    }

    public static boolean isImbued(ItemStack item) {

        List<String> loreList = item.getItemMeta().getLore();
        if (loreList == null) return false;
        for (String lore : loreList) {
            if (IMBUED_LORE.contains(lore)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isUnImbued(ItemStack item) {

        List<String> loreList = item.getItemMeta().getLore();
        if (loreList == null) return false;
        for (String lore : loreList) {
            if (UNIMBUED_LORE.contains(lore)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPowerCrystal(ItemStack item) {
        if (!item.hasItemMeta()) return false;
        List<String> loreList = item.getItemMeta().getLore();
        if (loreList == null) return false;
        Material type = item.getType();
        Material powerCrystal = Material.AMETHYST_SHARD;
        Bukkit.getServer().getConsoleSender().sendMessage("inside isPowerCrystal");
        if (loreList.stream().anyMatch(lore -> lore.matches(POWER_CRYSTAL) && (type == powerCrystal))){
            Bukkit.getServer().getConsoleSender().sendMessage("passed isPowerCrystal check");
            return true;
        };

        return false;


    }

    public static boolean hasCharge(ItemStack item){
        if (!item.hasItemMeta()) return false;
        List<String> loreList = item.getItemMeta().getLore();
        if (loreList == null) return false;
        Material type = item.getType();
        Material fireCharge = Material.FIRE_CHARGE;
        for (String lore : loreList) {
            if (lore.matches("(.*)" + ANCIENT_CHARGE + "(.*)") && (type != fireCharge)) {
                return true;
            }
        }
        return false;
    }

    public static String getColor(ItemStack item){
        if (!item.hasItemMeta()) return null;
        List<String> loreList = item.getItemMeta().getLore();
        if (loreList == null) return null;
        for (String lore : loreList) {
            if (lore.matches("(.*)" + RED + "(.*)")) {
                return "RED";
            } else if (lore.matches("(.*)" + YELLOW + "(.*)")) {
                return "YELLOW";
            } else if (lore.matches("(.*)" + BLUE + "(.*)")) {
                return "BLUE";
            }
        }
        return null;
    }

    public static Integer getAncientPowerLoreIndex(List<String> loreList) {
        Bukkit.getServer().getConsoleSender().sendMessage("inside of getAncientPowerLoreIndex");
        for (int i = 0; i < loreList.size(); i++){
/*            if (IMBUED_LORE.contains(loreList.get(i)) ||
                    UNIMBUED_LORE.contains(loreList.get(i)) ||
                    CHARGED_LORE.contains(loreList.get(i))) return i;*/
            if (TITAN_LORE.contains(loreList.get(i))) return i;
        }
        return null;
    }
}
