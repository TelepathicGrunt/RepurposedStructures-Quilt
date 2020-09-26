package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.world.features.*;
import com.telepathicgrunt.repurposedstructures.world.structures.*;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.RSMineshaftPieces;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePieces;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class RSFeatures {

    //Static instance of our structure so we can reference it and add it to biomes easily.
    public static Feature<DefaultFeatureConfig> BADLANDS_DUNGEONS = new DungeonBadlands();
    public static Feature<DefaultFeatureConfig> DARK_FOREST_DUNGEONS = new DungeonDarkForest();
    public static Feature<DefaultFeatureConfig> DESERT_DUNGEONS = new DungeonDesert();
    public static Feature<DefaultFeatureConfig> END_DUNGEONS = new DungeonEnd();
    public static Feature<DefaultFeatureConfig> NETHER_DUNGEONS = new DungeonNether();
    public static Feature<DefaultFeatureConfig> SNOW_DUNGEONS = new DungeonSnow();
    public static Feature<DefaultFeatureConfig> SWAMP_DUNGEONS = new DungeonSwamp();
    public static Feature<DefaultFeatureConfig> MUSHROOM_DUNGEONS = new DungeonMushroom();
    public static Feature<DefaultFeatureConfig> JUNGLE_DUNGEONS = new DungeonJungle();
    public static Feature<DefaultFeatureConfig> OCEAN_DUNGEONS = new DungeonOcean();

    public static Feature<DefaultFeatureConfig> BADLANDS_WELL = new WellBadlands();
    public static Feature<DefaultFeatureConfig> NETHER_WELL = new WellNether();
    public static Feature<DefaultFeatureConfig> SNOW_WELL = new WellSnow();
    public static Feature<DefaultFeatureConfig> MOSSY_STONE_WELL = new WellMossyStone();
    public static Feature<DefaultFeatureConfig> FOREST_WELL = new WellForest();

    public static Feature<DefaultFeatureConfig> BOULDER_GIANT = new BoulderGiant();
    public static Feature<DefaultFeatureConfig> BOULDER_TINY = new BoulderTiny();
    public static Feature<TreeFeatureConfig> HORNED_SWAMP_TREE = new TreeSwampHorned();
    public static Feature<DefaultFeatureConfig> SHORT_VINES = new VinesShort();
    public static Feature<DefaultFeatureConfig> SWAMP_VILLAGE_VINES = new SwampVillageVines();
    public static Feature<DefaultFeatureConfig> JUNGLE_STRUCTURES_VINES = new JungleStructuresVines();
    public static Feature<DefaultFeatureConfig> FORTRESS_BREAKAGE = new FortressBreakage();
    public static Feature<DefaultFeatureConfig> STRONGHOLD_CHAINS = new StrongholdChains();

    public static void registerFeatures() {
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "dungeons_badlands", BADLANDS_DUNGEONS);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "dungeons_dark_forest", DARK_FOREST_DUNGEONS);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "dungeons_desert", DESERT_DUNGEONS);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "dungeons_end", END_DUNGEONS);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "dungeons_nether", NETHER_DUNGEONS);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "dungeons_snow", SNOW_DUNGEONS);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "dungeons_swamp", SWAMP_DUNGEONS);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "dungeons_mushroom", MUSHROOM_DUNGEONS);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "dungeons_jungle", JUNGLE_DUNGEONS);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "dungeons_ocean", OCEAN_DUNGEONS);

        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "well_badlands", BADLANDS_WELL);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "well_nether", NETHER_WELL);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "well_snow", SNOW_WELL);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "well_mossy_stone", MOSSY_STONE_WELL);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "well_forest", FOREST_WELL);

        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "boulder_giant", BOULDER_GIANT);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "boulder_tiny", BOULDER_TINY);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "horned_swamp_tree", HORNED_SWAMP_TREE);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "short_vines", SHORT_VINES);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "swamp_village_vines", SWAMP_VILLAGE_VINES);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "jungle_structures_vines", JUNGLE_STRUCTURES_VINES);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "fortress_breakage", FORTRESS_BREAKAGE);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "stronghold_chains", STRONGHOLD_CHAINS);
    }
}
