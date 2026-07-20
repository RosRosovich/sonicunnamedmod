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
    private static final VoxelShape OUTLINE_THICK_POST = Block.createCuboidShape(4, 0, 4, 12, 16, 12);
    private static final VoxelShape OUTLINE_THIN_POST = Block.createCuboidShape(6, 0, 6, 10, 14, 10);

    private static final VoxelShape OUTLINE_THICK_NORTH_LOWER = Block.createCuboidShape(7, 2, 0, 9, 6, 4);
    private static final VoxelShape OUTLINE_THICK_NORTH_UPPER = Block.createCuboidShape(7, 10, 0, 9, 14, 4);
    private static final VoxelShape OUTLINE_THICK_EAST_LOWER = Block.createCuboidShape(12, 2, 7, 16, 6, 9);
    private static final VoxelShape OUTLINE_THICK_EAST_UPPER = Block.createCuboidShape(12, 10, 7, 16, 14, 9);
    private static final VoxelShape OUTLINE_THICK_SOUTH_LOWER = Block.createCuboidShape(7, 2, 12, 9, 6, 16);
    private static final VoxelShape OUTLINE_THICK_SOUTH_UPPER = Block.createCuboidShape(7, 10, 12, 9, 14, 16);
    private static final VoxelShape OUTLINE_THICK_WEST_LOWER = Block.createCuboidShape(0, 2, 7, 4, 6, 9);
    private static final VoxelShape OUTLINE_THICK_WEST_UPPER = Block.createCuboidShape(0, 10, 7, 4, 14, 9);

    private static final VoxelShape OUTLINE_THIN_NORTH_LOWER = Block.createCuboidShape(7, 2, 0, 9, 6, 6);
    private static final VoxelShape OUTLINE_THIN_NORTH_UPPER = Block.createCuboidShape(7, 10, 0, 9, 14, 6);
    private static final VoxelShape OUTLINE_THIN_EAST_LOWER = Block.createCuboidShape(10, 2, 7, 16, 6, 9);
    private static final VoxelShape OUTLINE_THIN_EAST_UPPER = Block.createCuboidShape(10, 10, 7, 16, 14, 9);
    private static final VoxelShape OUTLINE_THIN_SOUTH_LOWER = Block.createCuboidShape(7, 2, 10, 9, 6, 16);
    private static final VoxelShape OUTLINE_THIN_SOUTH_UPPER = Block.createCuboidShape(7, 10, 10, 9, 14, 16);
    private static final VoxelShape OUTLINE_THIN_WEST_LOWER = Block.createCuboidShape(0, 2, 7, 6, 6, 9);
    private static final VoxelShape OUTLINE_THIN_WEST_UPPER = Block.createCuboidShape(0, 10, 7, 6, 14, 9);

    // Fences keep a 1.5-block-tall collision so players cannot jump over them.
    private static final VoxelShape COLLISION_THICK_POST = Block.createCuboidShape(4, 0, 4, 12, 24, 12);
    private static final VoxelShape COLLISION_THIN_POST = Block.createCuboidShape(6, 0, 6, 10, 24, 10);
    private static final VoxelShape COLLISION_THICK_NORTH = Block.createCuboidShape(7, 0, 0, 9, 24, 4);
    private static final VoxelShape COLLISION_THICK_EAST = Block.createCuboidShape(12, 0, 7, 16, 24, 9);
    private static final VoxelShape COLLISION_THICK_SOUTH = Block.createCuboidShape(7, 0, 12, 9, 24, 16);
    private static final VoxelShape COLLISION_THICK_WEST = Block.createCuboidShape(0, 0, 7, 4, 24, 9);
    private static final VoxelShape COLLISION_THIN_NORTH = Block.createCuboidShape(7, 0, 0, 9, 24, 6);
    private static final VoxelShape COLLISION_THIN_EAST = Block.createCuboidShape(10, 0, 7, 16, 24, 9);
    private static final VoxelShape COLLISION_THIN_SOUTH = Block.createCuboidShape(7, 0, 10, 9, 24, 16);
    private static final VoxelShape COLLISION_THIN_WEST = Block.createCuboidShape(0, 0, 7, 6, 24, 9);

    public PalmFenceBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        boolean straightMiddle = isStraightMiddle(state);
        VoxelShape shape = straightMiddle ? OUTLINE_THIN_POST : OUTLINE_THICK_POST;

        if (state.get(NORTH)) {
            shape = straightMiddle
                    ? VoxelShapes.union(shape, OUTLINE_THIN_NORTH_LOWER, OUTLINE_THIN_NORTH_UPPER)
                    : VoxelShapes.union(shape, OUTLINE_THICK_NORTH_LOWER, OUTLINE_THICK_NORTH_UPPER);
        }
        if (state.get(EAST)) {
            shape = straightMiddle
                    ? VoxelShapes.union(shape, OUTLINE_THIN_EAST_LOWER, OUTLINE_THIN_EAST_UPPER)
                    : VoxelShapes.union(shape, OUTLINE_THICK_EAST_LOWER, OUTLINE_THICK_EAST_UPPER);
        }
        if (state.get(SOUTH)) {
            shape = straightMiddle
                    ? VoxelShapes.union(shape, OUTLINE_THIN_SOUTH_LOWER, OUTLINE_THIN_SOUTH_UPPER)
                    : VoxelShapes.union(shape, OUTLINE_THICK_SOUTH_LOWER, OUTLINE_THICK_SOUTH_UPPER);
        }
        if (state.get(WEST)) {
            shape = straightMiddle
                    ? VoxelShapes.union(shape, OUTLINE_THIN_WEST_LOWER, OUTLINE_THIN_WEST_UPPER)
                    : VoxelShapes.union(shape, OUTLINE_THICK_WEST_LOWER, OUTLINE_THICK_WEST_UPPER);
        }

        return shape;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        boolean straightMiddle = isStraightMiddle(state);
        VoxelShape shape = straightMiddle ? COLLISION_THIN_POST : COLLISION_THICK_POST;

        if (state.get(NORTH)) {
            shape = VoxelShapes.union(shape, straightMiddle ? COLLISION_THIN_NORTH : COLLISION_THICK_NORTH);
        }
        if (state.get(EAST)) {
            shape = VoxelShapes.union(shape, straightMiddle ? COLLISION_THIN_EAST : COLLISION_THICK_EAST);
        }
        if (state.get(SOUTH)) {
            shape = VoxelShapes.union(shape, straightMiddle ? COLLISION_THIN_SOUTH : COLLISION_THICK_SOUTH);
        }
        if (state.get(WEST)) {
            shape = VoxelShapes.union(shape, straightMiddle ? COLLISION_THIN_WEST : COLLISION_THICK_WEST);
        }

        return shape;
    }

    private static boolean isStraightMiddle(BlockState state) {
        boolean north = state.get(NORTH);
        boolean east = state.get(EAST);
        boolean south = state.get(SOUTH);
        boolean west = state.get(WEST);

        return north && south && !east && !west
                || east && west && !north && !south;
    }
}
