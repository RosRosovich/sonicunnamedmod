package com.github.sonicunnamedmod.block;

import com.github.sonicunnamedmod.ModBlocks;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.ToolItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PalmCoreInteractionHandler {

    public static void register() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            var stack = player.getStackInHand(hand);
            var pos = hitResult.getBlockPos();
            var state = world.getBlockState(pos);

            if (!state.isOf(ModBlocks.PALM_CORE)) {
                return ActionResult.PASS;
            }

            if (!(stack.getItem() instanceof ToolItem)) {
                return ActionResult.PASS;
            }

            if (!isAxe(stack.getItem())) {
                return ActionResult.PASS;
            }

            if (world.isClient) {
                return ActionResult.SUCCESS;
            }

            world.setBlockState(pos, ModBlocks.STRIPPED_PALM_CORE.getDefaultState());

            world.playSound(null, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0f, 1.0f);

            if (!player.isCreative()) {
                stack.damage(1, player, hand == net.minecraft.util.Hand.MAIN_HAND ?
                        net.minecraft.entity.EquipmentSlot.MAINHAND :
                        net.minecraft.entity.EquipmentSlot.OFFHAND);
            }

            return ActionResult.SUCCESS;
        });
    }

    private static boolean isAxe(Item item) {
        return item == Items.WOODEN_AXE ||
                item == Items.STONE_AXE ||
                item == Items.IRON_AXE ||
                item == Items.GOLDEN_AXE ||
                item == Items.DIAMOND_AXE ||
                item == Items.NETHERITE_AXE;
    }
}