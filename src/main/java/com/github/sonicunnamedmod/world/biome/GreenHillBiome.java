package com.github.sonicunnamedmod.world.biome;

import com.github.sonicunnamedmod.SonicUnnamedMod;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;

public class GreenHillBiome {

    public static final RegistryKey<Biome> GREEN_HILL_BIOME_KEY =
            RegistryKey.of(RegistryKeys.BIOME,
                    Identifier.of(SonicUnnamedMod.MOD_ID, "green_hill_biome"));

    public static Biome create() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();

        return new Biome.Builder()
                .precipitation(true)
                .temperature(0.8f)
                .downfall(0.4f)
                .effects(new BiomeEffects.Builder()
                        .waterColor(0x3f76e4)
                        .waterFogColor(0x50533)
                        .skyColor(0x78a7ff)
                        .fogColor(0xc0d8ff)
                        .grassColor(0x8b9d6b)
                        .foliageColor(0x8b9d6b)
                        .build())
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }
}