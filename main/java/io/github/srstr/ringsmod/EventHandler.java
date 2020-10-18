package io.github.srstr.ringsmod;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
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
        if (deEquipEvent(event)){
            if(event.getFrom().getItem() instanceof Ring){
                Ring ring = (Ring)(event.getFrom().getItem());
                event.getEntityLiving().removePotionEffect(ring.getEffect());
                event.getEntityLiving().getEquipmentAndArmor().forEach(armor -> {
                    armor.getEnchantmentTagList().clear();
                });
            }
        }
        if(equipEvent(event)){
            if(ringIsEquipped(event.getEntityLiving())){
                Ring ring = (Ring)(event.getEntityLiving().getHeldItemOffhand().getItem());
                event.getEntityLiving().addPotionEffect(new EffectInstance(ring.getEffect(), 9999));
                event.getEntityLiving().getEquipmentAndArmor().forEach(armor -> {
                    //Additional constraints: not mainhand item, not enchanted already, not ring itself
                    if(isArmor(armor)
                            && event.getSlot()!=EquipmentSlotType.MAINHAND
                            && armor.getEnchantmentTagList().isEmpty()){
                        armor.addEnchantment(ring.getEnchantmentForItem(armor), ring.getLevel());
                        armor.addEnchantment(Enchantments.BINDING_CURSE, 1);
                    }
                });
            }
        }
        //for getting minecraft object tags for recipes
//        else{
//            if(!Objects.isNull(event.getTo().getTag()))
//                LOGGER.info(event.getTo().getTag().getString());
//        }
    }

    public static boolean equipEvent(LivingEquipmentChangeEvent event){
        return equipmentMovedEvent(event, event.getTo());
    }

    public static boolean deEquipEvent(LivingEquipmentChangeEvent event){
        return equipmentMovedEvent(event, event.getFrom());
    }
    public static boolean ringIsEquipped(LivingEntity entity){
        return entity.getHeldItemOffhand().getItem() instanceof Ring;
    }

    public static boolean equipmentMovedEvent(LivingEquipmentChangeEvent event, ItemStack item){
        if(item.getItem() instanceof ArmorItem){
            if(event.getSlot() == EquipmentSlotType.CHEST
            || event.getSlot() == EquipmentSlotType.HEAD
            || event.getSlot() == EquipmentSlotType.LEGS
            || event.getSlot() == EquipmentSlotType.FEET){
                return true;
            } else {
                return false;
            }
        }  else if(event.getSlot() == EquipmentSlotType.OFFHAND){
            if(item.getItem() instanceof Ring){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isArmor(ItemStack item){
        if(item.getDisplayName().getString().contains("Helmet")
        || item.getDisplayName().getString().contains("Chestplate")
        || item.getDisplayName().getString().contains("Leggings")
        || item.getDisplayName().getString().contains("Boots"))
            return true;
        else
            return false;
    }
}
