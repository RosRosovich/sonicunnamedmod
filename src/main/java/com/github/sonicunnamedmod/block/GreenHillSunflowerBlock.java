package com.github.sonicunnamedmod.block;

import com.github.sonicunnamedmod.ModBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

public class GreenHillSunflowerBlock extends Block {

    public static final MapCodec<GreenHillSunflowerBlock> CODEC =
            createCodec(GreenHillSunflowerBlock::new);

    private static final VoxelShape SHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 32.0, 14.0);

    public GreenHillSunflowerBlock(Settings settings) {
        super(settings);
    }

    @Override
    public MapCodec<? extends Block> getCodec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos belowPos = pos.down();
        BlockState belowState = world.getBlockState(belowPos);
        return canPlantOnTop(belowState, world, belowPos) && world.isAir(pos.up());
    }

    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(ModBlocks.GREEN_HILL_GRASS_BLOCK)
                || floor.isOf(ModBlocks.GREEN_HILL_DIRT)
                || floor.isOf(ModBlocks.MARBLE_TILES_GRASS_BLOCK)
                || floor.isOf(ModBlocks.MARBLE_BRICKS_GRASS_BLOCK)
                || floor.isOf(ModBlocks.POLISHED_MARBLE_GRASS_BLOCK)
                || floor.isOf(Blocks.GRASS_BLOCK)
                || floor.isOf(Blocks.DIRT)
                || floor.isOf(Blocks.COARSE_DIRT)
                || floor.isOf(Blocks.ROOTED_DIRT)
                || floor.isOf(Blocks.PODZOL)
                || floor.isOf(Blocks.MYCELIUM);
    }
}