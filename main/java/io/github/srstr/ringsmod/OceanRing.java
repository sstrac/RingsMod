package io.github.srstr.ringsmod;

import com.google.common.collect.Lists;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Queue;

public class OceanRing extends Ring{
    private OceanRing(int level){
        super(Enchantments.AQUA_AFFINITY,
                null,
                null,
                Enchantments.DEPTH_STRIDER,
                null, level,
                Effects.NIGHT_VISION);
    }

    public static OceanRing create() { return create(1); }
    public static OceanRing create(int level) {
        return new OceanRing(level);
    }

    public ActionResultType onItemUse(ItemUseContext context){
        absorb(context.getWorld(), context.getPos());
        return ActionResultType.PASS;
    }

    private boolean absorb(World worldIn, BlockPos pos) {
        Queue<Tuple<BlockPos, Integer>> queue = Lists.newLinkedList();
        queue.add(new Tuple<>(pos, 0));
        int i = 0;

        while(!queue.isEmpty()) {
            Tuple<BlockPos, Integer> tuple = queue.poll();
            BlockPos blockpos = tuple.getA();
            int j = tuple.getB();

            for(Direction direction : Direction.values()) {
                BlockPos blockpos1 = blockpos.offset(direction);
                BlockState blockstate = worldIn.getBlockState(blockpos1);
                FluidState fluidstate = worldIn.getFluidState(blockpos1);
                Material material = blockstate.getMaterial();
                if (fluidstate.isTagged(FluidTags.WATER)) {
                    if (blockstate.getBlock() instanceof IBucketPickupHandler && ((IBucketPickupHandler)blockstate.getBlock()).pickupFluid(worldIn, blockpos1, blockstate) != Fluids.EMPTY) {
                        ++i;
                        if (j < 6) {
                            queue.add(new Tuple<>(blockpos1, j + 1));
                        }
                    } else if (blockstate.getBlock() instanceof FlowingFluidBlock) {
                        worldIn.setBlockState(blockpos1, Blocks.AIR.getDefaultState(), 3);
                        ++i;
                        if (j < 6) {
                            queue.add(new Tuple<>(blockpos1, j + 1));
                        }
                    } else if (material == Material.OCEAN_PLANT || material == Material.SEA_GRASS) {
                        TileEntity tileentity = blockstate.hasTileEntity() ? worldIn.getTileEntity(blockpos1) : null;
                        worldIn.setBlockState(blockpos1, Blocks.AIR.getDefaultState(), 3);
                        ++i;
                        if (j < 6) {
                            queue.add(new Tuple<>(blockpos1, j + 1));
                        }
                    }
                }
            }

            if (i > 64) {
                break;
            }
        }

        return i > 0;
    }
}

