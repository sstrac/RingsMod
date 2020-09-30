package io.github.srstr.ringsmod;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber
public class EventHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public static void handleEquipmentChange(LivingEquipmentChangeEvent event){
        if(armorEquippedEvent(event)){
            if(ringEquipped(event.getEntityLiving())){
                if(event.getTo().getEnchantmentTagList().isEmpty()) {
                    event.getTo().addEnchantment(FireRing.armorEnchantment, 1);
                    event.getTo().addEnchantment(Enchantments.BINDING_CURSE, 1);
                }
            }
        }
        else if (ringDeEquippedEvent(event)){
            event.getEntityLiving().getEquipmentAndArmor().forEach(armor -> {
                armor.getEnchantmentTagList().clear();
            });
        }
        else if (ringEquippedEvent(event)){
            event.getEntityLiving().getEquipmentAndArmor().forEach(armor -> {
                event.getTo().addEnchantment(FireRing.armorEnchantment, 1);
                event.getTo().addEnchantment(Enchantments.BINDING_CURSE, 1);
            });
        }
    }

    public static boolean armorEquippedEvent(LivingEquipmentChangeEvent event){
        return equipmentMovedEvent(event, event.getTo());
    }

    public static boolean ringDeEquippedEvent(LivingEquipmentChangeEvent event){
        return equipmentMovedEvent(event, event.getFrom());
    }
    public static boolean ringEquippedEvent(LivingEquipmentChangeEvent event){
        return equipmentMovedEvent(event, event.getTo());
    }

    public static boolean equipmentMovedEvent(LivingEquipmentChangeEvent event, ItemStack item){
        boolean result = false;
        if(event.getSlot() == EquipmentSlotType.CHEST){
            if(item.getDisplayName().getString().contains("Chestplate")){
                result =  true;
            }
        } else if(event.getSlot() == EquipmentSlotType.HEAD){
            if(item.getDisplayName().getString().contains("Helmet")){
                result =  true;
            }
        } else if(event.getSlot() == EquipmentSlotType.LEGS){
            if(item.getDisplayName().getString().contains("Leggings")){
                result =  true;
            }
        } else if(event.getSlot() == EquipmentSlotType.FEET){
            if(item.getDisplayName().getString().contains("Boots")){
                result =  true;
            }
        } else if(event.getSlot() == EquipmentSlotType.OFFHAND){
            if(item.getDisplayName().getString().contains("Ring")){
                result = true;
            }
        }
        return result;
    }

    public static boolean ringEquipped(LivingEntity entity){
        return entity.getHeldItemOffhand().getDisplayName().getString().contains("Ring");
    }
}
