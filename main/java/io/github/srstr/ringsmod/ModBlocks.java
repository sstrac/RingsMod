package io.github.srstr.ringsmod;

import io.github.srstr.ringsmod.block.BurntIceBlock;
import io.github.srstr.ringsmod.block.GrandGravelBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;

public class ModBlocks {
    public static final RegistryObject<Block> GRAND_GRAVEL = Registration. registerBlock("grand_gravel",GrandGravelBlock::create);
    public static final RegistryObject<Block> BURNT_ICE_BLOCK = Registration.registerBlock("burnt_ice",BurntIceBlock::create);

    public ModBlocks(){}

    public static ModBlocks init(){
        return new ModBlocks();
    }
}
