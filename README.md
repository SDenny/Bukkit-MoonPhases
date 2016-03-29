# Bukkit-MoonPhases
Tells the player what phase the moon is in on login and when it turns night. Also causes mob damage and xp rates to change depending on the phase of the moon.

*Tested on Bukkit 1.7.9-R0.3*

All 8 phases of the moon have the ability to cause different effects on the players and mobs.
Phase 0 (Full/Blood Moon): Mobs do 2x damage and drop 2.5x XP
Phase 1 to Phase 5: Mobs do 1x Damage and drop 1x XP
Phase 6: Mobs do 1.2x Damage and drop 1.2x XP
Phase 7: Mobs do 1.5x Damage and drop 1.5x XP

Each night that is configured to cause more damage by mobs, player damage is reduced to 1/DamageMultiplier as well, so watch out for that!

#Configuration:
Ensure that in config.yml, your main world is set to whatever world you want the plugin to be active in. Currently does not support working in multiple worlds.
