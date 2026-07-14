package com.github.sonicunnamedmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class PalmBlock extends PillarBlock {
    private static final VoxelShape X_SHAPE = Block.createCuboidShape(0, 4, 4, 16, 12, 12);
    private static final VoxelShape Y_SHAPE = Block.createCuboidShape(4, 0, 4, 12, 16, 12);
    private static final VoxelShape Z_SHAPE = Block.createCuboidShape(4, 4, 0, 12, 12, 16);

    public PalmBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getShape(state);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getShape(state);
    }

    private VoxelShape getShape(BlockState state) {
        Direction.Axis axis = state.get(AXIS);

        return switch (axis) {
            case X -> X_SHAPE;
            case Y -> Y_SHAPE;
            case Z -> Z_SHAPE;
        };
    }
}
