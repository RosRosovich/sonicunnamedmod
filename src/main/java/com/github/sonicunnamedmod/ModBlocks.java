package com.github.sonicunnamedmod;

import com.github.sonicunnamedmod.block.CloverFlowerBlock;
import com.github.sonicunnamedmod.block.GreenHillGrassPlantBlock;
import com.github.sonicunnamedmod.block.GreenHillTurfBlock;
import com.github.sonicunnamedmod.block.GreenMarblePillarBlock;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TallBlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final RegistryKey<ItemGroup> ITEM_GROUP_KEY =
            RegistryKey.of(Registries.ITEM_GROUP.getKey(),
                    Identifier.of(SonicUnnamedMod.MOD_ID, "blocks"));

    public static final ItemGroup ITEM_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            ITEM_GROUP_KEY,
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemGroup.sonicunnamedmod.blocks"))
                    .icon(() -> new ItemStack(ModBlocks.GREEN_HILL_TURF))
                    .build()
    );

    private static Block register(String name, Block block) {
        Identifier id = Identifier.of(SonicUnnamedMod.MOD_ID, name);
        Registry.register(Registries.BLOCK, id, block);
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
        return block;
    }

    private static Block registerWithoutBlockItem(String name, Block block) {
        Identifier id = Identifier.of(SonicUnnamedMod.MOD_ID, name);
        Registry.register(Registries.BLOCK, id, block);
        return block;
    }

    public static final Block CLOVER_FLOWER = registerWithoutBlockItem("clover_flower",
            new CloverFlowerBlock(AbstractBlock.Settings.copy(Blocks.PEONY).nonOpaque())
    );

    public static final Block GREEN_HILL_GRASS = register("green_hill_grass",
            new GreenHillGrassPlantBlock(
                    AbstractBlock.Settings.copy(Blocks.SHORT_GRASS).nonOpaque()
            )
    );

    public static final Block GREEN_HILL_DIRT = register("green_hill_dirt",
            new Block(AbstractBlock.Settings.copy(Blocks.DIRT)));

    public static final Block GREEN_HILL_TURF = register("green_hill_turf",
            new GreenHillTurfBlock(AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK))
    );

    public static final Block CARVED_GREEN_HILL_TURF = register("carved_green_hill_turf",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS).requiresTool()));

    public static final Block CRACKED_GREEN_HILL_TURF = register("cracked_green_hill_turf",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS).requiresTool().strength(1.5f, 6.0f)));

    public static final Block TOTEM_ANGRY_BLOCK = register("totem_angry_block",
            new TotemBlock(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool().strength(2.0f)));

    public static final Block TOTEM_CREEPER_BLOCK = register("totem_creeper_block",
            new TotemBlock(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool().strength(2.0f)));

    public static final Block TOTEM_GOLEM_BLOCK = register("totem_golem_block",
            new TotemBlock(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool().strength(2.0f)));

    public static final Block TOTEM_SURPRISE_BLOCK = register("totem_surprise_block",
            new TotemBlock(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool().strength(2.0f)));

    public static void registerFlowerItem() {
        Identifier id = Identifier.of(SonicUnnamedMod.MOD_ID, "clover_flower");
        Item flowerItem = Registry.register(Registries.ITEM, id,
                new TallBlockItem(CLOVER_FLOWER, new Item.Settings()));
    }

    public static void registerAll() {
        registerFlowerItem();

        //Marble Blocks

        Block marble_bricks = register("marble_bricks",
                new Block(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool()));
        register("marble_bricks_slab", new SlabBlock(AbstractBlock.Settings.copy(marble_bricks).requiresTool()));
        register("marble_bricks_stairs", new StairsBlock(marble_bricks.getDefaultState(), AbstractBlock.Settings.copy(marble_bricks).requiresTool()));
        register("marble_bricks_wall", new WallBlock(AbstractBlock.Settings.copy(marble_bricks).requiresTool()));

        Block marble_flagstone = register("marble_flagstone",
                new Block(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool()));
        register("marble_flagstone_slab", new SlabBlock(AbstractBlock.Settings.copy(marble_flagstone).requiresTool()));
        register("marble_flagstone_stairs", new StairsBlock(marble_flagstone.getDefaultState(), AbstractBlock.Settings.copy(marble_flagstone).requiresTool()));
        register("marble_flagstone_wall", new WallBlock(AbstractBlock.Settings.copy(marble_flagstone).requiresTool()));

        Block polished_marble = register("polished_marble",
                new Block(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool()));
        register("polished_marble_slab", new SlabBlock(AbstractBlock.Settings.copy(polished_marble).requiresTool()));
        register("polished_marble_stairs", new StairsBlock(polished_marble.getDefaultState(), AbstractBlock.Settings.copy(polished_marble).requiresTool()));
        register("polished_marble_wall", new WallBlock(AbstractBlock.Settings.copy(polished_marble).requiresTool()));

        Block green_marble_brick = register("green_marble_brick",
                new Block(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool()));
        register("green_marble_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(green_marble_brick).requiresTool()));
        register("green_marble_brick_stairs", new StairsBlock(green_marble_brick.getDefaultState(), AbstractBlock.Settings.copy(green_marble_brick).requiresTool()));
        register("green_marble_brick_wall", new WallBlock(AbstractBlock.Settings.copy(green_marble_brick).requiresTool()));

        Block green_polished_marble = register("green_polished_marble",
                new Block(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool()));
        register("green_polished_marble_slab", new SlabBlock(AbstractBlock.Settings.copy(green_polished_marble).requiresTool()));
        register("green_polished_marble_stairs", new StairsBlock(green_polished_marble.getDefaultState(), AbstractBlock.Settings.copy(green_polished_marble).requiresTool()));
        register("green_polished_marble_wall", new WallBlock(AbstractBlock.Settings.copy(green_polished_marble).requiresTool()));

        Block green_marble_tiles = register("green_marble_tiles",
                new Block(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool()));
        register("green_marble_tiles_slab", new SlabBlock(AbstractBlock.Settings.copy(green_marble_tiles).requiresTool()));
        register("green_marble_tiles_stairs", new StairsBlock(green_marble_tiles.getDefaultState(), AbstractBlock.Settings.copy(green_marble_tiles).requiresTool()));
        register("green_marble_tiles_wall", new WallBlock(AbstractBlock.Settings.copy(green_marble_tiles).requiresTool()));

        register("green_chiseled_marble",
                new Block(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool()));

        register("green_chiseled_marble_bricks",
                new Block(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool()));

        register("green_marble_pillar",
                new GreenMarblePillarBlock(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool()));

        register("green_push_marble",
                new Block(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool()));

        ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP_KEY).register(entries -> {
            entries.add(CLOVER_FLOWER);
            entries.add(GREEN_HILL_GRASS);

            entries.add(GREEN_HILL_DIRT);
            entries.add(GREEN_HILL_TURF);
            entries.add(CRACKED_GREEN_HILL_TURF);
            entries.add(CARVED_GREEN_HILL_TURF);

            entries.add(TOTEM_ANGRY_BLOCK);
            entries.add(TOTEM_CREEPER_BLOCK);
            entries.add(TOTEM_GOLEM_BLOCK);
            entries.add(TOTEM_SURPRISE_BLOCK);

            entries.add(marble_bricks);
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "marble_bricks_slab")));
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "marble_bricks_stairs")));
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "marble_bricks_wall")));

            entries.add(marble_flagstone);
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "marble_flagstone_slab")));
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "marble_flagstone_stairs")));
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "marble_flagstone_wall")));

            entries.add(polished_marble);
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "polished_marble_slab")));
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "polished_marble_stairs")));
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "polished_marble_wall")));

            entries.add(green_marble_brick);
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "green_marble_brick_slab")));
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "green_marble_brick_stairs")));
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "green_marble_brick_wall")));

            entries.add(green_polished_marble);
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "green_polished_marble_slab")));
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "green_polished_marble_stairs")));
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "green_polished_marble_wall")));

            entries.add(green_marble_tiles);
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "green_marble_tiles_slab")));
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "green_marble_tiles_stairs")));
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "green_marble_tiles_wall")));

            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "green_chiseled_marble")));
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "green_chiseled_marble_bricks")));
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "green_marble_pillar")));
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "green_push_marble")));
        });
    }
}