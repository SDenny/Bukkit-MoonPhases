package com.minecraftport.moonphases;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {

    public static String endMsg;
    public final everythingListener evl = new everythingListener(this);
    public final String tag = "[MoonPhases]";

    public static String curPhase;
    public static String sunriseMsg;
    public static double XPXtra;
    public static double DmgMult;

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
                                }
                            } else if (getServer().getWorld(mainworld).getTime() >= 22980 && getServer().getWorld(mainworld).getTime() <= 22999) {
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    p.sendMessage(ChatColor.GOLD + "The sun has risen" + sunriseMsg);
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

    public void sendStatusMsg(Player p){
        if (getServer().getWorld(mainworld).getTime() >= 13000 && getServer().getWorld(mainworld).getTime() <= 23000) {
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
        }else{
            sender.sendMessage(tag + " You must be a player to use this command!");
        }
        return false;
    }
}