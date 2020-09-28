package io.github.srstr.ringsmod;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class FireRing extends Item{

    public FireRing(Properties properties) {
        super(properties);
    }

    public static FireRing create(){
        return new FireRing(new Item.Properties().group(ItemGroup.TOOLS));
    };
}

