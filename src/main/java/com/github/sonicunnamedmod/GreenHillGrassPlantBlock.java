package com.github.sonicunnamedmod;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class GreenHillGrassPlantBlock extends PlantBlock {

    public static final MapCodec<GreenHillGrassPlantBlock> CODEC =
            createCodec(GreenHillGrassPlantBlock::new);

    public GreenHillGrassPlantBlock(Settings settings) {
        super(settings);
    }

    @Override
    public MapCodec<? extends PlantBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(ModBlocks.GREEN_HILL_TURF)
                || floor.isOf(ModBlocks.GREEN_HILL_DIRT)
                || super.canPlantOnTop(floor, world, pos);
    }
}