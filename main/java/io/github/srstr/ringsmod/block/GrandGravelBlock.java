package io.github.srstr.ringsmod.block;
import net.minecraft.block.material.Material;

public class GrandGravelBlock extends RisingBlock{
    private GrandGravelBlock(Properties properties) {
        super(properties);
    }
    public static GrandGravelBlock create(){
        return new GrandGravelBlock(Properties.create(Material.SAND));
    }
}
