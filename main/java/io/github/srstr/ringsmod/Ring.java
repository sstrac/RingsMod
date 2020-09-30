package io.github.srstr.ringsmod;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;

public abstract class Ring extends Item {
    public static int level;
    public static Enchantment armorEnchantment;
    public static Enchantment weaponEnchantment;

    public Ring(Enchantment armorEnchantment, Enchantment weaponEnchantment, int level) {
        super(new Item.Properties()
                .group(ItemGroup.TOOLS)
                .rarity(Rarity.EPIC)
                .setNoRepair()
                .maxStackSize(1));
        Ring.armorEnchantment = armorEnchantment;
        Ring.weaponEnchantment = weaponEnchantment;
        Ring.level = level;
    }
}
