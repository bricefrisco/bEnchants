package com.bfrisco.benchants;

import com.bfrisco.benchants.commands.Commands;
import com.bfrisco.benchants.commands.PlayerCommands;

import com.bfrisco.benchants.enchants.*;
import com.bfrisco.benchants.enchants.TitanAxe.TitanAxe;
import com.bfrisco.benchants.enchants.TitanRod.TitanRod;
import com.bfrisco.benchants.enchants.TitanShovel.TitanShovel;
import com.bfrisco.benchants.enchants.TitanShovel.ToggleChargedShovel;
import com.bfrisco.benchants.enchants.TitanShovel.ToggleImbuedShovel;
import com.bfrisco.benchants.misc.SuperMan;
import com.bfrisco.benchants.utils.ChargeManagement;
import com.bfrisco.benchants.utils.ToggleImbuedItem;
import com.bfrisco.benchants.utils.ToggleChargedItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public class BEnchants extends JavaPlugin {
    public static Logger LOGGER;
    public static FileConfiguration CONFIG;
    public static Plugin PLUGIN;

    public BEnchants() {
        PLUGIN = this;
        LOGGER = getLogger();
        CONFIG = getConfig();
    }

    public static BEnchants getInstance() {
        return getPlugin(BEnchants.class);
    }

    @Override
    public void onEnable() {
        this.getConfig().options().copyDefaults();
        saveDefaultConfig();
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "bEnchants  E N A B L E D  with emphasis!!!!!!");
        Bukkit.getPluginManager().registerEvents(new Trench(), this);
        Bukkit.getPluginManager().registerEvents(new TitanAxe(), this);
        Bukkit.getPluginManager().registerEvents(new TitanShovel(), this);
        Bukkit.getPluginManager().registerEvents(new ToggleImbuedShovel(), this);
        Bukkit.getPluginManager().registerEvents(new ToggleChargedShovel(), this);
        Bukkit.getPluginManager().registerEvents(new Durability(), this);
        Bukkit.getPluginManager().registerEvents(new ToggleImbuedItem(), this);
        Bukkit.getPluginManager().registerEvents(new ToggleChargedItem(), this);
        Bukkit.getPluginManager().registerEvents(new TitanRod(),this);
        Bukkit.getPluginManager().registerEvents(new ChargeManagement(),this);
        Bukkit.getPluginManager().registerEvents(new SuperMan(),this);

/*        Bukkit.getPluginManager().registerEvents(new HoverBoots(),this);*/

        Objects.requireNonNull(getCommand("benchants")).setExecutor(new Commands());
        Objects.requireNonNull(getCommand("ancient")).setExecutor(new PlayerCommands());

    }
}
