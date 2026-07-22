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
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class TotemWingsBlock extends TotemBlock {
    public static final BooleanProperty WALL = BooleanProperty.of("wall");

    private static final VoxelShape NORTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0, 8, 7.5, 15, 14, 8.5),
            Block.createCuboidShape(0, 9, 7, 12, 14, 9),
            Block.createCuboidShape(0, 2, 7.5, 10, 7, 8.5),
            Block.createCuboidShape(0, 3, 7, 8, 7, 9)
    );
    private static final VoxelShape EAST_SHAPE = rotateYClockwise(NORTH_SHAPE);
    private static final VoxelShape SOUTH_SHAPE = rotateYClockwise(EAST_SHAPE);
    private static final VoxelShape WEST_SHAPE = rotateYClockwise(SOUTH_SHAPE);

    private static final VoxelShape FLOOR_NORTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0, 0.5, 2, 14, 1.5, 8),
            Block.createCuboidShape(0, 0, 2, 11, 2, 7),
            Block.createCuboidShape(0, 0.5, 9, 9, 1.5, 14),
            Block.createCuboidShape(0, 0, 9, 7, 2, 13)
    );
    private static final VoxelShape FLOOR_EAST_SHAPE = rotateYClockwise(FLOOR_NORTH_SHAPE);
    private static final VoxelShape FLOOR_SOUTH_SHAPE = rotateYClockwise(FLOOR_EAST_SHAPE);
    private static final VoxelShape FLOOR_WEST_SHAPE = rotateYClockwise(FLOOR_SOUTH_SHAPE);

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

    private static VoxelShape rotateYClockwise(VoxelShape shape) {
        VoxelShape[] rotated = {VoxelShapes.empty()};
        shape.forEachBox((minX, minY, minZ, maxX, maxY, maxZ) ->
                rotated[0] = VoxelShapes.union(rotated[0], VoxelShapes.cuboid(
                        1.0 - maxZ, minY, minX,
                        1.0 - minZ, maxY, maxX
                ))
        );
        return rotated[0];
    }
}
