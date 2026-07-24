package com.github.sonicunnamedmod.world.gen.feature;

import com.github.sonicunnamedmod.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.ArrayList;
import java.util.List;

public class GreenHillVegetationFeature extends Feature<DefaultFeatureConfig> {

    private static final List<BlockState> PLANTS = new ArrayList<>();

    static {
        PLANTS.add(ModBlocks.GREEN_HILL_GRASS.getDefaultState());
        PLANTS.add(ModBlocks.GREEN_HILL_BUSH.getDefaultState());
        PLANTS.add(ModBlocks.CLOVER_FLOWER.getDefaultState());
    }

    public GreenHillVegetationFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();

        if (random.nextInt(2) != 0) {
            return false;
        }

        BlockPos groundPos = origin.down();
        BlockState groundState = world.getBlockState(groundPos);

        if (!isValidGround(groundState, world, groundPos)) {
            return false;
        }

        if (random.nextFloat() < 0.05f) {
            BlockPos flowerPos = origin;
            if (world.isAir(flowerPos) && world.isAir(flowerPos.up())) {
                world.setBlockState(flowerPos,
                        ModBlocks.GREEN_HILL_SUNFLOWER.getDefaultState()
                                .with(Properties.HORIZONTAL_FACING, Direction.EAST), 3);
                return true;
            }
            return false;
        }

        BlockState plant = PLANTS.get(random.nextInt(PLANTS.size()));
        BlockPos plantPos = origin;

        if (world.isAir(plantPos)) {
            if (plant.getBlock() instanceof TallPlantBlock) {
                if (world.isAir(plantPos.up())) {
                    world.setBlockState(plantPos,
                            plant.with(TallPlantBlock.HALF, DoubleBlockHalf.LOWER), 3);
                    world.setBlockState(plantPos.up(),
                            plant.with(TallPlantBlock.HALF, DoubleBlockHalf.UPPER), 3);
                    return true;
                }
                return false;
            } else {
                world.setBlockState(plantPos, plant, 3);
                return true;
            }
        }

        return false;
    }

    private boolean isValidGround(BlockState state, BlockView world, BlockPos pos) {
        return state.isOf(Blocks.GRASS_BLOCK)
                || state.isOf(Blocks.DIRT)
                || state.isOf(Blocks.COARSE_DIRT)
                || state.isOf(Blocks.PODZOL)
                || state.isOf(Blocks.ROOTED_DIRT)
                || state.isOf(Blocks.MYCELIUM)
                || state.isOf(ModBlocks.GREEN_HILL_GRASS_BLOCK)
                || state.isOf(ModBlocks.GREEN_HILL_DIRT)
                || state.isOf(ModBlocks.MARBLE_TILES_GRASS_BLOCK)
                || state.isOf(ModBlocks.MARBLE_BRICKS_GRASS_BLOCK)
                || state.isOf(ModBlocks.POLISHED_MARBLE_GRASS_BLOCK);
    }
}