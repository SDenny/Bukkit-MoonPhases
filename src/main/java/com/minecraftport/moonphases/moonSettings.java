package com.minecraftport.moonphases;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class moonSettings {

    //Phase Descriptions
    public static String phaseTxt[]={
            ChatColor.RED + "" + ChatColor.BOLD + "Blood Moon",
            ChatColor.DARK_PURPLE + "Waning Gibbous",
            ChatColor.DARK_PURPLE + "Third Quarter",
            ChatColor.DARK_PURPLE + "Waning Crescent",
            ChatColor.DARK_PURPLE + "New Moon",
            ChatColor.DARK_PURPLE + "Waxing Crescent",
            ChatColor.RED + "First Quarter",
            ChatColor.RED + "Waxing Gibbous",
    };

    //Phase damage multipliers
    public static double phaseDmg[]={
            2.0,//Phase 0 - full moon
            1.0,//phase 1
            1.0,//phase 2
            1.0,//phase 3
            1.0,//phase 4 - new moon
            1.0,//phase 5
            1.2,//phase 6
            1.5//phase 7
    };

    //Phase XP multipliers
    public static double phaseXP[]={
            2.5,//Phase 0 - full moon
            1.0,//phase 1
            1.0,//phase 2
            1.0,//phase 3
            1.0,//phase 4 - new moon
            1.0,//phase 5
            1.2,//phase 6
            1.5//phase 7
    };

    public static void getPhaseInfo(int i) {
        //Gets all of the information associated with a particular moon phase
        main.curPhase = phaseTxt[i];
        main.DmgMult = phaseDmg[i];
        main.XPXtra = phaseXP[i];

        if (main.DmgMult != 1.0) {
            main.sunriseMsg = " and mobs are back to normal!";
        }else{
            main.sunriseMsg = "!";
        }

    }
}