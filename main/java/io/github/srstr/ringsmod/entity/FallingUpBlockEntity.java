package io.github.srstr.ringsmod.entity;

import net.minecraft.block.*;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.DirectionalPlaceContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class FallingUpBlockEntity extends FallingBlockEntity {
    private BlockState fallTile = Blocks.SAND.getDefaultState();
    private boolean dontSetBlock;
    private boolean hurtEntities;
    private int fallHurtMax = 40;
    private float fallHurtAmount = 2.0F;

    public FallingUpBlockEntity(World worldIn, double x, double y, double z, BlockState fallingBlockState){
        super(worldIn, x, y, z, fallingBlockState);
    }
//
//    public void tick() {
//        if (this.fallTile.isAir()) {
//            this.remove();
//        } else {
//            Block block = this.fallTile.getBlock();
//            if (this.fallTime++ == 0) {
//                BlockPos blockpos = this.func_233580_cy_();
//                if (this.world.getBlockState(blockpos).isIn(block)) {
//                    this.world.removeBlock(blockpos, false);
//                } else if (!this.world.isRemote) {
//                    this.remove();
//                    return;
//                }
//            }
//
//            if (!this.hasNoGravity()) {
//                this.setMotion(this.getMotion().add(0.0D, -0.04D, 0.0D));
//            }
//
//            this.move(MoverType.SELF, this.getMotion());
//            if (!this.world.isRemote) {
//                BlockPos blockpos1 = this.func_233580_cy_();
//                boolean flag = this.fallTile.getBlock() instanceof ConcretePowderBlock;
//                boolean flag1 = flag && this.world.getFluidState(blockpos1).isTagged(FluidTags.WATER);
//                double d0 = this.getMotion().lengthSquared();
//                if (flag && d0 > 1.0D) {
//                    BlockRayTraceResult blockraytraceresult = this.world.rayTraceBlocks(new RayTraceContext(new Vector3d(this.prevPosX, this.prevPosY, this.prevPosZ), this.getPositionVec(), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.SOURCE_ONLY, this));
//                    if (blockraytraceresult.getType() != RayTraceResult.Type.MISS && this.world.getFluidState(blockraytraceresult.getPos()).isTagged(FluidTags.WATER)) {
//                        blockpos1 = blockraytraceresult.getPos();
//                        flag1 = true;
//                    }
//                }
//
//                if (!this.onGround && !flag1) {
//                    if (!this.world.isRemote && (this.fallTime > 100 && (blockpos1.getY() < 1 || blockpos1.getY() > 256) || this.fallTime > 600)) {
//                        if (this.shouldDropItem && this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
//                            this.entityDropItem(block);
//                        }
//
//                        this.remove();
//                    }
//                } else {
//                    BlockState blockstate = this.world.getBlockState(blockpos1);
//                    this.setMotion(this.getMotion().mul(0.7D, 0.5D, 0.7D));
//                    if (!blockstate.isIn(Blocks.MOVING_PISTON)) {
//                        this.remove();
//                        if (!this.dontSetBlock) {
//                            boolean flag2 = blockstate.isReplaceable(new DirectionalPlaceContext(this.world, blockpos1, Direction.UP, ItemStack.EMPTY, Direction.DOWN));
//                            boolean flag3 = FallingBlock.canFallThrough(this.world.getBlockState(blockpos1.up())) && (!flag || !flag1);
//                            boolean flag4 = this.fallTile.isValidPosition(this.world, blockpos1) && !flag3;
//                            if (flag2 && flag4) {
//                                if (this.fallTile.func_235901_b_(BlockStateProperties.WATERLOGGED) && this.world.getFluidState(blockpos1).getFluid() == Fluids.WATER) {
//                                    this.fallTile = this.fallTile.with(BlockStateProperties.WATERLOGGED, Boolean.valueOf(true));
//                                }
//
//                                if (this.world.setBlockState(blockpos1, this.fallTile, 3)) {
//                                    if (block instanceof FallingBlock) {
//                                        ((FallingBlock)block).onEndFalling(this.world, blockpos1, this.fallTile, blockstate, this);
//                                    }
//
//                                    if (this.tileEntityData != null && this.fallTile.hasTileEntity()) {
//                                        TileEntity tileentity = this.world.getTileEntity(blockpos1);
//                                        if (tileentity != null) {
//                                            CompoundNBT compoundnbt = tileentity.write(new CompoundNBT());
//
//                                            for(String s : this.tileEntityData.keySet()) {
//                                                INBT inbt = this.tileEntityData.get(s);
//                                                if (!"x".equals(s) && !"y".equals(s) && !"z".equals(s)) {
//                                                    compoundnbt.put(s, inbt.copy());
//                                                }
//                                            }
//
//                                            tileentity.func_230337_a_(this.fallTile, compoundnbt);
//                                            tileentity.markDirty();
//                                        }
//                                    }
//                                } else if (this.shouldDropItem && this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
//                                    this.entityDropItem(block);
//                                }
//                            } else if (this.shouldDropItem && this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
//                                this.entityDropItem(block);
//                            }
//                        } else if (block instanceof FallingBlock) {
//                            ((FallingBlock)block).onBroken(this.world, blockpos1, this);
//                        }
//                    }
//                }
//            }
//
//            this.setMotion(this.getMotion().scale(0.98D));
//        }
//    }
}
