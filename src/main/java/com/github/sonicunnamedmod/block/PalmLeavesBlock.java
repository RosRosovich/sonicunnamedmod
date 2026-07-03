package com.github.sonicunnamedmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class PalmLeavesBlock extends Block {
    public static final BooleanProperty TOP = BooleanProperty.of("top");

    private static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 16, 16, 16);

    public PalmLeavesBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState()
                .with(TOP, false)
                .with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TOP, Properties.HORIZONTAL_FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction side = ctx.getSide();
        Direction facing = ctx.getHorizontalPlayerFacing().getOpposite();

        if (side == Direction.UP || side == Direction.DOWN) {
            return getDefaultState()
                    .with(TOP, true)
                    .with(Properties.HORIZONTAL_FACING, facing);
        }
        else {
            return getDefaultState()
                    .with(TOP, false)
                    .with(Properties.HORIZONTAL_FACING, side);
        }
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction facing = state.get(Properties.HORIZONTAL_FACING);
        boolean isTop = state.get(TOP);

        BlockPos supportPos;
        if (isTop) {
            supportPos = pos.down();
        } else {
            supportPos = pos.offset(facing.getOpposite());
        }

        BlockState supportState = world.getBlockState(supportPos);
        return supportState.isSolidBlock(world, supportPos);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction,
                                                   BlockState neighborState, WorldAccess world,
                                                   BlockPos pos, BlockPos neighborPos) {
        if (!canPlaceAt(state, world, pos)) {
        }
        return state;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }
}