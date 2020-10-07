package io.github.srstr.ringsmod;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public abstract class Ring extends Item {
    private static final Logger LOGGER = LogManager.getLogger();
    private int level;
    private HashMap<EquipmentSlotType, Enchantment> enchantments = new HashMap<>();
    private Effect playerEffect;

    public Ring(Enchantment headEnchantment,
                Enchantment chestplateEnchantment,
                Enchantment leggingsEnchantment,
                Enchantment bootsEnchantment,
                Enchantment weaponEnchantment,
                int level,
                Effect playerEffect) {
        super(new Item.Properties()
                .group(ItemGroup.TOOLS)
                .rarity(Rarity.EPIC)
                .setNoRepair()
                .maxStackSize(1));
        this.enchantments.put(EquipmentSlotType.HEAD, headEnchantment);
        this.enchantments.put(EquipmentSlotType.CHEST, chestplateEnchantment);
        this.enchantments.put(EquipmentSlotType.LEGS, leggingsEnchantment);
        this.enchantments.put(EquipmentSlotType.FEET, bootsEnchantment);
        this.enchantments.put(EquipmentSlotType.MAINHAND, weaponEnchantment);
        this.level = level;
        this.playerEffect = playerEffect;
    }

    public int getLevel() {
        return level;
    }

    public Effect getEffect(){ return this.playerEffect; }

    public Enchantment getEnchantmentForSlot(EquipmentSlotType slot){
        return this.enchantments.get(slot);
    }

    public Enchantment getEnchantmentForItem(ItemStack item){
        if(item.getDisplayName().getString().contains("Helmet")){
            return getEnchantmentForSlot(EquipmentSlotType.HEAD);
        } else if(item.getDisplayName().getString().contains("Chestplate")){
            return getEnchantmentForSlot(EquipmentSlotType.CHEST);
        } else if(item.getDisplayName().getString().contains("Leggings")){
            return getEnchantmentForSlot(EquipmentSlotType.LEGS);
        } else if(item.getDisplayName().getString().contains("Boots")){
            return getEnchantmentForSlot(EquipmentSlotType.FEET);
        } else {
            LOGGER.error("No valid enchantment for item" + item.getDisplayName().getString());
            return null;
        }
    }
}
