package io.github.srstr.ringsmod.block;

import net.minecraft.block.BreakableBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
public class BurntIceBlock extends BreakableBlock {
    private BurntIceBlock() {
        super(Properties.create(Material.GLASS)
                .slipperiness(0.98F)
                .tickRandomly()
                .hardnessAndResistance(10000.0F)
                .sound(SoundType.GLASS)
                .notSolid()
        );
    }

    public static BurntIceBlock create(){
        return new BurntIceBlock();
    }
}
