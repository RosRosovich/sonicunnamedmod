package com.github.sonicunnamedmod;

import com.github.sonicunnamedmod.block.GreenHillInteractionHandler;
import com.github.sonicunnamedmod.block.PalmCoreInteractionHandler;
import com.github.sonicunnamedmod.block.ThornyPalmCoreBlock;
import net.fabricmc.api.ModInitializer;

public class SonicUnnamedMod implements ModInitializer {
    public static final String MOD_ID = "sonicunnamedmod";

    @Override
    public void onInitialize() {
        ModBlocks.registerAll();
        ModItems.registerAll();
        ModComposter.register();
        ModFlammableBlocks.register();
        ModFuelRegistry.register();
        ModTrades.register();
        GreenHillInteractionHandler.register();
        ThornyPalmCoreBlock.registerDamageHandler();
        PalmCoreInteractionHandler.register();
    }
}
