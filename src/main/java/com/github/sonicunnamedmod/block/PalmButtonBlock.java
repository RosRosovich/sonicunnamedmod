package com.github.sonicunnamedmod.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.ButtonBlock;

public class PalmButtonBlock extends ButtonBlock {
    public PalmButtonBlock(AbstractBlock.Settings settings) {
        super(BlockSetType.OAK, 30, settings);
    }
}
