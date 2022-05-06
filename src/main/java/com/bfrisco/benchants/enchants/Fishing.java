package com.bfrisco.benchants.enchants;

import com.bfrisco.benchants.BEnchants;
import com.bfrisco.benchants.utils.ChargeManagement;
import com.bfrisco.benchants.utils.ItemInfo;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;


public class Fishing implements Listener {

    public static final String TROPICALFISH = "Tropical Fish";
    public static final String PUFFERFISH = "Pufferfish";
    public static final String RAWCOD = "Raw Cod";
    public static final String RAWSALMON = "Raw Salmon";
    public static final ItemStack TropicalFish = new ItemStack(Material.TROPICAL_FISH);
    public static final ItemStack PufferFish = new ItemStack(Material.PUFFERFISH);
    public static final ItemStack Raw_Cod = new ItemStack(Material.COD);
    public static final ItemStack Raw_Salmon = new ItemStack(Material.SALMON);

    @EventHandler
    public void onFishEvent(PlayerFishEvent event){

        Player player = event.getPlayer();
        Location location = player.getEyeLocation();
        ItemStack item = player.getInventory().getItemInMainHand();
        Inventory inv = player.getInventory();
        if (!ItemInfo.isTitanTool(item)) return;

        if (!ItemInfo.isActive(item) && !ItemInfo.isActiveCharge(item)) return;



        player.sendMessage(event.getState().toString());
        if (event.getState().toString().equalsIgnoreCase("CAUGHT_FISH")){
            player.sendMessage(event.getState().toString());
            player.sendMessage("Succeeded case");


            if (event.getCaught().getName().equalsIgnoreCase(TROPICALFISH)) {
                event.getPlayer().getWorld().dropItem(location, TropicalFish);
                ChargeManagement.decreaseChargeLore(item,player);
            }
            if (event.getCaught().getName().equalsIgnoreCase(PUFFERFISH)) {
                event.getPlayer().getWorld().dropItem(location, PufferFish);
                ChargeManagement.decreaseChargeLore(item,player);
            }
            if (event.getCaught().getName().equalsIgnoreCase(RAWCOD)) {
                event.getPlayer().getWorld().dropItem(location, Raw_Cod);
                ChargeManagement.decreaseChargeLore(item,player);
            }
            if (event.getCaught().getName().equalsIgnoreCase(RAWSALMON)) {
                event.getPlayer().getWorld().dropItem(location, Raw_Salmon);
                ChargeManagement.decreaseChargeLore(item,player);
            }
            player.sendMessage(String.valueOf(event.getCaught().getName()));

        }



    }




}
