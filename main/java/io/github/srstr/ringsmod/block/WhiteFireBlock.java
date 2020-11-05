package io.github.srstr.ringsmod.block;

//import io.github.srstr.ringsmod.item.RingsmodBlocks;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.block.material.Material;

public class WhiteFireBlock extends FireBlock {
    private final Object2IntMap<Block> encouragements = new Object2IntOpenHashMap<>();
    private final Object2IntMap<Block> flammabilities = new Object2IntOpenHashMap<>();

    public WhiteFireBlock() {
        super(Properties.create(Material.FIRE));
    }

    public static WhiteFireBlock create(){ return new WhiteFireBlock(); }

    private void setFireInfo(Block blockIn, int encouragement, int flammability) {
        if (blockIn == Blocks.AIR) throw new IllegalArgumentException("Tried to set air on fire... This is bad.");
        this.encouragements.put(blockIn, encouragement);
        this.flammabilities.put(blockIn, flammability);
    }

//    @Override
//    public static void init() {
//        FireBlock fireblock = (FireBlock) RingsmodBlocks.WHITE_FIRE;
//        fireblock.setFireInfo(, 30, 20);
//    }
}
