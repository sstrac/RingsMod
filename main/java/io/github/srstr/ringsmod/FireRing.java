package io.github.srstr.ringsmod;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.*;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.Effects;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public class FireRing extends Ring{
    private FireRing(int level){
        super(Enchantments.FIRE_PROTECTION,
                Enchantments.FIRE_PROTECTION,
                Enchantments.FIRE_PROTECTION,
                Enchantments.FIRE_PROTECTION,
                Enchantments.FIRE_ASPECT,
                level,
                Effects.FIRE_RESISTANCE);
    }

    public static FireRing create() { return create(1); }
    public static FireRing create(int level) {
        return new FireRing(level);
    }

    public ActionResultType onItemUse(ItemUseContext context) {
        if (Ring.itemUseIsValid(context.getHand())){
            return ignite(context);
        } else {
            return ActionResultType.FAIL;
        }
    }

    private ActionResultType ignite(ItemUseContext context){
        PlayerEntity playerentity = context.getPlayer();
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        BlockState blockstate = world.getBlockState(blockpos);
        if (CampfireBlock.func_241470_h_(blockstate)) {
            world.playSound(playerentity, blockpos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
            world.setBlockState(blockpos, blockstate.with(BlockStateProperties.LIT, Boolean.valueOf(true)), 11);
            if (playerentity != null) {
                context.getItem().damageItem(1, playerentity, (p_219999_1_) -> {
                    p_219999_1_.sendBreakAnimation(context.getHand());
                });
            }

            return ActionResultType.func_233537_a_(world.isRemote());
        } else {
            final BlockPos blockpos1 = blockpos.offset(context.getFace());
            ArrayList<BlockPos> surroundingBlocks = findValidBlocks(blockstate, world, blockpos1);
            if (AbstractFireBlock.func_241465_a_(world, blockpos1, context.getPlacementHorizontalFacing())) {
                world.playSound(playerentity, blockpos1, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
                surroundingBlocks.forEach(blockPos -> {
                    BlockState blockState = AbstractFireBlock.func_235326_a_(world, blockPos);
                    world.setBlockState(blockPos, blockState, 11);
                });
                ItemStack itemstack = context.getItem();
                if (playerentity instanceof ServerPlayerEntity) {
                    CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity)playerentity, blockpos1, itemstack);
                    itemstack.damageItem(1, playerentity, (p_219998_1_) -> {
                        p_219998_1_.sendBreakAnimation(context.getHand());
                    });
                }

                return ActionResultType.func_233537_a_(world.isRemote());
            } else {
                return ActionResultType.FAIL;
            }
        }
    }

    /**
     * Checks if a block can be ignited based on whether the block is air and the
     * block below is solid
     * @return
     */
    private boolean validIgnition(World world, BlockPos blockPos){
        //check if block same y is air and block below is not air
        BlockState blockState = world.getBlockState(blockPos);
        BlockState belowBlockState = world.getBlockState(blockPos.down());
        return blockState.isAir(world, blockPos) && belowBlockState.isSolid();
    }

    /**
     * Get list of block which can be ignited in surrounding area from item used block
     * @param blockState
     * @param world
     * @param blockPos
     * @return
     */
    private ArrayList<BlockPos> findValidBlocks(BlockState blockState, World world, BlockPos blockPos){
        ArrayList<BlockPos> surroundingBlocks = new ArrayList<>();
        if(validIgnition(world, blockPos))
            surroundingBlocks.add(blockPos);
        if(validIgnition(world, blockPos.east()))
            surroundingBlocks.add(blockPos.east());
        if (validIgnition(world, blockPos.west()))
            surroundingBlocks.add(blockPos.west());
        if (validIgnition(world, blockPos.north()))
            surroundingBlocks.add(blockPos.north());
        if (validIgnition(world, blockPos.south()))
            surroundingBlocks.add(blockPos.south());
        if (validIgnition(world, blockPos.east().up()))
            surroundingBlocks.add(blockPos.east().up());
        if (validIgnition(world, blockPos.west().up()))
            surroundingBlocks.add(blockPos.west().up());
        if (validIgnition(world, blockPos.north().up()))
            surroundingBlocks.add(blockPos.north().up());
        if (validIgnition(world, blockPos.south().up()))
            surroundingBlocks.add(blockPos.south().up());
        if (validIgnition(world, blockPos.east().down()))
            surroundingBlocks.add(blockPos.east().down());
        if (validIgnition(world, blockPos.west().down()))
            surroundingBlocks.add(blockPos.west().down());
        if (validIgnition(world, blockPos.north().down()))
            surroundingBlocks.add(blockPos.north().down());
        if (validIgnition(world, blockPos.south().down()))
            surroundingBlocks.add(blockPos.south().down());
        if (validIgnition(world, blockPos.add(1,0,1)))
            surroundingBlocks.add(blockPos.add(1,0,1));
        if (validIgnition(world, blockPos.add(-1,0,1)))
            surroundingBlocks.add(blockPos.add(-1,0,1));
        if (validIgnition(world, blockPos.add(1,0,-1)))
            surroundingBlocks.add(blockPos.add(1,0,-1));
        if (validIgnition(world, blockPos.add(-1,0,-1)))
            surroundingBlocks.add(blockPos.add(-1,0,-1));
        if (validIgnition(world, blockPos.add(1,1,1)))
            surroundingBlocks.add(blockPos.add(1,1,1));
        if (validIgnition(world, blockPos.add(-1,1,1)))
            surroundingBlocks.add(blockPos.add(-1,1,1));
        if (validIgnition(world, blockPos.add(1,1,-1)))
            surroundingBlocks.add(blockPos.add(1,1,-1));
        if (validIgnition(world, blockPos.add(-1,1,-1)))
            surroundingBlocks.add(blockPos.add(-1,1,-1));
        if (validIgnition(world, blockPos.add(1,-1,1)))
            surroundingBlocks.add(blockPos.add(1,-1,1));
        if (validIgnition(world, blockPos.add(-1,-1,1)))
            surroundingBlocks.add(blockPos.add(-1,-1,1));
        if (validIgnition(world, blockPos.add(1,-1,-1)))
            surroundingBlocks.add(blockPos.add(1,-1,-1));
        if (validIgnition(world, blockPos.add(-1,-1,-1)))
            surroundingBlocks.add(blockPos.add(-1,-1,-1));
        return surroundingBlocks;
    }
}