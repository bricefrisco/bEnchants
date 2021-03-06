package com.bfrisco.benchants.enchants;

import com.bfrisco.benchants.BEnchants;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class Trench implements Listener {
    public static final Set<Material> ALLOWED_ITEMS = new HashSet<>();
    public static final Set<Material> ENCHANTABLE_ITEMS = new HashSet<>();
    public static final Set<Location> IGNORE_LOCATIONS = new HashSet<>();

    public Trench() {
        loadConfig();
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onBlockBreakEvent(BlockBreakEvent event) {
        if (IGNORE_LOCATIONS.contains(event.getBlock().getLocation())) {
            IGNORE_LOCATIONS.remove(event.getBlock().getLocation());
            return;
        }

        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
        if (!hasTrench(item)) return;

        for (Block block : getNearbyBlocks(event.getBlock().getLocation())) {
            if (block.getLocation().equals(event.getBlock().getLocation())) {
                continue;
            }

            if (ALLOWED_ITEMS.contains(block.getType())) {
                IGNORE_LOCATIONS.add(block.getLocation());
                BlockBreakEvent e = new BlockBreakEvent(block, event.getPlayer());
                Bukkit.getPluginManager().callEvent(e);
                if (!e.isCancelled()) {
                    if (!hasSilkTouch(item)) {
                        dropExperience(block);
                    }
                    block.breakNaturally(item);
                }
            }
        }
    }

    public static void apply(ItemStack item, Player player) {
        if (!ENCHANTABLE_ITEMS.contains(item.getType())) {
            player.sendMessage(ChatColor.RED + "That item cannot be enchanted with Trench.");
            return;
        }

        if (hasTrench(item)) {
            player.sendMessage(ChatColor.RED + "That item is already enchanted with Trench.");
            return;
        }

        BEnchants.LOGGER.info("Enchanting item with trench...");
        NBTItem nbti = new NBTItem(item);
        nbti.setBoolean("trench", Boolean.TRUE);
        nbti.applyNBT(item);
        player.sendMessage(ChatColor.GREEN + "Successfully enchanted item with Trench.");
    }

    public static void remove(ItemStack item, Player player) {
        if (!hasTrench(item)) {
            player.sendMessage(ChatColor.RED + "That item is not enchanted with Trench.");
            return;
        }

        BEnchants.LOGGER.info("Removing trench enchantment from item...");
        NBTItem nbti = new NBTItem(item);
        nbti.setBoolean("trench", Boolean.FALSE);
        nbti.applyNBT(item);
        player.sendMessage(ChatColor.GREEN + "Successfully removed trench enchantment from item.");
    }

    public static boolean hasTrench(ItemStack item) {
        if (!ENCHANTABLE_ITEMS.contains(item.getType())) return false;
        NBTItem nbti = new NBTItem(item);
        if (!nbti.hasNBTData()) return false;
        return nbti.getBoolean("trench");
    }

    public static void loadConfig() {
        ENCHANTABLE_ITEMS.clear();

        ConfigurationSection trench = BEnchants.PLUGIN.getConfig().getConfigurationSection("trench");
        if (trench == null) {
            BEnchants.LOGGER.warning("Trench configuration not found!");
            return;
        }

        List<String> items = trench.getStringList("destroyable-items");

        if (items.size() == 0) {
            BEnchants.LOGGER.warning("No destroyable-items found in trench section of config.");
        }

        for (String item : items) {
            try {
                ALLOWED_ITEMS.add(Material.valueOf(item));
            } catch (Exception e) {
                BEnchants.LOGGER.warning("'" + item + "' is not a valid material name! Skipping this item.");
            }
        }

        List<String> enchantableItems = trench.getStringList("enchantable-items");

        if (items.size() == 0) {
            BEnchants.LOGGER.warning("No enchantable-items found in trench section of config.");
        }

        for (String item : enchantableItems) {
            try {
                ENCHANTABLE_ITEMS.add(Material.valueOf(item));
            } catch (Exception e) {
                BEnchants.LOGGER.warning("'" + item + "' is not a valid material name! Skipping this item.");
            }
        }
    }

    private void dropExperience(Block block) {
        int experience = switch (block.getType()) {
            case COAL_ORE -> getRandomNumber(0, 2);
            case NETHER_GOLD_ORE -> getRandomNumber(0, 1);
            case DIAMOND_ORE, EMERALD_ORE -> getRandomNumber(3, 7);
            case LAPIS_LAZULI, NETHER_QUARTZ_ORE -> getRandomNumber(2, 5);
            case REDSTONE_ORE -> getRandomNumber(1, 5);
            default -> 0;
        };

        if (experience > 0) {
            block.getWorld().spawn(block.getLocation(), ExperienceOrb.class).setExperience(5);
        }
    }

    private boolean hasSilkTouch(ItemStack itemStack) {
        return itemStack.getEnchantments().containsKey(Enchantment.SILK_TOUCH);
    }

    private static List<Block> getNearbyBlocks(Location location) {
        List<Block> blocks = new ArrayList<>();
        for (int x = location.getBlockX() - 1; x <= location.getBlockX() + 1; x++) {
            for (int y = location.getBlockY() - 1; y <= location.getBlockY() + 1; y++) {
                for (int z = location.getBlockZ() - 1; z <= location.getBlockZ() + 1; z++) {
                    blocks.add(location.getWorld().getBlockAt(x, y, z));
                }
            }
        }

        return blocks;
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
