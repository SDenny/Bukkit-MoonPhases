package com.minecraftport.moonphases;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class everythingListener implements Listener {

    public static main plugin;

    public everythingListener(main instance) {
        plugin = instance;
    }

    //Give damage increase from mobs and decrease to mobs
    @EventHandler
    public void mobDmgLstn(EntityDamageByEntityEvent event) {
        if(plugin.getConfig().getBoolean("enablePhaseModifiers")) {
            if (plugin.isNight()) {
                if (event.getDamager() instanceof Monster) {
                    event.setDamage(event.getDamage() * main.DmgMult);
                } else if (event.getEntity() instanceof Monster) {
                    event.setDamage(event.getDamage() / main.DmgMult);
                }
            }
        }
    }

    //Checks for the death of mobs to apply extra  xp
    @EventHandler
    public void mobkillLst(EntityDeathEvent event) {
        if (event.getEntity().getKiller() instanceof Player && event.getEntity().getType() != EntityType.PLAYER) {//Ensure entity that is killed is not a player
            int xpmult = (int) (event.getDroppedExp() * main.XPMult);
            event.setDroppedExp(xpmult);
        }
    }

    //Disable Spawners during xp-increasing moons to allow more mobs to spawn on the surface and in caves. Also set some creepers to be charged.
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onCreatureSpawnEvent(CreatureSpawnEvent event) {
        if (plugin.getConfig().getBoolean("preventXPFarmExploit") && main.XPMult > 1 && event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER) {
            event.setCancelled(true);
        }
        if(event.getEntity() instanceof Creeper && plugin.getConfig().getBoolean("enableChargedCreepers") && main.DmgMult > 1){
            int randNumC = (int) (Math.random()*plugin.getConfig().getInt("chargedChance"));
            Creeper creeper = (Creeper) event.getEntity();
            if(randNumC == 1){
                creeper.setPowered(true);
            }
        }
    }

    //Sends the player a warning message if the moon is out.
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        plugin.sendStatusMsg(p);
    }
}