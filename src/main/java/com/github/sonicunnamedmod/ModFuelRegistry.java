package com.github.sonicunnamedmod;

import net.fabricmc.fabric.api.registry.FuelRegistry;

public class ModFuelRegistry {

    public static void register() {

        FuelRegistry.INSTANCE.add(ModBlocks.PALM_BLOCK.asItem(), 300);
        FuelRegistry.INSTANCE.add(ModBlocks.PALM_WOOD.asItem(), 300);
        FuelRegistry.INSTANCE.add(ModBlocks.WOODEN_SPIKE.asItem(), 300);
        FuelRegistry.INSTANCE.add(ModBlocks.STRIPPED_PALM_BLOCK.asItem(), 300);
        FuelRegistry.INSTANCE.add(ModBlocks.STRIPPED_PALM_WOOD.asItem(), 300);
        FuelRegistry.INSTANCE.add(ModBlocks.PALM_LOG.asItem(), 300);
        FuelRegistry.INSTANCE.add(ModBlocks.PALM_CORE.asItem(), 300);
        FuelRegistry.INSTANCE.add(ModBlocks.STRIPPED_PALM_CORE.asItem(), 300);
        FuelRegistry.INSTANCE.add(ModBlocks.THORNY_PALM_CORE.asItem(), 300);

        FuelRegistry.INSTANCE.add(ModBlocks.PALM_PLANKS.asItem(), 300);

        FuelRegistry.INSTANCE.add(ModBlocks.PALM_SLAB.asItem(), 150);
        FuelRegistry.INSTANCE.add(ModBlocks.PALM_BLOCK_SLAB.asItem(), 150);

        FuelRegistry.INSTANCE.add(ModBlocks.PALM_STAIRS.asItem(), 300);
        FuelRegistry.INSTANCE.add(ModBlocks.PALM_BLOCK_STAIRS.asItem(), 300);

        FuelRegistry.INSTANCE.add(ModBlocks.PALM_FENCE.asItem(), 300);
        FuelRegistry.INSTANCE.add(ModBlocks.PALM_FENCE_GATE.asItem(), 300);

        FuelRegistry.INSTANCE.add(ModBlocks.PALM_BUTTON.asItem(), 100);
        FuelRegistry.INSTANCE.add(ModBlocks.PALM_PRESSURE_PLATE.asItem(), 300);

        FuelRegistry.INSTANCE.add(ModItems.PALM_SIGN_ITEM, 200);
        FuelRegistry.INSTANCE.add(ModItems.PALM_HANGING_SIGN_ITEM, 200);
    }
}