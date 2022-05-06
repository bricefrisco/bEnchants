package com.bfrisco.benchants.enchants;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SuperMan implements Listener {
    static ItemStack superMansTorment = new ItemStack(Material.WITHER_ROSE);
    static ItemMeta meta = superMansTorment.getItemMeta();

    @EventHandler
    public static void LaserEyes(PlayerInteractEvent event){
        meta.setDisplayName(ChatColor.DARK_PURPLE + "Superman's Torment");
        List<String> lore = new ArrayList<>();
        lore.add("...");
        meta.setLore(lore);
        superMansTorment.setItemMeta(meta);

        Player player = event.getPlayer();
        if (player.hasPermission("Superman.op") && event.getAction().isRightClick()) {
            Inventory inv = player.getInventory();

            if (!inv.containsAtLeast(superMansTorment,1)) return;
            if (!player.getInventory().getItemInMainHand().equals(superMansTorment)) {
                player.sendMessage("Item in hand is not correct");
                return;
            }

            Location origin = player.getEyeLocation();
            Vector direction = origin.getDirection();

            direction.multiply(25);

            Location destination = origin.clone().add(direction);
            destination.getWorld();
            direction.normalize();
            Set<Material> mats = new HashSet<>();
            mats.add(Material.AIR);
            Block blockL = player.getTargetBlock(mats, 100);
            Location blockLocation = blockL.getLocation();
            player.sendMessage("Direction: " + direction);
            Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, 1);

            for (int i = 0; i < 25 /* range */; i++) {
                destination.getWorld().spawnParticle(Particle.REDSTONE, origin.add(direction), 10, dustOptions);
                player.getWorld().playSound(player.getLocation(), Sound.BLOCK_CONDUIT_AMBIENT, 100, 2);

            }
            blockL.setType(Material.AIR);
            for (Block block : Tree.generateSphere(blockLocation, 3, false)) {
                if (block.getLocation().equals(blockLocation)) {
                    continue;
                }
                BlockBreakEvent e = new BlockBreakEvent(block, event.getPlayer());
                Bukkit.getPluginManager().callEvent(e);
                if (!e.isCancelled()) {
                    block.breakNaturally();
                }
                blockLocation.createExplosion(3,true);
                block.breakNaturally();
            }
        }
    }

}
