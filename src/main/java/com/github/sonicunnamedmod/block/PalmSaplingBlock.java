package com.github.sonicunnamedmod.block;

import com.github.sonicunnamedmod.SonicUnnamedMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;

import java.util.Optional;

public class PalmSaplingBlock extends SaplingBlock {

    private static final RegistryKey<ConfiguredFeature<?, ?>> PALM_TREE_KEY =
            RegistryKey.of(
                    RegistryKeys.CONFIGURED_FEATURE,
                    Identifier.of(SonicUnnamedMod.MOD_ID, "palm_tree")
            );

    private static final SaplingGenerator PALM_GENERATOR =
            new SaplingGenerator(
                    "palm",
                    Optional.empty(),
                    Optional.of(PALM_TREE_KEY),
                    Optional.empty()
            );

    public PalmSaplingBlock(Settings settings) {
        super(PALM_GENERATOR, settings);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(Blocks.GRASS_BLOCK)
                || floor.isOf(Blocks.DIRT)
                || floor.isOf(Blocks.SAND)
                || floor.isOf(Blocks.COARSE_DIRT)
                || floor.isOf(Blocks.PODZOL)
                || floor.isOf(Blocks.ROOTED_DIRT)
                || floor.isOf(Blocks.MYCELIUM)
                || floor.isOf(com.github.sonicunnamedmod.ModBlocks.GREEN_HILL_GRASS_BLOCK)
                || floor.isOf(com.github.sonicunnamedmod.ModBlocks.GREEN_HILL_DIRT)
                || floor.isOf(com.github.sonicunnamedmod.ModBlocks.MARBLE_TILES_GRASS_BLOCK)
                || floor.isOf(com.github.sonicunnamedmod.ModBlocks.MARBLE_BRICKS_GRASS_BLOCK)
                || floor.isOf(com.github.sonicunnamedmod.ModBlocks.POLISHED_MARBLE_GRASS_BLOCK);
    }
}