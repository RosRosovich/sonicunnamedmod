package com.github.sonicunnamedmod.world.gen.feature;

import com.github.sonicunnamedmod.ModBlocks;
import com.github.sonicunnamedmod.TotemWingsBlock;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.ArrayList;
import java.util.List;

public class GreenHillTotemFeature extends Feature<DefaultFeatureConfig> {

    private static final List<Block> TOTEM_BLOCKS = new ArrayList<>();
    private static final Direction[] HORIZONTAL_DIRECTIONS = {
            Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST
    };

    static {
        TOTEM_BLOCKS.add(ModBlocks.TOTEM_ANGRY_BLOCK);
        TOTEM_BLOCKS.add(ModBlocks.TOTEM_CREEPER_BLOCK);
        TOTEM_BLOCKS.add(ModBlocks.TOTEM_GOLEM_BLOCK);
        TOTEM_BLOCKS.add(ModBlocks.TOTEM_SURPRISE_BLOCK);
    }

    public GreenHillTotemFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();

        if (random.nextInt(4) != 0) {
            return false;
        }

        BlockPos groundPos = origin.down();
        if (world.isAir(groundPos) || world.getBlockState(groundPos).isOf(Blocks.WATER)) {
            return false;
        }

        int height = 2 + random.nextInt(4);

        Direction facing = HORIZONTAL_DIRECTIONS[random.nextInt(HORIZONTAL_DIRECTIONS.length)];

        for (int i = 0; i < height; i++) {
            BlockPos pos = origin.up(i);
            if (world.isAir(pos) || world.getBlockState(pos).isAir()) {
                Block randomFace = TOTEM_BLOCKS.get(random.nextInt(TOTEM_BLOCKS.size()));
                world.setBlockState(pos,
                        randomFace.getDefaultState()
                                .with(Properties.HORIZONTAL_FACING, facing), 3);
            } else {
                return false;
            }
        }

        BlockPos wingsPos = origin.up(height - 1);

        Direction leftPos = facing.rotateYCounterclockwise();
        Direction rightPos = facing.rotateYClockwise();

        BlockPos leftWingPos = wingsPos.offset(leftPos);
        if (world.isAir(leftWingPos) || world.getBlockState(leftWingPos).isAir()) {
            world.setBlockState(leftWingPos,
                    ModBlocks.TOTEM_WINGS.getDefaultState()
                            .with(Properties.HORIZONTAL_FACING, facing.getOpposite())
                            .with(TotemWingsBlock.WALL, true), 3);
        }

        BlockPos rightWingPos = wingsPos.offset(rightPos);
        if (world.isAir(rightWingPos) || world.getBlockState(rightWingPos).isAir()) {
            world.setBlockState(rightWingPos,
                    ModBlocks.TOTEM_WINGS.getDefaultState()
                            .with(Properties.HORIZONTAL_FACING, facing)
                            .with(TotemWingsBlock.WALL, true), 3);
        }

        if (height >= 4 && random.nextFloat() < 0.3f) {
            BlockPos extraWingsPos = origin.up(height - 2);

            BlockPos extraLeftPos = extraWingsPos.offset(leftPos);
            if (world.isAir(extraLeftPos) || world.getBlockState(extraLeftPos).isAir()) {
                world.setBlockState(extraLeftPos,
                        ModBlocks.TOTEM_WINGS.getDefaultState()
                                .with(Properties.HORIZONTAL_FACING, facing.getOpposite())
                                .with(TotemWingsBlock.WALL, true), 3);
            }

            BlockPos extraRightPos = extraWingsPos.offset(rightPos);
            if (world.isAir(extraRightPos) || world.getBlockState(extraRightPos).isAir()) {
                world.setBlockState(extraRightPos,
                        ModBlocks.TOTEM_WINGS.getDefaultState()
                                .with(Properties.HORIZONTAL_FACING, facing)
                                .with(TotemWingsBlock.WALL, true), 3);
            }
        }

        return true;
    }
}