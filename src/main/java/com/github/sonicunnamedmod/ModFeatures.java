package com.github.sonicunnamedmod;

import com.github.sonicunnamedmod.world.gen.feature.GreenHillPalmFeature;
import com.github.sonicunnamedmod.world.gen.feature.GreenHillTotemFeature;
import com.github.sonicunnamedmod.world.gen.feature.GreenHillVegetationFeature;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlacedFeature;

public class ModFeatures {

    public static final Feature<DefaultFeatureConfig> GREEN_HILL_PALM;
    public static final Feature<DefaultFeatureConfig> GREEN_HILL_TOTEM;
    public static final Feature<DefaultFeatureConfig> GREEN_HILL_VEGETATION;

    public static final RegistryKey<PlacedFeature> GREEN_HILL_PALM_PLACED_KEY =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE,
                    Identifier.of(SonicUnnamedMod.MOD_ID, "green_hill_palm_placed"));

    public static final RegistryKey<PlacedFeature> GREEN_HILL_TOTEM_PLACED_KEY =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE,
                    Identifier.of(SonicUnnamedMod.MOD_ID, "green_hill_totem_placed"));

    public static final RegistryKey<PlacedFeature> GREEN_HILL_VEGETATION_PLACED_KEY =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE,
                    Identifier.of(SonicUnnamedMod.MOD_ID, "green_hill_vegetation_placed"));

    static {
        GREEN_HILL_PALM = Registry.register(
                Registries.FEATURE,
                Identifier.of(SonicUnnamedMod.MOD_ID, "green_hill_palm_feature"),
                new GreenHillPalmFeature(DefaultFeatureConfig.CODEC)
        );

        GREEN_HILL_TOTEM = Registry.register(
                Registries.FEATURE,
                Identifier.of(SonicUnnamedMod.MOD_ID, "green_hill_totem_feature"),
                new GreenHillTotemFeature(DefaultFeatureConfig.CODEC)
        );

        GREEN_HILL_VEGETATION = Registry.register(
                Registries.FEATURE,
                Identifier.of(SonicUnnamedMod.MOD_ID, "green_hill_vegetation_feature"),
                new GreenHillVegetationFeature(DefaultFeatureConfig.CODEC)
        );
    }

    public static void registerAll() {
        addFeatureToBiome(BiomeKeys.PLAINS, GREEN_HILL_PALM_PLACED_KEY);
        addFeatureToBiome(BiomeKeys.PLAINS, GREEN_HILL_TOTEM_PLACED_KEY);
        addFeatureToBiome(BiomeKeys.PLAINS, GREEN_HILL_VEGETATION_PLACED_KEY);

        addFeatureToBiome(BiomeKeys.SUNFLOWER_PLAINS, GREEN_HILL_PALM_PLACED_KEY);
        addFeatureToBiome(BiomeKeys.SUNFLOWER_PLAINS, GREEN_HILL_TOTEM_PLACED_KEY);
        addFeatureToBiome(BiomeKeys.SUNFLOWER_PLAINS, GREEN_HILL_VEGETATION_PLACED_KEY);

        addFeatureToBiome(BiomeKeys.FOREST, GREEN_HILL_PALM_PLACED_KEY);
        addFeatureToBiome(BiomeKeys.FOREST, GREEN_HILL_TOTEM_PLACED_KEY);
        addFeatureToBiome(BiomeKeys.FOREST, GREEN_HILL_VEGETATION_PLACED_KEY);

        addFeatureToBiome(BiomeKeys.FLOWER_FOREST, GREEN_HILL_PALM_PLACED_KEY);
        addFeatureToBiome(BiomeKeys.FLOWER_FOREST, GREEN_HILL_TOTEM_PLACED_KEY);
        addFeatureToBiome(BiomeKeys.FLOWER_FOREST, GREEN_HILL_VEGETATION_PLACED_KEY);
    }

    private static void addFeatureToBiome(RegistryKey<net.minecraft.world.biome.Biome> biomeKey, RegistryKey<PlacedFeature> featureKey) {
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(biomeKey),
                GenerationStep.Feature.VEGETAL_DECORATION,
                featureKey
        );
    }
}