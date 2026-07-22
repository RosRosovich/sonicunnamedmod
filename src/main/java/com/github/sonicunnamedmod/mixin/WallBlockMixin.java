package com.github.sonicunnamedmod.mixin;

import com.github.sonicunnamedmod.block.PalmFenceBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallBlock;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WallBlock.class)
public abstract class WallBlockMixin {
    @Inject(method = "shouldConnectTo", at = @At("HEAD"), cancellable = true)
    private void sonicunnamedmod$preventPalmConnectionToVanillaWalls(
            BlockState neighborState, boolean faceFullSquare, Direction direction,
            CallbackInfoReturnable<Boolean> callback) {
        Block self = (Block) (Object) this;
        Block neighbor = neighborState.getBlock();
        boolean selfIsPalm = self instanceof PalmFenceBlock;
        boolean neighborIsPalm = neighbor instanceof PalmFenceBlock;

        if (!selfIsPalm && !neighborIsPalm) {
            return;
        }

        Block other = selfIsPalm ? neighbor : self;
        Identifier otherId = Registries.BLOCK.getId(other);
        if (other instanceof WallBlock && "minecraft".equals(otherId.getNamespace())) {
            callback.setReturnValue(false);
        }
    }
}
