package com.github.sonicunnamedmod;

import net.minecraft.item.HangingSignItem;
import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    private static Item register(String name, Item item) {
        Identifier id = Identifier.of(SonicUnnamedMod.MOD_ID, name);
        return Registry.register(Registries.ITEM, id, item);
    }

    public static final Item PALM_HANGING_SIGN_ITEM = register("palm_hanging_sign",
            new HangingSignItem(ModBlocks.PALM_HANGING_SIGN, ModBlocks.PALM_WALL_HANGING_SIGN,
                    new Item.Settings().maxCount(16)));

    public static final Item PALM_SIGN_ITEM = register("palm_sign",
            new SignItem(new Item.Settings().maxCount(16), ModBlocks.PALM_SIGN, ModBlocks.PALM_WALL_SIGN));

    public static void registerAll() {
    }
}
