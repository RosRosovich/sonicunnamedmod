package com.github.sonicunnamedmod;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;

public final class ModTrades {

    public static void register() {

        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {

            factories.add((entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.EMERALD, 1),
                            new ItemStack(ModBlocks.GREEN_HILL_GRASS, 4),
                            12,
                            1,
                            0.05F
                    )
            );

            factories.add((entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.EMERALD, 1),
                            new ItemStack(ModBlocks.GREEN_HILL_BUSH, 2),
                            12,
                            1,
                            0.05F
                    )
            );

            factories.add((entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.EMERALD, 1),
                            new ItemStack(ModBlocks.CLOVER_FLOWER, 2),
                            12,
                            1,
                            0.05F
                    )
            );

            factories.add((entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.EMERALD, 2),
                            new ItemStack(ModBlocks.GREEN_HILL_SUNFLOWER, 1),
                            8,
                            1,
                            0.05F
                    )
            );

            factories.add((entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.EMERALD, 2),
                            new ItemStack(ModBlocks.PALM_LEAVES, 8),
                            8,
                            1,
                            0.05F
                    )
            );

            factories.add((entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.EMERALD, 2),
                            new ItemStack(ModBlocks.PALM_THORN, 8),
                            8,
                            1,
                            0.05F
                    )
            );

        });
    }
}