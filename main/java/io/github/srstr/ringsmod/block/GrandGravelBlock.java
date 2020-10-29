package io.github.srstr.ringsmod.block;

import io.github.srstr.ringsmod.entity.FallingUpBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.GravelBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;


public class GrandGravelBlock extends GravelBlock {
    private GrandGravelBlock(Properties properties) {
        super(properties);
    }

    public static GrandGravelBlock create(){
        return new GrandGravelBlock(Properties.create(Material.SAND));
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if (worldIn.isAirBlock(pos.down()) || canFallThrough(worldIn.getBlockState(pos.down())) && pos.getY() >= 0) {
            FallingBlockEntity fallingblockentity = new FallingUpBlockEntity(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, worldIn.getBlockState(pos));
            this.onStartFalling(fallingblockentity);
            worldIn.addEntity(fallingblockentity);
        }
    }
}
