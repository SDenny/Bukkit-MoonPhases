package com.minecraftport.moonphases;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class main extends JavaPlugin {

    public static String endMsg;
    public static String curPhase;
    public static String sunriseMsg;
    public static double XPXtra;
    public static double DmgMult;
    public final everythingListener evl = new everythingListener(this);
    public final String tag = "[MoonPhases]";
    public String mainworld = getConfig().getString("mainworld");

    @Override
    public void onDisable() {
        PluginDescriptionFile config = this.getDescription();
        String tag = "[MoonPhases]";
        System.out.println(tag + " MoonPhases v." + config.getVersion() + " has been disabled!");
    }

    @Override
    public void onEnable() {
        PluginDescriptionFile config = this.getDescription();
        getConfig().options().copyDefaults(true);
        saveConfig();

        PluginManager pm = getServer().getPluginManager();
        System.out.println(tag + " MoonPhases v." + config.getVersion() + " has been enabled!");
        pm.registerEvents(evl, this);

        if (Bukkit.getWorld(mainworld) != null) {
            this.getServer().getScheduler().scheduleSyncRepeatingTask(
                    this,
                    new Runnable() {
                        @Override
                        public void run() {
                            double day = getServer().getWorld(mainworld).getFullTime() / 24000;
                            int phase = (int) (day % 8);

                            moonSettings.getPhaseInfo(phase);

                            if (getServer().getWorld(mainworld).getTime() >= 13000 && getServer().getWorld(mainworld).getTime() <= 13019) {
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    p.sendMessage(ChatColor.DARK_GRAY + "It is now night and the moon is out.");
                                    sendStatusMsg(p);
                                    Location ploc = p.getLocation();
                                    if(phase == 0) {
                                        p.playSound(ploc, Sound.WOLF_HOWL, 0.5f, 0.5f);
                                    }
                                }
                            } else if (getServer().getWorld(mainworld).getTime() >= 22980 && getServer().getWorld(mainworld).getTime() <= 22999) {
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    p.sendMessage(ChatColor.GOLD + "The sun has risen" + sunriseMsg);
                                }
                            }

                            if (getConfig().getBoolean("fullMoonAngryWolves")) {

                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    double radius = 16D;
                                    Location loc = p.getLocation();
                                    List<Entity> near = loc.getWorld().getEntities();
                                    for (Entity e : near) {
                                        if(e.getLocation().distance(loc) <= radius) {
                                            if (e instanceof Wolf) {
                                                if (phase == 0 && !((Wolf) e).isTamed() && isNight()) {
                                                    ((Wolf) e).setAngry(true);
                                                    ((Wolf) e).setTarget(p);
                                                    ((Wolf) e).setMaxHealth(10);
                                                } else if(((Wolf) e).getMaxHealth() == 10) {
                                                    ((Wolf) e).setAngry(false);
                                                    ((Wolf) e).setTarget(null);
                                                    ((Wolf) e).setMaxHealth(8);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }, 20, 20
            );
        } else {
            System.out.println(tag + "The world set in the config, \"" + mainworld + "\", does not exist! MoonPhases WILL NOT WORK.");
            getPluginLoader().disablePlugin(this);
        }
    }

    private boolean isNight() {
        return (getServer().getWorld(mainworld).getTime() >= 13000 && getServer().getWorld(mainworld).getTime() <= 23000);
    }

    public void sendStatusMsg(Player p) {
        if (isNight()) {
            p.sendMessage(ChatColor.GRAY + "Current moon phase is " + curPhase + ChatColor.GRAY);
            if (main.DmgMult != 1) {
                if (main.XPXtra != 1) {
                    endMsg = "" + ChatColor.RESET + ChatColor.YELLOW + " until sunrise and drop " + ChatColor.BLUE + ChatColor.ITALIC + ChatColor.BOLD + XPXtra + "x XP!";
                } else {
                    endMsg = "" + ChatColor.RESET + ChatColor.YELLOW + " until sunrise!";
                }
                p.sendMessage(ChatColor.YELLOW + "Mobs are also " + ChatColor.GOLD + ChatColor.ITALIC + ChatColor.BOLD + String.valueOf(DmgMult) + "x stronger" + endMsg);
            }
        } else {
            p.sendMessage(ChatColor.GRAY + "Tonight's moon phase will be " + ChatColor.DARK_PURPLE + curPhase + ChatColor.GRAY);
        }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player && commandLabel.equalsIgnoreCase("phase")) {
            Player p = (Player) sender;
            sendStatusMsg(p);
        } else {
            sender.sendMessage(tag + " You must be a player to use this command!");
        }
        return false;
    }
}