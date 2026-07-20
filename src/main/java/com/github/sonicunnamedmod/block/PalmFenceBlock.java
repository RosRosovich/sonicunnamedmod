package com.github.sonicunnamedmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class PalmFenceBlock extends FenceBlock {
    private static final VoxelShape OUTLINE_POST = Block.createCuboidShape(4, 0, 4, 12, 16, 12);
    private static final VoxelShape OUTLINE_NORTH_LOWER = Block.createCuboidShape(7, 2, 0, 9, 6, 4);
    private static final VoxelShape OUTLINE_NORTH_UPPER = Block.createCuboidShape(7, 10, 0, 9, 14, 4);
    private static final VoxelShape OUTLINE_EAST_LOWER = Block.createCuboidShape(12, 2, 7, 16, 6, 9);
    private static final VoxelShape OUTLINE_EAST_UPPER = Block.createCuboidShape(12, 10, 7, 16, 14, 9);
    private static final VoxelShape OUTLINE_SOUTH_LOWER = Block.createCuboidShape(7, 2, 12, 9, 6, 16);
    private static final VoxelShape OUTLINE_SOUTH_UPPER = Block.createCuboidShape(7, 10, 12, 9, 14, 16);
    private static final VoxelShape OUTLINE_WEST_LOWER = Block.createCuboidShape(0, 2, 7, 4, 6, 9);
    private static final VoxelShape OUTLINE_WEST_UPPER = Block.createCuboidShape(0, 10, 7, 4, 14, 9);

    // Fences keep a 1.5-block-tall collision so players cannot jump over them.
    private static final VoxelShape COLLISION_POST = Block.createCuboidShape(4, 0, 4, 12, 24, 12);
    private static final VoxelShape COLLISION_NORTH = Block.createCuboidShape(7, 0, 0, 9, 24, 4);
    private static final VoxelShape COLLISION_EAST = Block.createCuboidShape(12, 0, 7, 16, 24, 9);
    private static final VoxelShape COLLISION_SOUTH = Block.createCuboidShape(7, 0, 12, 9, 24, 16);
    private static final VoxelShape COLLISION_WEST = Block.createCuboidShape(0, 0, 7, 4, 24, 9);

    public PalmFenceBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape shape = OUTLINE_POST;

        if (state.get(NORTH)) {
            shape = VoxelShapes.union(shape, OUTLINE_NORTH_LOWER, OUTLINE_NORTH_UPPER);
        }
        if (state.get(EAST)) {
            shape = VoxelShapes.union(shape, OUTLINE_EAST_LOWER, OUTLINE_EAST_UPPER);
        }
        if (state.get(SOUTH)) {
            shape = VoxelShapes.union(shape, OUTLINE_SOUTH_LOWER, OUTLINE_SOUTH_UPPER);
        }
        if (state.get(WEST)) {
            shape = VoxelShapes.union(shape, OUTLINE_WEST_LOWER, OUTLINE_WEST_UPPER);
        }

        return shape;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape shape = COLLISION_POST;

        if (state.get(NORTH)) {
            shape = VoxelShapes.union(shape, COLLISION_NORTH);
        }
        if (state.get(EAST)) {
            shape = VoxelShapes.union(shape, COLLISION_EAST);
        }
        if (state.get(SOUTH)) {
            shape = VoxelShapes.union(shape, COLLISION_SOUTH);
        }
        if (state.get(WEST)) {
            shape = VoxelShapes.union(shape, COLLISION_WEST);
        }

        return shape;
    }
}
