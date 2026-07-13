package com.github.sonicunnamedmod;

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
import net.minecraft.world.BlockView;

public class TotemWingsBlock extends TotemBlock {
    public static final BooleanProperty WALL = BooleanProperty.of("wall");

    private static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(0, 2, 7, 15, 14, 9);
    private static final VoxelShape EAST_SHAPE = Block.createCuboidShape(7, 2, 0, 9, 14, 15);
    private static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(1, 2, 7, 16, 14, 9);
    private static final VoxelShape WEST_SHAPE = Block.createCuboidShape(7, 2, 1, 9, 14, 16);
    private static final VoxelShape FLOOR_NORTH_SHAPE = Block.createCuboidShape(0, 0, 0, 14, 2, 12);
    private static final VoxelShape FLOOR_EAST_SHAPE = Block.createCuboidShape(4, 0, 0, 16, 2, 14);
    private static final VoxelShape FLOOR_SOUTH_SHAPE = Block.createCuboidShape(2, 0, 4, 16, 2, 16);
    private static final VoxelShape FLOOR_WEST_SHAPE = Block.createCuboidShape(0, 0, 2, 12, 2, 16);

    public TotemWingsBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(WALL, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(WALL);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        Direction side = context.getSide();
        if (side == Direction.DOWN) {
            return null;
        }

        if (side == Direction.UP) {
            return getDefaultState()
                    .with(Properties.HORIZONTAL_FACING, context.getHorizontalPlayerFacing())
                    .with(WALL, false);
        }

        return getDefaultState()
                .with(Properties.HORIZONTAL_FACING, side.rotateYCounterclockwise())
                .with(WALL, true);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getShape(state);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getShape(state);
    }

    private static VoxelShape getShape(BlockState state) {
        Direction facing = state.get(Properties.HORIZONTAL_FACING);
        if (!state.get(WALL)) {
            return switch (facing) {
                case EAST -> FLOOR_EAST_SHAPE;
                case SOUTH -> FLOOR_SOUTH_SHAPE;
                case WEST -> FLOOR_WEST_SHAPE;
                default -> FLOOR_NORTH_SHAPE;
            };
        }

        return switch (facing) {
            case EAST -> EAST_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case WEST -> WEST_SHAPE;
            default -> NORTH_SHAPE;
        };
    }
}
