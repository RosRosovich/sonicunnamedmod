package com.mrspriters.mrspriters;

import com.mojang.serialization.MapCodec;
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
                    Identifier.of(MrSpritersMod.MOD_ID, "blocks"));

    public static final ItemGroup ITEM_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            ITEM_GROUP_KEY,
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemGroup.mrspriters.blocks"))
                    .icon(() -> new ItemStack(Blocks.GRANITE))
                    .build()
    );

    private static Block register(String name, Block block) {
        Identifier id = Identifier.of(MrSpritersMod.MOD_ID, name);
        Registry.register(Registries.BLOCK, id, block);
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
        ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP_KEY).register(entries -> entries.add(block));
        return block;
    }

    private static Block registerWithoutBlockItem(String name, Block block) {
        Identifier id = Identifier.of(MrSpritersMod.MOD_ID, name);
        Registry.register(Registries.BLOCK, id, block);
        return block;
    }

    public static final Block CLOVER_FLOWER = registerWithoutBlockItem("clover_flower",
            new TallPlantBlock(AbstractBlock.Settings.copy(Blocks.PEONY).nonOpaque()));

    public static final Block GREEN_HILL_GRASS = register("green_hill_grass",
            new PlantBlock(AbstractBlock.Settings.copy(Blocks.SHORT_GRASS).nonOpaque()) {
                @Override
                protected MapCodec<? extends PlantBlock> getCodec() {
                    return null;
                }
            });
    public static final Block BASALT_BRICKS = register("basalt_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS).requiresTool()));

    public static final Block GREEN_HILL_DIRT = register("green_hill_dirt",
            new Block(AbstractBlock.Settings.copy(Blocks.DIRT)));

    public static final Block GREEN_HILL_TURF = register("green_hill_turf",
            new GrassBlock(AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK)));

    public static final Block CARVED_GREEN_HILL_TURF = register("carved_green_hill_turf",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS).requiresTool()));

    public static final Block TOTEM_ANGRY_BLOCK = register("totem_angry_block",
            new TotemBlock(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool().strength(2.0f)));

    public static final Block TOTEM_CREEPER_BLOCK = register("totem_creeper_block",
            new TotemBlock(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool().strength(2.0f)));

    public static final Block TOTEM_GOLEM_BLOCK = register("totem_golem_block",
            new TotemBlock(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool().strength(2.0f)));

    public static final Block TOTEM_SURPRISE_BLOCK = register("totem_surprise_block",
            new TotemBlock(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool().strength(2.0f)));

    public static void registerFlowerItem() {
        Identifier id = Identifier.of(MrSpritersMod.MOD_ID, "clover_flower");
        Item flowerItem = Registry.register(Registries.ITEM, id,
                new TallBlockItem(CLOVER_FLOWER, new Item.Settings()));
        ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP_KEY).register(entries -> entries.add(flowerItem));
    }

    public static void registerAll() {
        registerFlowerItem();

        Block granite_bricks = register("granite_bricks",
                new Block(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool()));
        register("granite_bricks_slab", new SlabBlock(AbstractBlock.Settings.copy(granite_bricks).requiresTool()));
        register("granite_bricks_stairs", new StairsBlock(granite_bricks.getDefaultState(), AbstractBlock.Settings.copy(granite_bricks).requiresTool()));
        register("granite_bricks_wall", new WallBlock(AbstractBlock.Settings.copy(granite_bricks).requiresTool()));

        Block calcite_bricks = register("calcite_bricks",
                new Block(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool()));
        register("calcite_bricks_slab", new SlabBlock(AbstractBlock.Settings.copy(calcite_bricks).requiresTool()));
        register("calcite_bricks_stairs", new StairsBlock(calcite_bricks.getDefaultState(), AbstractBlock.Settings.copy(calcite_bricks).requiresTool()));
        register("calcite_bricks_wall", new WallBlock(AbstractBlock.Settings.copy(calcite_bricks).requiresTool()));

        Block carved_diorite = register("carved_diorite",
                new Block(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool()));
        register("carved_diorite_slab", new SlabBlock(AbstractBlock.Settings.copy(carved_diorite).requiresTool()));
        register("carved_diorite_stairs", new StairsBlock(carved_diorite.getDefaultState(), AbstractBlock.Settings.copy(carved_diorite).requiresTool()));
        register("carved_diorite_wall", new WallBlock(AbstractBlock.Settings.copy(carved_diorite).requiresTool()));

        Block andesite_bricks = register("andesite_bricks",
                new Block(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool()));
        register("andesite_bricks_slab", new SlabBlock(AbstractBlock.Settings.copy(andesite_bricks).requiresTool()));
        register("andesite_bricks_stairs", new StairsBlock(andesite_bricks.getDefaultState(), AbstractBlock.Settings.copy(andesite_bricks).requiresTool()));
        register("andesite_bricks_wall", new WallBlock(AbstractBlock.Settings.copy(andesite_bricks).requiresTool()));

        Block polished_calcite = register("polished_calcite",
                new Block(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool()));
        register("polished_calcite_slab", new SlabBlock(AbstractBlock.Settings.copy(polished_calcite).requiresTool()));
        register("polished_calcite_stairs", new StairsBlock(polished_calcite.getDefaultState(), AbstractBlock.Settings.copy(polished_calcite).requiresTool()));
        register("polished_calcite_wall", new WallBlock(AbstractBlock.Settings.copy(polished_calcite).requiresTool()));

        Block diorite_bricks = register("diorite_bricks",
                new Block(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool()));
        register("diorite_bricks_slab", new SlabBlock(AbstractBlock.Settings.copy(diorite_bricks).requiresTool()));
        register("diorite_bricks_stairs", new StairsBlock(diorite_bricks.getDefaultState(), AbstractBlock.Settings.copy(diorite_bricks).requiresTool()));
        register("diorite_bricks_wall", new WallBlock(AbstractBlock.Settings.copy(diorite_bricks).requiresTool()));
    }
}