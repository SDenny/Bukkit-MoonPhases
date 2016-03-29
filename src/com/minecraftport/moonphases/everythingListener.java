package com.minecraftport.moonphases;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
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
    @EventHandler()
    public void mobDmgLstn(EntityDamageByEntityEvent event) {
        if (event.getEntity().getWorld().getTime() >= 13000 && event.getEntity().getWorld().getTime() <= 23000) {
            if (event.getDamager() instanceof Monster) {
                event.setDamage(event.getDamage() * main.DmgMult);
            } else if (event.getEntity() instanceof Monster) {
                event.setDamage(event.getDamage() / main.DmgMult);
            }
        }
    }

    //Checks for the death of mobs to apply extra  xp
    @EventHandler
    public void mobkillLst(EntityDeathEvent event) {
        if (event.getEntity().getKiller() instanceof Player && event.getEntity().getType() != EntityType.PLAYER) {
            int xpmult = (int) (event.getDroppedExp() * main.XPXtra);
            event.setDroppedExp(xpmult);
        }
    }

    //Disable Spawners during xp increasing moons to allow more mobs to spawn on the surface and in caves
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onCreatureSpawnEvent(CreatureSpawnEvent event) {
        if (main.DmgMult != 1 && event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER) {
            event.setCancelled(true);
        }
    }

    //Sends the player a warning message if the moon is out.
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        plugin.sendStatusMsg(p);
    }
}