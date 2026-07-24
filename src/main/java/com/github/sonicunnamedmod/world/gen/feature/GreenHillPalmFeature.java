package com.github.sonicunnamedmod.world.gen.feature;

import com.github.sonicunnamedmod.ModBlocks;
import com.github.sonicunnamedmod.block.PalmLeavesBlock;
import com.github.sonicunnamedmod.block.PalmSaplingBlock;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class GreenHillPalmFeature extends Feature<DefaultFeatureConfig> {

    private static final int MIN_HEIGHT = 4;
    private static final int MAX_HEIGHT = 8;

    public GreenHillPalmFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();

        boolean isFromSapling = context.getConfig() == DefaultFeatureConfig.INSTANCE &&
                world.getBlockState(origin.down()).getBlock() instanceof PalmSaplingBlock;

        if (!isFromSapling && random.nextInt(3) != 0) {
            return false;
        }

        int height = MIN_HEIGHT + random.nextInt(MAX_HEIGHT - MIN_HEIGHT + 1);

        return generatePalmTree(world, origin, random, height);
    }

    public boolean generatePalmTree(StructureWorldAccess world, BlockPos origin, Random random, int height) {
        BlockPos groundPos = origin.down();

        BlockState groundState = world.getBlockState(groundPos);
        if (!isValidGround(groundState, world, groundPos)) {
            return false;
        }

        for (int i = 0; i <= height + 1; i++) {
            BlockPos checkPos = origin.up(i);
            if (!world.isAir(checkPos) && !world.getBlockState(checkPos).isAir()) {
                return false;
            }
        }

        BlockPos topLeafPos = origin.up(height + 1);
        for (Direction dir : Direction.values()) {
            if (dir != Direction.DOWN) {
                BlockPos leafCheckPos = topLeafPos.offset(dir);
                if (dir == Direction.UP) {
                    if (!world.isAir(leafCheckPos) && !world.getBlockState(leafCheckPos).isAir()) {
                        return false;
                    }
                } else {
                    BlockPos sideLeafPos = topLeafPos.offset(dir, 2);
                    if (!world.isAir(sideLeafPos) && !world.getBlockState(sideLeafPos).isAir()) {
                        return false;
                    }
                }
            }
        }

        for (int i = 0; i < height; i++) {
            BlockPos logPos = origin.up(i);
            world.setBlockState(logPos, ModBlocks.PALM_LOG.getDefaultState(), 3);
        }

        BlockPos corePos = origin.up(height);
        world.setBlockState(corePos, ModBlocks.THORNY_PALM_CORE.getDefaultState(), 3);

        generateLeaves(world, origin, height);

        return true;
    }

    private void generateLeaves(StructureWorldAccess world, BlockPos origin, int height) {
        BlockPos topLeafPos = origin.up(height + 1);
        world.setBlockState(topLeafPos,
                ModBlocks.PALM_LEAVES.getDefaultState()
                        .with(PalmLeavesBlock.TOP, true)
                        .with(Properties.HORIZONTAL_FACING, Direction.NORTH), 3);

        Direction[] directions = {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
        for (Direction dir : directions) {
            BlockPos leafPos = topLeafPos.offset(dir, 2);
            world.setBlockState(leafPos,
                    ModBlocks.PALM_LEAVES.getDefaultState()
                            .with(PalmLeavesBlock.TOP, false)
                            .with(Properties.HORIZONTAL_FACING, dir), 3);
        }
    }

    private boolean isValidGround(BlockState state, BlockView world, BlockPos pos) {
        return state.isOf(Blocks.GRASS_BLOCK)
                || state.isOf(Blocks.DIRT)
                || state.isOf(Blocks.SAND)
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