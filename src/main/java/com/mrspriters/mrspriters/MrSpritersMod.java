
package com.mrspriters.mrspriters;

import net.fabricmc.api.ModInitializer;

public class MrSpritersMod implements ModInitializer {
    public static final String MOD_ID = "mrspriters";

    @Override
    public void onInitialize() {
        ModBlocks.registerAll();
    }
}
