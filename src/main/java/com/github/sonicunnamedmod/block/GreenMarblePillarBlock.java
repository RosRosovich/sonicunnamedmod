package com.github.sonicunnamedmod.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;

public class GreenMarblePillarBlock extends PillarBlock {
    public static final BooleanProperty CONNECTED_NEGATIVE = BooleanProperty.of("connected_negative");
    public static final BooleanProperty CONNECTED_POSITIVE = BooleanProperty.of("connected_positive");

    public GreenMarblePillarBlock(AbstractBlock.Settings settings) {
        super(settings);
        setDefaultState(getDefaultState()
                .with(CONNECTED_NEGATIVE, false)
                .with(CONNECTED_POSITIVE, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(CONNECTED_NEGATIVE, CONNECTED_POSITIVE);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        BlockState state = super.getPlacementState(context);
        return state == null ? null : withConnections(state, context.getWorld(), context.getBlockPos());
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction,
                                                    BlockState neighborState, WorldAccess world,
                                                    BlockPos pos, BlockPos neighborPos) {
        if (direction.getAxis() != state.get(AXIS)) {
            return state;
        }

        return withConnections(state, world, pos);
    }

    private BlockState withConnections(BlockState state, WorldAccess world, BlockPos pos) {
        Direction.Axis axis = state.get(AXIS);
        Direction negative = Direction.from(axis, Direction.AxisDirection.NEGATIVE);
        Direction positive = Direction.from(axis, Direction.AxisDirection.POSITIVE);

        return state
                .with(CONNECTED_NEGATIVE, connectsTo(world.getBlockState(pos.offset(negative)), axis))
                .with(CONNECTED_POSITIVE, connectsTo(world.getBlockState(pos.offset(positive)), axis));
    }

    private boolean connectsTo(BlockState state, Direction.Axis axis) {
        return state.isOf(this) && state.get(AXIS) == axis;
    }
}
