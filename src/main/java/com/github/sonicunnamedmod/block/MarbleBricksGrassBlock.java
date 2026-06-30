package com.github.sonicunnamedmod.block;

import com.github.sonicunnamedmod.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class MarbleBricksGrassBlock extends Block {

    public MarbleBricksGrassBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient) {
            if (!world.isWater(pos.up()) && world.getLightLevel(pos.up()) >= 9) {
                for (int i = 0; i < 4; ++i) {
                    BlockPos targetPos = pos.add(random.nextInt(3) - 1, 0, random.nextInt(3) - 1);
                    BlockState targetState = world.getBlockState(targetPos);

                    if (targetState.isOf(ModBlocks.MARBLE_BRICKS) && world.getLightLevel(targetPos.up()) >= 9) {
                        world.setBlockState(targetPos, ModBlocks.MARBLE_BRICKS_GRASS_BLOCK.getDefaultState());
                    }
                }
            }
        }
    }
}