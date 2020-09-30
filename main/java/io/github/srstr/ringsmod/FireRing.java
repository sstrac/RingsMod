package io.github.srstr.ringsmod;

import net.minecraft.enchantment.Enchantments;

public class FireRing extends Ring{
    FireRing(int level){
        super(Enchantments.FIRE_PROTECTION, Enchantments.FIRE_ASPECT, level);
    }

    public static FireRing create() { return create(1); }
    public static FireRing create(int level) {
        return new FireRing(level);
    }
}

