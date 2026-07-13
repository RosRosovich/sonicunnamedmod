package com.github.sonicunnamedmod;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;

public class ModComposter {

    public static void register() {

        CompostingChanceRegistry.INSTANCE.add(ModBlocks.GREEN_HILL_GRASS.asItem(), 0.65F);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.GREEN_HILL_BUSH.asItem(), 0.65F);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.CLOVER_FLOWER.asItem(), 0.65F);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.GREEN_HILL_SUNFLOWER.asItem(), 0.65F);

        CompostingChanceRegistry.INSTANCE.add(ModBlocks.PALM_LEAVES.asItem(), 0.30F);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.PALM_THORN, 0.30F);
    }
}