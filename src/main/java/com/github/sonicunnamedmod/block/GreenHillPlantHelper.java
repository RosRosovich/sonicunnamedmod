package com.github.sonicunnamedmod.block;

import com.github.sonicunnamedmod.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class GreenHillPlantHelper {

    private static final List<BlockState> PLANTS = new ArrayList<>();

    static {
        PLANTS.add(ModBlocks.CLOVER_FLOWER.getDefaultState());
        PLANTS.add(ModBlocks.GREEN_HILL_GRASS.getDefaultState());
    }

    public static void spawnPlantOnBlock(World world, BlockPos pos, PlayerEntity player, ItemStack stack) {
        if (world.isClient) return;

        BlockState state = world.getBlockState(pos);

        if (!state.isOf(ModBlocks.GREEN_HILL_GRASS_BLOCK) &&
                !state.isOf(ModBlocks.GREEN_HILL_DIRT) &&
                !state.isOf(ModBlocks.MARBLE_TILES_GRASS_BLOCK) &&
                !state.isOf(ModBlocks.MARBLE_BRICKS_GRASS_BLOCK) &&
                !state.isOf(ModBlocks.POLISHED_MARBLE_GRASS_BLOCK)) {
            return;
        }

        if (stack.getItem() == Items.BONE_MEAL && !player.getAbilities().creativeMode) {
            stack.decrement(1);
        }

        for (int i = 0; i < 20; i++) {
            int x = pos.getX() + world.random.nextInt(3) - 1;
            int z = pos.getZ() + world.random.nextInt(3) - 1;
            BlockPos plantPos = new BlockPos(x, pos.getY() + 1, z);

            if (world.isAir(plantPos)) {
                BlockState below = world.getBlockState(plantPos.down());
                if (below.isOf(ModBlocks.GREEN_HILL_GRASS_BLOCK) ||
                        below.isOf(ModBlocks.GREEN_HILL_DIRT) ||
                        below.isOf(ModBlocks.MARBLE_TILES_GRASS_BLOCK) ||
                        below.isOf(ModBlocks.MARBLE_BRICKS_GRASS_BLOCK) ||
                        below.isOf(ModBlocks.POLISHED_MARBLE_GRASS_BLOCK)) {
                    BlockState plant = PLANTS.get(world.random.nextInt(PLANTS.size()));
                    placePlant(world, plantPos, plant);
                    spawnEffects(world, pos);
                    return;
                }
            }
        }
    }

    public static boolean isModBlock(World world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        return state.isOf(ModBlocks.GREEN_HILL_GRASS_BLOCK) ||
                state.isOf(ModBlocks.GREEN_HILL_DIRT) ||
                state.isOf(ModBlocks.CLOVER_FLOWER) ||
                state.isOf(ModBlocks.MARBLE_BRICKS_GRASS_BLOCK) ||
                state.isOf(ModBlocks.MARBLE_TILES_GRASS_BLOCK) ||
                state.isOf(ModBlocks.POLISHED_MARBLE_GRASS_BLOCK) ||
                state.isOf(ModBlocks.GREEN_HILL_GRASS);
    }

    public static void duplicatePlant(World world, BlockPos pos, PlayerEntity player, ItemStack stack) {
        if (world.isClient) return;

        BlockState state = world.getBlockState(pos);

        if (!state.isOf(ModBlocks.CLOVER_FLOWER) && !state.isOf(ModBlocks.GREEN_HILL_GRASS)) {
            return;
        }

        if (stack.getItem() == Items.BONE_MEAL && !player.getAbilities().creativeMode) {
            stack.decrement(1);
        }

        for (int i = 0; i < 20; i++) {
            int x = pos.getX() + world.random.nextInt(3) - 1;
            int z = pos.getZ() + world.random.nextInt(3) - 1;
            BlockPos plantPos = new BlockPos(x, pos.getY(), z);

            if (world.isAir(plantPos)) {
                BlockState below = world.getBlockState(plantPos.down());
                if (below.isOf(ModBlocks.GREEN_HILL_GRASS_BLOCK) ||
                        below.isOf(ModBlocks.GREEN_HILL_DIRT) ||
                        below.isOf(ModBlocks.MARBLE_TILES_GRASS_BLOCK) ||
                        below.isOf(ModBlocks.MARBLE_BRICKS_GRASS_BLOCK) ||
                        below.isOf(ModBlocks.POLISHED_MARBLE_GRASS_BLOCK)) {
                    placePlant(world, plantPos, state);
                    spawnEffects(world, pos);
                    return;
                }
            }
        }
    }

    private static void placePlant(World world, BlockPos pos, BlockState plant) {
        if (plant.getBlock() instanceof TallPlantBlock) {
            if (world.isAir(pos.up())) {
                world.setBlockState(pos, plant.with(TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
                world.setBlockState(pos.up(), plant.with(TallPlantBlock.HALF, DoubleBlockHalf.UPPER));
            }
        } else {
            world.setBlockState(pos, plant);
        }
    }

    private static void spawnEffects(World world, BlockPos pos) {
        world.playSound(null, pos, SoundEvents.ITEM_BONE_MEAL_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
        if (!world.isClient) {
            ServerWorld serverWorld = (ServerWorld) world;
            for (int i = 0; i < 10; i++) {
                double x = pos.getX() + world.random.nextDouble();
                double y = pos.getY() + 0.5 + world.random.nextDouble() * 0.5;
                double z = pos.getZ() + world.random.nextDouble();
                serverWorld.spawnParticles(ParticleTypes.HAPPY_VILLAGER, x, y, z, 1, 0, 0, 0, 0);
            }
        }
    }
}