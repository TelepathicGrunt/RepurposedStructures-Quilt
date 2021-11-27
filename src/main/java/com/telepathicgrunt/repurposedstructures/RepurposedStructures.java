package com.telepathicgrunt.repurposedstructures;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.telepathicgrunt.repurposedstructures.biomeinjection.Dungeons;
import com.telepathicgrunt.repurposedstructures.biomeinjection.TemporaryBiomeInjection;
import com.telepathicgrunt.repurposedstructures.biomeinjection.Wells;
import com.telepathicgrunt.repurposedstructures.configs.RSAllConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSAllowDisallowOmegaConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSNaturalMobSpawningOmegaConfig;
import com.telepathicgrunt.repurposedstructures.misc.BiomeDimensionAllowDisallow;
import com.telepathicgrunt.repurposedstructures.misc.MobMapTrades;
import com.telepathicgrunt.repurposedstructures.misc.MobSpawnerManager;
import com.telepathicgrunt.repurposedstructures.misc.MobSpawningOverTime;
import com.telepathicgrunt.repurposedstructures.misc.PoolAdditionMerger;
import com.telepathicgrunt.repurposedstructures.mixin.structures.StructuresConfigAccessor;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSPlacements;
import com.telepathicgrunt.repurposedstructures.modinit.RSPredicates;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePiecesBehavior;
import draylar.omegaconfig.OmegaConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


public class RepurposedStructures implements ModInitializer, DedicatedServerModInitializer, ClientModInitializer {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "repurposed_structures";
    public static MobSpawnerManager mobSpawnerManager = new MobSpawnerManager();

	public static RSAllConfig RSAllConfig = null;
    public static final RSAllowDisallowOmegaConfig omegaBiomeDimConfig = OmegaConfig.register(RSAllowDisallowOmegaConfig.class);
    public static final RSNaturalMobSpawningOmegaConfig omegaMobSpawnConfig = OmegaConfig.register(RSNaturalMobSpawningOmegaConfig.class);
    public static boolean initialized = false;


    @Override
    public void onInitialize() {
        AutoConfig.register(RSAllConfig.class, JanksonConfigSerializer::new);
        RSAllConfig = AutoConfig.getConfigHolder(RSAllConfig.class).getConfig();

        RSPlacements.registerPlacements();
        RSFeatures.registerFeatures();
        RSProcessors.registerProcessors();
        RSPredicates.registerPredicates();
        RSStructures.registerStructures();
        RSStructureTagMap.setupTags();
        RSConfiguredFeatures.registerConfiguredFeatures();
        RSConfiguredFeatures.registerPlacedFeatures();
        RSConfiguredStructures.registerConfiguredStructures();

        BiomeDimensionAllowDisallow.setupAllowDisallowMaps();
        MobSpawningOverTime.setupMobSpawningMaps();
        setupBiomeModifications();
        allowStructureSpawningPerDimension();
        PoolAdditionMerger.mergeAdditionPools();
        StructurePiecesBehavior.init();
        MobMapTrades.addMapTrades();
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(RepurposedStructures.mobSpawnerManager);

        initialized = true;
    }

    // These are for when we want to add compat with other mods but uses their configs/codes that isn't read in the regular onInitialize method
    @Override
    public void onInitializeServer() {
        //StructurePiecesBehavior.addDelayedRequiredPieces();
    }

    @Override
    public void onInitializeClient() {
        //StructurePiecesBehavior.addDelayedRequiredPieces();
    }

    public static void allowStructureSpawningPerDimension() {
        // This is for making sure our ServerWorldEvents.LOAD event always fires after Fabric API's so our changes don't get overwritten
        ResourceLocation runAfterFabricAPIPhase = new ResourceLocation(RepurposedStructures.MODID, "run_after_fabric_api");
        ServerWorldEvents.LOAD.addPhaseOrdering(Event.DEFAULT_PHASE, runAfterFabricAPIPhase);

        // Controls the dimension blacklisting
        ServerWorldEvents.LOAD.register(runAfterFabricAPIPhase, (MinecraftServer minecraftServer, ServerLevel serverWorld) -> {
            // We will need this a lot lol
            StructureSettings worldStructureSettings = serverWorld.getChunkSource().getGenerator().getSettings();

            //////////// BIOME BASED STRUCTURE SPAWNING ////////////
            /*
             * NOTE: BiomeModifications from Fabric API does not work in 1.18 currently.
             * Instead, we will use the below to add our structure to overworld biomes.
             * Remember, this is temporary until Fabric API finds a better solution for adding structures to biomes.
             */

            // Grab the map that holds what ConfigureStructures a structure has and what biomes it can spawn in.
            // We will inject our structures into that map/multimap
            ImmutableMap.Builder<StructureFeature<?>, ImmutableMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> tempStructureToMultiMap = ImmutableMap.builder();
            ((StructuresConfigAccessor) worldStructureSettings).getConfiguredStructures().entrySet().forEach(tempStructureToMultiMap::put);
            TemporaryBiomeInjection.addStructureToBiomes(tempStructureToMultiMap);
            ((StructuresConfigAccessor) worldStructureSettings).setConfiguredStructures(tempStructureToMultiMap.build());


            //////////// DIMENSION BASED STRUCTURE SPAWNING ////////////

            //add our structure spacing to all chunkgenerators including modded one and datapack ones.
            // Need temp map as some mods use custom chunk generators with immutable maps in themselves.
            Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(worldStructureSettings.structureConfig());

            // make absolutely sure superflat dimension cannot spawn RS structures
            if (serverWorld.getChunkSource().getGenerator() instanceof FlatLevelSource && serverWorld.dimension().equals(Level.OVERWORLD)) {
                tempMap.keySet().removeAll(RSStructures.RS_STRUCTURES.keySet());
            }
            // Not superflat overworld. Do normal behavior now
            else{
                for(Map.Entry<StructureFeature<?>, StructureFeatureConfiguration> structureFeatureEntry : RSStructures.RS_STRUCTURES.entrySet()){
                    boolean isWorldBlacklisted = GeneralUtils.isBlacklistedForWorld(serverWorld, Registry.STRUCTURE_FEATURE.getKey(structureFeatureEntry.getKey()));
                    if (isWorldBlacklisted){
                        // make absolutely sure dimension cannot spawn the RS structure
                        tempMap.remove(structureFeatureEntry.getKey());
                    }
                    else {
                        // make absolutely sure dimension can spawn the RS structure
                        tempMap.putIfAbsent(structureFeatureEntry.getKey(), structureFeatureEntry.getValue());
                    }
                }
            }
            ((StructuresConfigAccessor) worldStructureSettings).repurposedstructures_setStructureConfig(tempMap);
        });
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BIOME MODIFICATION API //
    public static void setupBiomeModifications() {
        Dungeons.addDungeons();
        Wells.addWells();

        // Biome Modification API from Fabric API does not work with structures right now.
//        Mineshafts.addMineshafts();
//        Strongholds.addStrongholds();
//        Outposts.addOutposts();
//        Shipwrecks.addShipwrecks();
//        Fortresses.addJungleFortress();
//        Temples.addTemples();
//        Pyramids.addPyramids();
//        Igloos.addIgloos();
//        Villages.addVillages();
//        RuinedPortals.addRuinedPortals();
//        Ruins.addRuins();
//        Cities.addCities();
//        Mansions.addMansions();
//        WitchHuts.addWitchHuts();
//        Bastions.addBastions();
    }
}
