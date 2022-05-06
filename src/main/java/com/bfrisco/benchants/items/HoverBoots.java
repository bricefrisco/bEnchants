package com.bfrisco.benchants.items;

import com.bfrisco.benchants.BEnchants;

import com.bfrisco.benchants.utils.HoverBootParticles;
import com.bfrisco.benchants.utils.ParticlesData;
import it.unimi.dsi.fastutil.Hash;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;

/*public class HoverBoots implements Listener {

    public static Material coolDown = Material.AXOLOTL_SPAWN_EGG;
    public static HashMap<UUID,Boolean> isReady = new HashMap<UUID, Boolean>();

    public static ItemStack hoverBoots() {
        ItemStack hoverBoots = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta meta = hoverBoots.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setDisplayName("HoverBoots");
        lore.add("the boots that hover");
        meta.setLore(lore);
        hoverBoots.setItemMeta(meta);
        return hoverBoots;
    }

    @EventHandler
    public void readyBoots(PlayerInteractEvent event){
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        if (!player.isSneaking() && event.getAction().isRightClick()){
            isReady.putIfAbsent(playerUUID,true);
            if (isReady.get(playerUUID)){
                isReady.put(playerUUID,false);
                player.sendMessage("Boots are not ready");
            } else if (!isReady.get(playerUUID)){
                isReady.put(playerUUID,true);
                player.sendMessage("Boots are ready");
            }
        }
        return;
    }

    @EventHandler
    public void onHover(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        if (!event.getAction().isRightClick()) return;

        *//*if (!player.isSneaking()) return;*//*
        if (player.hasCooldown(coolDown))return;
        if (isWearingElytra(player)) {
            player.sendMessage("player is wearing elytra");
            return;
        }
        if (event.getClickedBlock().getType() == Material.CHEST) {
            player.sendMessage("You are clicking on a chest");
            return;
        }
        if (event.getClickedBlock().getType() == Material.ENDER_CHEST) {
            player.sendMessage("You are clicking on a ender chest");
            return;
        }
        if (event.getClickedBlock().getType() == Material.SHULKER_BOX) {
            player.sendMessage("You are clicking on a shulkerbox");
            return;
        }
        if (!isWearingHoverBoots(player)) {
            player.sendMessage("You are not wearing Hover boots!");
            return;
        }
        Location loc = player.getLocation();
        double Y = loc.getY();
        double newY = Y + 0.1;
        player.sendMessage("Y location?" + newY );
        Vector direction = getVector(player);
        double xDirection = direction.getX();
        double zDirection = direction.getZ();
        Vector newDirection = direction.setY(loc.getY()+.1).normalize();
        Vector vector = new Vector(xDirection,0,zDirection);
        if (!player.hasCooldown(coolDown)){
            player.setVelocity(newDirection.multiply(.33));
            player.setCooldown(coolDown,120);
            ParticlesData particle = new ParticlesData(player.getUniqueId());
            if (particle.hasID()){
                particle.endTask();
                particle.removeID();
            }
            HoverBootParticles trails = new HoverBootParticles(player);
            trails.startTotem();
            new BukkitRunnable(){
                @Override
                public void run() {
                    player.setGravity(false);
                    player.setVelocity(vector.multiply(.4));
                    player.sendMessage("Gravity set to false");
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.setGravity(true);
                            player.sendMessage("gravity set to true");
                            player.setAllowFlight(false);
                            particle.endTask();
                            particle.removeID();
                        }
                    }.runTaskLater(BEnchants.getInstance(), 20 * 3);
                }
            }.runTaskLater(BEnchants.getInstance(), 5);
        } else {
            player.sendMessage("You are on the ground!");
        }
    }
    public Vector getVector(Player player){
        Location origin = player.getEyeLocation();
        return origin.getDirection();
    }
    @EventHandler
    public void enableGrav(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if (!player.hasGravity()) {
            player.setGravity(true);
        }
    }

    public boolean isWearingElytra(Player player){
        ItemStack chestSlot = player.getInventory().getChestplate();
        if (chestSlot == null) return false;
        if (chestSlot.getType() == Material.ELYTRA)
            return true;
        return false;
    }

    public boolean isWearingHoverBoots(Player player){
        ItemStack bootSlot = player.getInventory().getBoots();
        if (bootSlot == null) return false;
        if (!bootSlot.isSimilar(hoverBoots())) return false;
        return true;
    }

}*/
