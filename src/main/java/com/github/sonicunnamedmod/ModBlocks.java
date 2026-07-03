package com.github.sonicunnamedmod;

import com.github.sonicunnamedmod.block.*;
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
import net.minecraft.sound.BlockSoundGroup;
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
                    .icon(() -> new ItemStack(ModBlocks.GREEN_HILL_GRASS_BLOCK))
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

    public static final Block GREEN_HILL_GRASS_BLOCK = register("green_hill_grass_block",
            new GreenHillTurfBlock(AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK))
    );

    public static final Block PALM_LOG = register("palm_log",
            new PalmBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block PALM_TOP = register("palm_top",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block PALM_BLOCK = register("palm_block",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));

    public static final Block PALM_BLOCK_SLAB = register("palm_block_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB)));

    public static final Block PALM_BLOCK_STAIRS = register("palm_block_stairs",
            new StairsBlock(PALM_BLOCK.getDefaultState(), AbstractBlock.Settings.copy(Blocks.OAK_STAIRS)));

    public static final Block PALM_PLANKS = register("palm_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));

    public static final Block PALM_SLAB = register("palm_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB)));

    public static final Block PALM_STAIRS = register("palm_stairs",
            new StairsBlock(PALM_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.OAK_STAIRS)));

    public static final Block PALM_FENCE = register("palm_fence",
            new FenceBlock(AbstractBlock.Settings.copy(Blocks.OAK_FENCE)));

    public static final Block PALM_FENCE_GATE = register("palm_fence_gate",
            new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_FENCE_GATE)));

    public static final Block PALM_BUTTON = register("palm_button",
            new PalmButtonBlock(AbstractBlock.Settings.copy(Blocks.OAK_BUTTON)));

    public static final Block PALM_PRESSURE_PLATE = register("palm_pressure_plate",
            new PalmPressurePlateBlock(AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE)));

    public static final Block CARVED_GREEN_HILL_DIRT = register("carved_green_hill_dirt",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS).requiresTool()));

    public static final Block CRACKED_GREEN_HILL_DIRT = register("cracked_green_hill_dirt",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS).requiresTool().strength(1.5f, 6.0f)));

    public static final Block TOTEM_ANGRY_BLOCK = register("totem_angry_block",
            new TotemBlock(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool().strength(2.0f)));

    public static final Block TOTEM_CREEPER_BLOCK = register("totem_creeper_block",
            new TotemBlock(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool().strength(2.0f)));

    public static final Block TOTEM_GOLEM_BLOCK = register("totem_golem_block",
            new TotemBlock(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool().strength(2.0f)));

    public static final Block TOTEM_SURPRISE_BLOCK = register("totem_surprise_block",
            new TotemBlock(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool().strength(2.0f)));

    public static final Block TOTEM_WINGS = register("totem_wings",
            new TotemWingsBlock(AbstractBlock.Settings.copy(Blocks.STONE)
                    .requiresTool()
                    .strength(2.0f)
                    .nonOpaque()));

    public static Block MARBLE_BRICKS;
    public static Block MARBLE_TILES;
    public static Block POLISHED_MARBLE;
    public static Block GREEN_MARBLE_BRICK;
    public static Block GREEN_POLISHED_MARBLE;
    public static Block GREEN_MARBLE_TILES;

    public static final Block LAVA_GLASS = register("lava_glass",
            new LavaGlassBlock(AbstractBlock.Settings.create()
                    .nonOpaque()
                    .luminance(state -> 15)
                    .strength(0.3f)
                    .sounds(BlockSoundGroup.GLASS)
            ));

    public static final Block PALM_LEAVES = register("palm_leaves",
            new PalmLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)
                    .nonOpaque()
                    .strength(0.2f)
                    .sounds(BlockSoundGroup.GRASS)
                    .ticksRandomly()
            )
    );

    public static Block MARBLE_TILES_GRASS_BLOCK;
    public static Block MARBLE_BRICKS_GRASS_BLOCK;
    public static Block POLISHED_MARBLE_GRASS_BLOCK;

    public static void registerFlowerItem() {
        Identifier id = Identifier.of(SonicUnnamedMod.MOD_ID, "clover_flower");
        Item flowerItem = Registry.register(Registries.ITEM, id,
                new TallBlockItem(CLOVER_FLOWER, new Item.Settings()));
    }

    public static void registerAll() {
        registerFlowerItem();

        MARBLE_BRICKS = register("marble_bricks",
                new Block(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool()));
        register("marble_bricks_slab", new SlabBlock(AbstractBlock.Settings.copy(MARBLE_BRICKS).requiresTool()));
        register("marble_bricks_stairs", new StairsBlock(MARBLE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(MARBLE_BRICKS).requiresTool()));
        register("marble_bricks_wall", new WallBlock(AbstractBlock.Settings.copy(MARBLE_BRICKS).requiresTool()));

        MARBLE_TILES = register("marble_tiles",
                new Block(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool()));
        register("marble_tiles_slab", new SlabBlock(AbstractBlock.Settings.copy(MARBLE_TILES).requiresTool()));
        register("marble_tiles_stairs", new StairsBlock(MARBLE_TILES.getDefaultState(), AbstractBlock.Settings.copy(MARBLE_TILES).requiresTool()));
        register("marble_tiles_wall", new WallBlock(AbstractBlock.Settings.copy(MARBLE_TILES).requiresTool()));

        POLISHED_MARBLE = register("polished_marble",
                new Block(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool()));
        register("polished_marble_slab", new SlabBlock(AbstractBlock.Settings.copy(POLISHED_MARBLE).requiresTool()));
        register("polished_marble_stairs", new StairsBlock(POLISHED_MARBLE.getDefaultState(), AbstractBlock.Settings.copy(POLISHED_MARBLE).requiresTool()));
        register("polished_marble_wall", new WallBlock(AbstractBlock.Settings.copy(POLISHED_MARBLE).requiresTool()));

        MARBLE_TILES_GRASS_BLOCK = register("marble_tiles_grass_block",
                new MarbleTilesGrassBlock(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool().strength(1.5f, 6.0f))
        );

        MARBLE_BRICKS_GRASS_BLOCK = register("marble_bricks_grass_block",
                new MarbleBricksGrassBlock(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool().strength(1.5f, 6.0f))
        );

        POLISHED_MARBLE_GRASS_BLOCK = register("polished_marble_grass_block",
                new PolishedMarbleGrassBlock(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool().strength(1.5f, 6.0f))
        );

        GREEN_MARBLE_BRICK = register("green_marble_brick",
                new Block(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool()));
        register("green_marble_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(GREEN_MARBLE_BRICK).requiresTool()));
        register("green_marble_brick_stairs", new StairsBlock(GREEN_MARBLE_BRICK.getDefaultState(), AbstractBlock.Settings.copy(GREEN_MARBLE_BRICK).requiresTool()));
        register("green_marble_brick_wall", new WallBlock(AbstractBlock.Settings.copy(GREEN_MARBLE_BRICK).requiresTool()));

        GREEN_POLISHED_MARBLE = register("green_polished_marble",
                new Block(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool()));
        register("green_polished_marble_slab", new SlabBlock(AbstractBlock.Settings.copy(GREEN_POLISHED_MARBLE).requiresTool()));
        register("green_polished_marble_stairs", new StairsBlock(GREEN_POLISHED_MARBLE.getDefaultState(), AbstractBlock.Settings.copy(GREEN_POLISHED_MARBLE).requiresTool()));
        register("green_polished_marble_wall", new WallBlock(AbstractBlock.Settings.copy(GREEN_POLISHED_MARBLE).requiresTool()));

        GREEN_MARBLE_TILES = register("green_marble_tiles",
                new Block(AbstractBlock.Settings.copy(Blocks.STONE).requiresTool()));
        register("green_marble_tiles_slab", new SlabBlock(AbstractBlock.Settings.copy(GREEN_MARBLE_TILES).requiresTool()));
        register("green_marble_tiles_stairs", new StairsBlock(GREEN_MARBLE_TILES.getDefaultState(), AbstractBlock.Settings.copy(GREEN_MARBLE_TILES).requiresTool()));
        register("green_marble_tiles_wall", new WallBlock(AbstractBlock.Settings.copy(GREEN_MARBLE_TILES).requiresTool()));

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
            entries.add(GREEN_HILL_GRASS_BLOCK);
            entries.add(PALM_LOG);
            entries.add(PALM_LEAVES);
            entries.add(PALM_TOP);
            entries.add(PALM_BLOCK);
            entries.add(PALM_BLOCK_SLAB);
            entries.add(PALM_BLOCK_STAIRS);
            entries.add(PALM_PLANKS);
            entries.add(PALM_SLAB);
            entries.add(PALM_STAIRS);
            entries.add(PALM_FENCE);
            entries.add(PALM_FENCE_GATE);
            entries.add(PALM_BUTTON);
            entries.add(PALM_PRESSURE_PLATE);
            entries.add(CRACKED_GREEN_HILL_DIRT);
            entries.add(CARVED_GREEN_HILL_DIRT);

            entries.add(TOTEM_ANGRY_BLOCK);
            entries.add(TOTEM_CREEPER_BLOCK);
            entries.add(TOTEM_GOLEM_BLOCK);
            entries.add(TOTEM_SURPRISE_BLOCK);
            entries.add(TOTEM_WINGS);

            entries.add(LAVA_GLASS);
            entries.add(MARBLE_TILES_GRASS_BLOCK);
            entries.add(MARBLE_BRICKS_GRASS_BLOCK);
            entries.add(POLISHED_MARBLE_GRASS_BLOCK);

            entries.add(MARBLE_BRICKS);
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "marble_bricks_slab")));
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "marble_bricks_stairs")));
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "marble_bricks_wall")));

            entries.add(MARBLE_TILES);
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "marble_tiles_slab")));
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "marble_tiles_stairs")));
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "marble_tiles_wall")));

            entries.add(POLISHED_MARBLE);
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "polished_marble_slab")));
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "polished_marble_stairs")));
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "polished_marble_wall")));

            entries.add(GREEN_MARBLE_BRICK);
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "green_marble_brick_slab")));
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "green_marble_brick_stairs")));
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "green_marble_brick_wall")));

            entries.add(GREEN_POLISHED_MARBLE);
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "green_polished_marble_slab")));
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "green_polished_marble_stairs")));
            entries.add(Registries.BLOCK.get(Identifier.of(SonicUnnamedMod.MOD_ID, "green_polished_marble_wall")));

            entries.add(GREEN_MARBLE_TILES);
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
