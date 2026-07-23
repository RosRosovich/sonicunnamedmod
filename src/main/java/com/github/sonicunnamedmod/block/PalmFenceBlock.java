package com.github.sonicunnamedmod.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.enums.WallShape;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

public class PalmFenceBlock extends WallBlock {
    public PalmFenceBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        BlockState state = super.getPlacementState(context);
        return state == null ? null : adjustStateBelowFence(state, context.getWorld(), context.getBlockPos());
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction,
                                                    BlockState neighborState, WorldAccess world,
                                                    BlockPos pos, BlockPos neighborPos) {
        BlockState updatedState = super.getStateForNeighborUpdate(
                state, direction, neighborState, world, pos, neighborPos);
        return adjustStateBelowFence(updatedState, world, pos);
    }

    private BlockState adjustStateBelowFence(BlockState state, BlockView world, BlockPos pos) {
        if (!(world.getBlockState(pos.up()).getBlock() instanceof FenceBlock)) {
            return state;
        }

        state = state
                .with(NORTH_SHAPE, makeTallIfConnected(state.get(NORTH_SHAPE)))
                .with(EAST_SHAPE, makeTallIfConnected(state.get(EAST_SHAPE)))
                .with(SOUTH_SHAPE, makeTallIfConnected(state.get(SOUTH_SHAPE)))
                .with(WEST_SHAPE, makeTallIfConnected(state.get(WEST_SHAPE)));

        boolean northNone = state.get(NORTH_SHAPE) == WallShape.NONE;
        boolean eastNone = state.get(EAST_SHAPE) == WallShape.NONE;
        boolean southNone = state.get(SOUTH_SHAPE) == WallShape.NONE;
        boolean westNone = state.get(WEST_SHAPE) == WallShape.NONE;

        // Vanilla wall topology rule without the collision-shape contribution
        // from the block above: keep posts at isolated, end, corner and T states.
        boolean topologyNeedsPost = northNone && eastNone && southNone && westNone
                || northNone != southNone
                || eastNone != westNone;
        return state.with(UP, topologyNeedsPost);
    }

    private static WallShape makeTallIfConnected(WallShape shape) {
        return shape == WallShape.NONE ? WallShape.NONE : WallShape.TALL;
    }
}
