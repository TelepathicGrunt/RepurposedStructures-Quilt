package com.telepathicgrunt.repurposedstructures.configs;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;


@Config(name = "Shipwrecks")
public class RSShipwrecksConfig implements ConfigData {

    @ConfigEntry.Gui.CollapsibleObject
    public RSShipwrecksConfig.Blacklists blacklist = new RSShipwrecksConfig.Blacklists();

    @ConfigEntry.Gui.CollapsibleObject
    public RSShipwrecksConfig.Spawnrates spawnrate = new RSShipwrecksConfig.Spawnrates();

    public static class Blacklists {
        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add the ID/resource location of the biome you don't want"
                +"\nRS's shipwrecks to spawn in. Separate each ID with a comma ,"
                +"\n"
                +"\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
        public String blacklistedShipwreckBiomes = "";

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add End Shipwreck to modded End biomes.")
        public boolean addEndShipwreckToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Add Nether Bricks Shipwreck to modded non-warped or non-crimson Nether biome.")
        public boolean addNetherBricksShipwreckToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Add Crimson Shipwreck to modded Crimson-named Nether biome.")
        public boolean addCrimsonShipwreckToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Add Warped Shipwreck to modded Warped-named Nether biome.")
        public boolean addWarpedShipwreckToModdedBiomes = true;
    }

    public static class Spawnrates {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are End Shipwreck in End Highlands biomes."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int endShipwreckSpawnrate = 15;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Nether Bricks Shipwreck in any non-warped or non-crimson Nether biome."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int netherBricksShipwreckSpawnrate = 51;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Crimson Shipwreck in Crimson Nether biome."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int crimsonShipwreckSpawnrate = 41;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Warped Shipwreck in Warped Nether biome."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int warpedShipwreckSpawnrate = 41;
    }
}