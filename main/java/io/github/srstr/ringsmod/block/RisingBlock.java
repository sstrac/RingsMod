package io.github.srstr.ringsmod.block;


import io.github.srstr.ringsmod.entity.RisingBlockEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RisingBlock extends Block {
    protected RisingBlock(AbstractBlock.Properties properties) {
        super(properties);
    }

    public void onEndFalling(World worldIn, BlockPos pos, BlockState fallingState, BlockState hitState, RisingBlockEntity p_176502_5_) {
    }

    public void onBroken(World worldIn, BlockPos pos, RisingBlockEntity p_190974_3_) {
    }

    public static boolean canFallThrough(BlockState state) {
        Material material = state.getMaterial();
        return state.isAir() || state.func_235714_a_(BlockTags.field_232872_am_) || material.isLiquid() || material.isReplaceable();
    }
}