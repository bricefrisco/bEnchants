package com.bfrisco.benchants.utils;

import com.bfrisco.benchants.BEnchants;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class BEnchantEffects {



    public void enableEffect(Player player) {

        player.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE,player.getEyeLocation(),100);
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE,10, 1);

    }
    public void disableEffect(Player player) {

        player.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE,player.getEyeLocation(),100);
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE,10, 1);

    }

    public void depletedChargeEffect(Player player) {

        player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, player.getEyeLocation(), 100);
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_CONDUIT_DEACTIVATE, 10, 1);

    }


}
