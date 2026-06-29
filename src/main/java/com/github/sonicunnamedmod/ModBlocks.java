package com.github.sonicunnamedmod;

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
                    Identifier.of(SonicUnnamedMod.MOD_ID, "blocks"));

    public static final ItemGroup ITEM_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            ITEM_GROUP_KEY,
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemGroup.sonicunnamedmod.blocks"))
                    .icon(() -> new ItemStack(Blocks.GRANITE))
                    .build()
    );

    private static Block register(String name, Block block) {
        Identifier id = Identifier.of(SonicUnnamedMod.MOD_ID, name);
        Registry.register(Registries.BLOCK, id, block);
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
        ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP_KEY).register(entries -> entries.add(block));
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
        ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP_KEY).register(entries -> entries.add(flowerItem));
    }

    public static void registerAll() {
        registerFlowerItem();


    }
}