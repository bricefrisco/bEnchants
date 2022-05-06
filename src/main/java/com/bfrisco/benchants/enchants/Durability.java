package com.bfrisco.benchants.enchants;

import com.bfrisco.benchants.BEnchants;
import com.bfrisco.benchants.utils.ItemInfo;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Durability implements Listener {
    public static final Set<Material> ENCHANTABLE_ITEMS = new HashSet<>();

    public Durability() {
        loadConfig();
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onPlayerItemDamageEvent(PlayerItemDamageEvent event) {

        Player player = event.getPlayer();
        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
        if (!ItemInfo.isTitanTool(item)) return;
        if (!ItemInfo.isActiveImbued(item)) return;
        event.setCancelled(Boolean.TRUE);
    }

    public static void loadConfig() {
        ENCHANTABLE_ITEMS.clear();

        ConfigurationSection durability = BEnchants.PLUGIN.getConfig().getConfigurationSection("durability");
        if (durability == null) {
            BEnchants.LOGGER.warning("Durability configuration not found!");
            return;
        }

        List<String> items = durability.getStringList("enchantable-items");

        if (items.size() == 0) {
            BEnchants.LOGGER.warning("No enchantable-items found in durability section of config.");
        }

        for (String item : items) {
            try {
                ENCHANTABLE_ITEMS.add(Material.valueOf(item));
            } catch (Exception e) {
                BEnchants.LOGGER.warning("'" + item + "' is not a valid material name! Skipping this item.");
            }
        }
    }

}
