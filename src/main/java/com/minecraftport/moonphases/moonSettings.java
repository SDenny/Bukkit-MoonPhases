package com.minecraftport.moonphases;

import org.bukkit.ChatColor;

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
            everythingListener.plugin.getConfig().getDouble("phase0.phaseDmg"),//Phase 0 - full moon
            everythingListener.plugin.getConfig().getDouble("phase1.phaseDmg"),//phase 1
            everythingListener.plugin.getConfig().getDouble("phase2.phaseDmg"),//phase 2
            everythingListener.plugin.getConfig().getDouble("phase3.phaseDmg"),//phase 3
            everythingListener.plugin.getConfig().getDouble("phase4.phaseDmg"),//phase 4 - new moon
            everythingListener.plugin.getConfig().getDouble("phase5.phaseDmg"),//phase 5
            everythingListener.plugin.getConfig().getDouble("phase6.phaseDmg"),//phase 6
            everythingListener.plugin.getConfig().getDouble("phase7.phaseDmg")//phase 7
    };

    //Phase XP multipliers
    public static double phaseXP[]={
            everythingListener.plugin.getConfig().getDouble("phase0.phaseXP"),//Phase 0 - full moon
            everythingListener.plugin.getConfig().getDouble("phase1.phaseXP"),//phase 1
            everythingListener.plugin.getConfig().getDouble("phase2.phaseXP"),//phase 2
            everythingListener.plugin.getConfig().getDouble("phase3.phaseXP"),//phase 3
            everythingListener.plugin.getConfig().getDouble("phase4.phaseXP"),//phase 4 - new moon
            everythingListener.plugin.getConfig().getDouble("phase5.phaseXP"),//phase 5
            everythingListener.plugin.getConfig().getDouble("phase6.phaseXP"),//phase 6
            everythingListener.plugin.getConfig().getDouble("phase7.phaseXP")//phase 7
    };

    public static void getPhaseInfo(int i) {
        //Gets all of the information associated with a particular moon phase
        main.curPhase = phaseTxt[i];
        main.DmgMult = phaseDmg[i];
        main.XPMult = phaseXP[i];

        if (main.DmgMult != 1.0) {
            main.sunriseMsg = " and mobs are back to normal!";
        }else{
            main.sunriseMsg = "!";
        }

    }
}