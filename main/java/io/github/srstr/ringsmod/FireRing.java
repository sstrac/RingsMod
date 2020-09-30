package io.github.srstr.ringsmod;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class FireRing extends Ring{
    FireRing(){
        super(Enchantments.FIRE_PROTECTION, Enchantments.FIRE_ASPECT);
    }

    public static FireRing create() {
        return new FireRing();
    }
}

