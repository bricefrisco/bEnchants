package com.bfrisco.benchants.utils;

import com.bfrisco.benchants.BEnchants;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class HoverBootParticles {

    private int taskID;
    private final Player player;

    public HoverBootParticles(Player player){
        this.player = player;
    }

    public void startTotem() {
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(BEnchants.getPlugin(BEnchants.class), new Runnable() {
            double var = 0;
            Location loc, first, second;
            ParticlesData particle = new ParticlesData(player.getUniqueId());

            @Override
            public void run() {
                if (!particle.hasID()){
                    particle.setID(taskID);
                }
                var += Math.PI / 16;
                loc = player.getLocation();
                first = loc.clone().add(Math.cos(var), Math.sin(var) - 1,Math.sin(var));
                second = loc.clone().add(Math.cos(var + Math.PI),Math.sin(var) -1,Math.sin(var+Math.PI));
                player.getWorld().spawnParticle(Particle.TOTEM, first,0);
                player.getWorld().spawnParticle(Particle.TOTEM, second,0);
            }
        }, 0,1);
    }
}