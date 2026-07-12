package com.github.sonicunnamedmod.block;

import com.mojang.serialization.MapCodec;
import com.github.sonicunnamedmod.SonicUnnamedMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;

public class MetalSpikeBlock extends Block {
    public static final MapCodec<MetalSpikeBlock> CODEC = createCodec(MetalSpikeBlock::new);
    private static final RegistryKey<DamageType> METAL_SPIKE_DAMAGE_TYPE = RegistryKey.of(
            RegistryKeys.DAMAGE_TYPE,
            Identifier.of(SonicUnnamedMod.MOD_ID, "metal_spike")
    );
    private static final VoxelShape COLLISION_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0);
    private static final VoxelShape OUTLINE_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);

    public MetalSpikeBlock(Settings settings) {
        super(settings);
    }

    @Override
    public MapCodec<? extends Block> getCodec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, net.minecraft.world.BlockView world, BlockPos pos,
                                           net.minecraft.block.ShapeContext context) {
        return COLLISION_SHAPE;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, net.minecraft.world.BlockView world, BlockPos pos,
                                         net.minecraft.block.ShapeContext context) {
        return OUTLINE_SHAPE;
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        entity.damage(world.getDamageSources().cactus(), 1.0f);
        super.onSteppedOn(world, pos, state, entity);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        entity.handleFallDamage(fallDistance, 2.0f, createMetalSpikeDamageSource(world));
    }

    private static DamageSource createMetalSpikeDamageSource(World world) {
        return new DamageSource(world.getRegistryManager()
                .get(RegistryKeys.DAMAGE_TYPE)
                .entryOf(METAL_SPIKE_DAMAGE_TYPE));
    }
}
