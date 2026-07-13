package com.github.sonicunnamedmod.block;

import com.github.sonicunnamedmod.ModBlocks;
import com.github.sonicunnamedmod.SonicUnnamedMod;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;

public class ThornyPalmCoreBlock extends Block {
    public static final MapCodec<ThornyPalmCoreBlock> CODEC = createCodec(ThornyPalmCoreBlock::new);
    public static final RegistryKey<DamageType> PALM_NEEDLES_DAMAGE_TYPE = RegistryKey.of(
            RegistryKeys.DAMAGE_TYPE,
            Identifier.of(SonicUnnamedMod.MOD_ID, "palm_needles")
    );
    private static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);

    public ThornyPalmCoreBlock(Settings settings) {
        super(settings);
    }

    @Override
    public MapCodec<? extends Block> getCodec() {
        return CODEC;
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos,
                                            PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!stack.isOf(Items.SHEARS)) {
            return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        if (!world.isClient) {
            world.setBlockState(pos, ModBlocks.PALM_CORE.getDefaultState(), Block.NOTIFY_ALL);
            Block.dropStack(world, pos, new ItemStack(ModBlocks.PALM_THORN, 6));
            world.playSound(null, pos, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0f, 1.0f);
            if (player instanceof ServerPlayerEntity serverPlayer) {
                EquipmentSlot slot = hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
                stack.damage(1, (ServerWorld) world, serverPlayer, item -> serverPlayer.sendEquipmentBreakStatus(item, slot));
            }
        }

        return ItemActionResult.success(world.isClient);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, net.minecraft.world.BlockView world, BlockPos pos,
                                           net.minecraft.block.ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, net.minecraft.world.BlockView world, BlockPos pos,
                                         net.minecraft.block.ShapeContext context) {
        return SHAPE;
    }

    public static void registerDamageHandler() {
        ServerTickEvents.END_WORLD_TICK.register(world -> {
            for (ServerPlayerEntity player : world.getPlayers()) {
                if (isTouchingThornyPalmCore(world, player)) {
                    damageEntityWithoutMotion(world, player);
                }
            }
        });
    }

    private static boolean isTouchingThornyPalmCore(ServerWorld world, Entity entity) {
        Box box = entity.getBoundingBox().expand(0.001);
        int minX = MathHelper.floor(box.minX);
        int minY = MathHelper.floor(box.minY);
        int minZ = MathHelper.floor(box.minZ);
        int maxX = MathHelper.floor(box.maxX);
        int maxY = MathHelper.floor(box.maxY);
        int maxZ = MathHelper.floor(box.maxZ);

        for (BlockPos blockPos : BlockPos.iterate(minX, minY, minZ, maxX, maxY, maxZ)) {
            if (world.getBlockState(blockPos).isOf(ModBlocks.THORNY_PALM_CORE)) {
                return true;
            }
        }

        return false;
    }

    private static void damageEntityWithoutMotion(World world, Entity entity) {
        if (entity instanceof ItemEntity) {
            return;
        }

        Vec3d velocity = entity.getVelocity();
        entity.damage(createPalmNeedlesDamageSource(world), 1.0f);
        entity.setVelocity(velocity);
    }

    private static DamageSource createPalmNeedlesDamageSource(World world) {
        return new DamageSource(world.getRegistryManager()
                .get(RegistryKeys.DAMAGE_TYPE)
                .entryOf(PALM_NEEDLES_DAMAGE_TYPE));
    }
}
