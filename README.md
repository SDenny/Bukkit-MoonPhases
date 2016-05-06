# Bukkit-MoonPhases
Tells the player what phase the moon is in on login and when it turns night. Also causes mob damage and xp rates to change depending on the phase of the moon.

*Last tested on CraftBukkit 1.9.2-R0.1*

All 8 phases of the moon have the ability to cause different effects on the players and mobs.
Phase 0 (Full/Blood Moon): Mobs do 2x damage and drop 2.5x XP
Phase 1 to Phase 5: Mobs do 1x Damage and drop 1x XP
Phase 6: Mobs do 1.2x Damage and drop 1.2x XP
Phase 7: Mobs do 1.5x Damage and drop 1.5x XP

Each night that is configured to cause more damage by mobs, player damage is reduced to 1/DamageMultiplier as well, so watch out for that!

#Configuration:
```yml
#Name of the world that the plugin should be active in.
mainWorld: world
fullMoonAngryWolves: true

#Prevents mob spawners from spawning any mobs when the XP multiplier is greater than 1.
#This also allows more mobs to spawn on the surface at night
preventSpawnerXP: true

#Enable Charged Creepers spawn (1 per "chargedChance") when damage multiplier is greater than 1.
#Set chargedChance to 1 for all charged creepers.
enableChargedCreepers: true
chargedChance: 10

###Phase Configuration###

#Master Switch to enable or disable the XP and damage multipliers based on the phase of the moon.
#Essentially disables most functionality of this plugin
enablePhaseModifiers: true

#Night start and end time (in ticks.) Default, nightStarts: 13000; nightEnds: 23000) Must be in the range of [0,24000]
nightStarts: 13000
nightEnds: 23000

#Set Phase multipliers
#Phase0 is the full moon and phase 4 is the new moon
phase0:
  phaseDmg: 2
  phaseXP: 2.5

phase1:
  phaseDmg: 1
  phaseXP: 1

phase2:
  phaseDmg: 1
  phaseXP: 1

phase3:
  phaseDmg: 1
  phaseXP: 1

#New Moon
phase4:
  phaseDmg: 1
  phaseXP: 1

phase5:
  phaseDmg: 1
  phaseXP: 1

phase6:
  phaseDmg: 1.2
  phaseXP: 1.2

phase7:
  phaseDmg: 1.5
  phaseXP: 1.5
```
