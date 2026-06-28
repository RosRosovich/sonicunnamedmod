package com.mrspriters.mrspriters;

import net.minecraft.block.*;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

class GreenHillTurfBlock extends GrassBlock {

    public GreenHillTurfBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient) {
            if (!world.isWater(pos.up()) && world.getLightLevel(pos.up()) >= 9) {
                BlockPos upPos = pos.up();
                BlockState upState = world.getBlockState(upPos);
                for (int i = 0; i < 4; ++i) {
                    BlockPos targetPos = upPos.add(random.nextInt(3) - 1, 0, random.nextInt(3) - 1);
                    BlockState targetState = world.getBlockState(targetPos);

                    if (targetState.isOf(ModBlocks.GREEN_HILL_DIRT) && world.getLightLevel(targetPos.up()) >= 9) {
                        world.setBlockState(targetPos, ModBlocks.GREEN_HILL_TURF.getDefaultState());
                    }
                }
            }
        }
    }

    @Override
    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack tool, boolean dropExperience) {
        super.onStacksDropped(state, world, pos, tool, dropExperience);
    }
}