package com.github.sonicunnamedmod.block;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;

public class GreenHillInteractionHandler {

    public static void register() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            var stack = player.getStackInHand(hand);

            if (stack.getItem() != Items.BONE_MEAL) {
                return ActionResult.PASS;
            }

            var pos = hitResult.getBlockPos();

            if (!GreenHillPlantHelper.isModBlock(world, pos)) {
                return ActionResult.PASS;
            }

            GreenHillPlantHelper.spawnPlantOnBlock(world, pos, player, stack);
            GreenHillPlantHelper.duplicatePlant(world, pos, player, stack);

            return ActionResult.SUCCESS;
        });
    }
}