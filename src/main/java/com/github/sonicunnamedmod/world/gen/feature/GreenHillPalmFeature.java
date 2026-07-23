package com.github.sonicunnamedmod.world.gen.feature;

import com.github.sonicunnamedmod.ModBlocks;
import com.github.sonicunnamedmod.block.PalmLeavesBlock;
import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class GreenHillPalmFeature extends Feature<DefaultFeatureConfig> {

    public GreenHillPalmFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();

        if (random.nextInt(3) != 0) {
            return false;
        }

        BlockPos groundPos = origin.down();
        if (world.isAir(groundPos) || world.getBlockState(groundPos).isOf(Blocks.WATER)) {
            return false;
        }

        for (int i = 0; i < 4; i++) {
            BlockPos logPos = origin.up(i);
            if (world.isAir(logPos) || world.getBlockState(logPos).isAir()) {
                world.setBlockState(logPos, ModBlocks.PALM_LOG.getDefaultState(), 3);
            } else {
                return false;
            }
        }

        BlockPos corePos = origin.up(4);
        if (world.isAir(corePos) || world.getBlockState(corePos).isAir()) {
            world.setBlockState(corePos, ModBlocks.THORNY_PALM_CORE.getDefaultState(), 3);
        } else {
            return false;
        }

        BlockPos topLeafPos = origin.up(5);
        if (world.isAir(topLeafPos) || world.getBlockState(topLeafPos).isAir()) {
            world.setBlockState(topLeafPos,
                    ModBlocks.PALM_LEAVES.getDefaultState()
                            .with(PalmLeavesBlock.TOP, true)
                            .with(Properties.HORIZONTAL_FACING, Direction.NORTH), 3);
        }

        Direction[] directions = {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};

        for (Direction dir : directions) {
            BlockPos leafPos = topLeafPos.offset(dir, 2);

            if (world.getBlockState(leafPos).isAir()) {
                world.setBlockState(leafPos,
                        ModBlocks.PALM_LEAVES.getDefaultState()
                                .with(PalmLeavesBlock.TOP, false)
                                .with(Properties.HORIZONTAL_FACING, dir), 3);
            }
        }

        return true;
    }
}