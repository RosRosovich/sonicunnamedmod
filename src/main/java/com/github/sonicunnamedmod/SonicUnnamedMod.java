
package com.github.sonicunnamedmod;

import net.fabricmc.api.ModInitializer;

public class SonicUnnamedMod implements ModInitializer {
    public static final String MOD_ID = "sonicunnamedmod";

    @Override
    public void onInitialize() {
        ModBlocks.registerAll();
    }
}
