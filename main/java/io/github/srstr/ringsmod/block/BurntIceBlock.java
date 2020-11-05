package io.github.srstr.ringsmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BurntIceBlock extends Block {
    private BurntIceBlock() {
        super(Properties.create(Material.ICE).hardnessAndResistance(1000000));
    }

    public static BurntIceBlock create(){
        return new BurntIceBlock();
    }
}
