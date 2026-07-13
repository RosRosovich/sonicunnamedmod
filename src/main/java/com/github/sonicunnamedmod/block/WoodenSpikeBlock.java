package com.github.sonicunnamedmod.block;

import com.github.sonicunnamedmod.SonicUnnamedMod;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;

public class WoodenSpikeBlock extends Block {
    public static final MapCodec<WoodenSpikeBlock> CODEC = createCodec(WoodenSpikeBlock::new);
    private static final RegistryKey<DamageType> WOODEN_SPIKE_DAMAGE_TYPE = RegistryKey.of(
            RegistryKeys.DAMAGE_TYPE,
            Identifier.of(SonicUnnamedMod.MOD_ID, "wooden_spike")
    );
    private static final VoxelShape COLLISION_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0);
    private static final VoxelShape OUTLINE_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);

    public WoodenSpikeBlock(Settings settings) {
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
        if (!(entity instanceof ItemEntity)) {
            Vec3d velocity = entity.getVelocity();
            entity.damage(createWoodenSpikeDamageSource(world), 1.0f);
            entity.setVelocity(velocity);
        }
        super.onSteppedOn(world, pos, state, entity);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        entity.handleFallDamage(fallDistance, 1.5f, createWoodenSpikeDamageSource(world));
    }

    private static DamageSource createWoodenSpikeDamageSource(World world) {
        return new DamageSource(world.getRegistryManager()
                .get(RegistryKeys.DAMAGE_TYPE)
                .entryOf(WOODEN_SPIKE_DAMAGE_TYPE));
    }
}
