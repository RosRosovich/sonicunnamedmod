package com.github.sonicunnamedmod;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class CloverFlowerBlock extends TallPlantBlock {

    public static final MapCodec<CloverFlowerBlock> CODEC =
            createCodec(CloverFlowerBlock::new);

    public CloverFlowerBlock(Settings settings) {
        super(settings);
    }

    @Override
    public MapCodec<? extends TallPlantBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(ModBlocks.GREEN_HILL_TURF)
                || floor.isOf(ModBlocks.GREEN_HILL_DIRT)
                || super.canPlantOnTop(floor, world, pos);
    }
}