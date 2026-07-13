package com.github.sonicunnamedmod;

import net.minecraft.block.FireBlock;
import net.minecraft.block.Blocks;

public class ModFlammableBlocks {

    public static void register() {

        FireBlock fire = (FireBlock) Blocks.FIRE;

        fire.registerFlammableBlock(ModBlocks.PALM_BLOCK, 5, 5);
        fire.registerFlammableBlock(ModBlocks.PALM_WOOD, 5, 5);
        fire.registerFlammableBlock(ModBlocks.STRIPPED_PALM_BLOCK, 5, 5);
        fire.registerFlammableBlock(ModBlocks.STRIPPED_PALM_WOOD, 5, 5);

        fire.registerFlammableBlock(ModBlocks.PALM_PLANKS, 5, 20);
        fire.registerFlammableBlock(ModBlocks.PALM_SLAB, 5, 20);
        fire.registerFlammableBlock(ModBlocks.PALM_STAIRS, 5, 20);

        fire.registerFlammableBlock(ModBlocks.PALM_FENCE, 5, 20);
        fire.registerFlammableBlock(ModBlocks.PALM_FENCE_GATE, 5, 20);

        fire.registerFlammableBlock(ModBlocks.PALM_BUTTON, 5, 20);
        fire.registerFlammableBlock(ModBlocks.PALM_PRESSURE_PLATE, 5, 20);

        fire.registerFlammableBlock(ModBlocks.PALM_SIGN, 5, 20);
        fire.registerFlammableBlock(ModBlocks.PALM_WALL_SIGN, 5, 20);

        fire.registerFlammableBlock(ModBlocks.PALM_HANGING_SIGN, 5, 20);
        fire.registerFlammableBlock(ModBlocks.PALM_WALL_HANGING_SIGN, 5, 20);

        fire.registerFlammableBlock(ModBlocks.PALM_CORE, 5, 5);
        fire.registerFlammableBlock(ModBlocks.THORNY_PALM_CORE, 5, 5);
        fire.registerFlammableBlock(ModBlocks.PALM_LOG, 5, 5);
    }
}