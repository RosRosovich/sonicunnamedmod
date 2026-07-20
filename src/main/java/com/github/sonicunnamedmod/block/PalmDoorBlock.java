package com.github.sonicunnamedmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.enums.DoorHinge;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class PalmDoorBlock extends DoorBlock {
    private static final VoxelShape LOWER_CLOSED_EAST = Block.createCuboidShape(0, 3, 1, 3, 16, 16);
    private static final VoxelShape LOWER_CLOSED_NORTH = Block.createCuboidShape(1, 3, 13, 16, 16, 16);
    private static final VoxelShape LOWER_CLOSED_SOUTH = Block.createCuboidShape(0, 3, 0, 15, 16, 3);
    private static final VoxelShape LOWER_CLOSED_WEST = Block.createCuboidShape(13, 3, 0, 16, 16, 15);

    private static final VoxelShape LOWER_CLOSED_LEFT_EAST = Block.createCuboidShape(0, 3, 0, 3, 16, 15);
    private static final VoxelShape LOWER_CLOSED_LEFT_NORTH = Block.createCuboidShape(0, 3, 13, 15, 16, 16);
    private static final VoxelShape LOWER_CLOSED_LEFT_SOUTH = Block.createCuboidShape(1, 3, 0, 16, 16, 3);
    private static final VoxelShape LOWER_CLOSED_LEFT_WEST = Block.createCuboidShape(13, 3, 1, 16, 16, 16);

    private static final VoxelShape LOWER_OPEN_EAST = Block.createCuboidShape(0, 3, 13, 15, 16, 16);
    private static final VoxelShape LOWER_OPEN_NORTH = Block.createCuboidShape(13, 3, 1, 16, 16, 16);
    private static final VoxelShape LOWER_OPEN_SOUTH = Block.createCuboidShape(0, 3, 0, 3, 16, 15);
    private static final VoxelShape LOWER_OPEN_WEST = Block.createCuboidShape(1, 3, 0, 16, 16, 3);

    private static final VoxelShape LOWER_OPEN_LEFT_EAST = Block.createCuboidShape(0, 3, 0, 15, 16, 3);
    private static final VoxelShape LOWER_OPEN_LEFT_NORTH = Block.createCuboidShape(0, 3, 1, 3, 16, 16);
    private static final VoxelShape LOWER_OPEN_LEFT_SOUTH = Block.createCuboidShape(13, 3, 0, 16, 16, 15);
    private static final VoxelShape LOWER_OPEN_LEFT_WEST = Block.createCuboidShape(1, 3, 13, 16, 16, 16);

    private static final VoxelShape UPPER_CLOSED_EAST = withTopDecoration(
            Block.createCuboidShape(0, 0, 1, 3, 8, 16),
            Block.createCuboidShape(0, 8, 1, 3, 10, 3));
    private static final VoxelShape UPPER_CLOSED_NORTH = withTopDecoration(
            Block.createCuboidShape(1, 0, 13, 16, 8, 16),
            Block.createCuboidShape(1, 8, 13, 3, 10, 16));
    private static final VoxelShape UPPER_CLOSED_SOUTH = withTopDecoration(
            Block.createCuboidShape(0, 0, 0, 15, 8, 3),
            Block.createCuboidShape(13, 8, 0, 15, 10, 3));
    private static final VoxelShape UPPER_CLOSED_WEST = withTopDecoration(
            Block.createCuboidShape(13, 0, 0, 16, 8, 15),
            Block.createCuboidShape(13, 8, 13, 16, 10, 15));

    private static final VoxelShape UPPER_CLOSED_LEFT_EAST = withTopDecoration(
            Block.createCuboidShape(0, 0, 0, 3, 8, 15),
            Block.createCuboidShape(0, 8, 13, 3, 10, 15));
    private static final VoxelShape UPPER_CLOSED_LEFT_NORTH = withTopDecoration(
            Block.createCuboidShape(0, 0, 13, 15, 8, 16),
            Block.createCuboidShape(13, 8, 13, 15, 10, 16));
    private static final VoxelShape UPPER_CLOSED_LEFT_SOUTH = withTopDecoration(
            Block.createCuboidShape(1, 0, 0, 16, 8, 3),
            Block.createCuboidShape(1, 8, 0, 3, 10, 3));
    private static final VoxelShape UPPER_CLOSED_LEFT_WEST = withTopDecoration(
            Block.createCuboidShape(13, 0, 1, 16, 8, 16),
            Block.createCuboidShape(13, 8, 1, 16, 10, 3));

    private static final VoxelShape UPPER_OPEN_EAST = withTopDecoration(
            Block.createCuboidShape(0, 0, 13, 15, 8, 16),
            Block.createCuboidShape(13, 8, 13, 15, 10, 16));
    private static final VoxelShape UPPER_OPEN_NORTH = withTopDecoration(
            Block.createCuboidShape(13, 0, 1, 16, 8, 16),
            Block.createCuboidShape(13, 8, 1, 16, 10, 3));
    private static final VoxelShape UPPER_OPEN_SOUTH = withTopDecoration(
            Block.createCuboidShape(0, 0, 0, 3, 8, 15),
            Block.createCuboidShape(0, 8, 13, 3, 10, 15));
    private static final VoxelShape UPPER_OPEN_WEST = withTopDecoration(
            Block.createCuboidShape(1, 0, 0, 16, 8, 3),
            Block.createCuboidShape(1, 8, 0, 3, 10, 3));

    private static final VoxelShape UPPER_OPEN_LEFT_EAST = withTopDecoration(
            Block.createCuboidShape(0, 0, 0, 15, 8, 3),
            Block.createCuboidShape(13, 8, 0, 15, 10, 3));
    private static final VoxelShape UPPER_OPEN_LEFT_NORTH = withTopDecoration(
            Block.createCuboidShape(0, 0, 1, 3, 8, 16),
            Block.createCuboidShape(0, 8, 1, 3, 10, 3));
    private static final VoxelShape UPPER_OPEN_LEFT_SOUTH = withTopDecoration(
            Block.createCuboidShape(13, 0, 0, 16, 8, 15),
            Block.createCuboidShape(13, 8, 13, 16, 10, 15));
    private static final VoxelShape UPPER_OPEN_LEFT_WEST = withTopDecoration(
            Block.createCuboidShape(1, 0, 13, 16, 8, 16),
            Block.createCuboidShape(1, 8, 13, 3, 10, 16));

    public PalmDoorBlock(Settings settings) {
        super(BlockSetType.OAK, settings);
    }

    private static VoxelShape withTopDecoration(VoxelShape panel, VoxelShape decoration) {
        return VoxelShapes.union(panel, decoration);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        BlockState state = super.getPlacementState(context);
        if (state == null) {
            return null;
        }

        Direction facing = state.get(FACING);
        Direction left = facing.rotateYCounterclockwise();
        Direction right = facing.rotateYClockwise();
        boolean supportedOnLeft = hasSupportColumn(context.getWorld(), context.getBlockPos(), left);
        boolean supportedOnRight = hasSupportColumn(context.getWorld(), context.getBlockPos(), right);

        if (!supportedOnLeft && !supportedOnRight) {
            return null;
        }
        if (supportedOnLeft != supportedOnRight) {
            return state.with(HINGE, supportedOnLeft ? DoorHinge.LEFT : DoorHinge.RIGHT);
        }

        return state;
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos lowerPos = state.get(HALF) == DoubleBlockHalf.LOWER ? pos : pos.down();
        if (state.get(HALF) == DoubleBlockHalf.UPPER && !world.getBlockState(lowerPos).isOf(this)) {
            return false;
        }

        return hasSupportColumn(world, lowerPos, getSupportDirection(state));
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState,
                                                   WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        BlockPos lowerPos = state.get(HALF) == DoubleBlockHalf.LOWER ? pos : pos.down();
        if (!hasSupportColumn(world, lowerPos, getSupportDirection(state))) {
            return Blocks.AIR.getDefaultState();
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    private static Direction getSupportDirection(BlockState state) {
        Direction facing = state.get(FACING);
        return state.get(HINGE) == DoorHinge.LEFT
                ? facing.rotateYCounterclockwise()
                : facing.rotateYClockwise();
    }

    private static boolean hasSupportColumn(WorldView world, BlockPos lowerDoorPos, Direction supportDirection) {
        BlockPos lowerSupportPos = lowerDoorPos.offset(supportDirection);
        BlockPos upperSupportPos = lowerSupportPos.up();
        Direction faceTowardDoor = supportDirection.getOpposite();

        return hasSolidFace(world, lowerSupportPos, faceTowardDoor)
                && hasSolidFace(world, upperSupportPos, faceTowardDoor);
    }

    private static boolean hasSolidFace(WorldView world, BlockPos supportPos, Direction faceTowardDoor) {
        return world.getBlockState(supportPos).isSideSolidFullSquare(world, supportPos, faceTowardDoor);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getPalmDoorShape(state);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getPalmDoorShape(state);
    }

    private static VoxelShape getPalmDoorShape(BlockState state) {
        Direction facing = state.get(FACING);
        boolean open = state.get(OPEN);
        boolean lower = state.get(HALF) == DoubleBlockHalf.LOWER;
        boolean rightHinge = state.get(HINGE) == DoorHinge.RIGHT;

        if (lower) {
            return open ? getLowerOpenShape(facing, rightHinge) : getLowerClosedShape(facing, rightHinge);
        }

        return open ? getUpperOpenShape(facing, rightHinge) : getUpperClosedShape(facing, rightHinge);
    }

    private static VoxelShape getLowerClosedShape(Direction facing, boolean rightHinge) {
        if (!rightHinge) {
            return switch (facing) {
                case EAST -> LOWER_CLOSED_LEFT_EAST;
                case NORTH -> LOWER_CLOSED_LEFT_NORTH;
                case SOUTH -> LOWER_CLOSED_LEFT_SOUTH;
                case WEST -> LOWER_CLOSED_LEFT_WEST;
                default -> throw new IllegalStateException("Palm door cannot face " + facing);
            };
        }

        return switch (facing) {
            case EAST -> LOWER_CLOSED_EAST;
            case NORTH -> LOWER_CLOSED_NORTH;
            case SOUTH -> LOWER_CLOSED_SOUTH;
            case WEST -> LOWER_CLOSED_WEST;
            default -> throw new IllegalStateException("Palm door cannot face " + facing);
        };
    }

    private static VoxelShape getLowerOpenShape(Direction facing, boolean rightHinge) {
        if (!rightHinge) {
            return switch (facing) {
                case EAST -> LOWER_OPEN_LEFT_EAST;
                case NORTH -> LOWER_OPEN_LEFT_NORTH;
                case SOUTH -> LOWER_OPEN_LEFT_SOUTH;
                case WEST -> LOWER_OPEN_LEFT_WEST;
                default -> throw new IllegalStateException("Palm door cannot face " + facing);
            };
        }

        return switch (facing) {
            case EAST -> LOWER_OPEN_EAST;
            case NORTH -> LOWER_OPEN_NORTH;
            case SOUTH -> LOWER_OPEN_SOUTH;
            case WEST -> LOWER_OPEN_WEST;
            default -> throw new IllegalStateException("Palm door cannot face " + facing);
        };
    }

    private static VoxelShape getUpperClosedShape(Direction facing, boolean rightHinge) {
        if (!rightHinge) {
            return switch (facing) {
                case EAST -> UPPER_CLOSED_LEFT_EAST;
                case NORTH -> UPPER_CLOSED_LEFT_NORTH;
                case SOUTH -> UPPER_CLOSED_LEFT_SOUTH;
                case WEST -> UPPER_CLOSED_LEFT_WEST;
                default -> throw new IllegalStateException("Palm door cannot face " + facing);
            };
        }

        return switch (facing) {
            case EAST -> UPPER_CLOSED_EAST;
            case NORTH -> UPPER_CLOSED_NORTH;
            case SOUTH -> UPPER_CLOSED_SOUTH;
            case WEST -> UPPER_CLOSED_WEST;
            default -> throw new IllegalStateException("Palm door cannot face " + facing);
        };
    }

    private static VoxelShape getUpperOpenShape(Direction facing, boolean rightHinge) {
        if (!rightHinge) {
            return switch (facing) {
                case EAST -> UPPER_OPEN_LEFT_EAST;
                case NORTH -> UPPER_OPEN_LEFT_NORTH;
                case SOUTH -> UPPER_OPEN_LEFT_SOUTH;
                case WEST -> UPPER_OPEN_LEFT_WEST;
                default -> throw new IllegalStateException("Palm door cannot face " + facing);
            };
        }

        return switch (facing) {
            case EAST -> UPPER_OPEN_EAST;
            case NORTH -> UPPER_OPEN_NORTH;
            case SOUTH -> UPPER_OPEN_SOUTH;
            case WEST -> UPPER_OPEN_WEST;
            default -> throw new IllegalStateException("Palm door cannot face " + facing);
        };
    }
}
