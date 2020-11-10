package io.github.srstr.ringsmod;

import io.github.srstr.ringsmod.item.FireRing;
import io.github.srstr.ringsmod.item.OceanRing;
import net.minecraftforge.fml.RegistryObject;
import net.minecraft.item.Item;

import static io.github.srstr.ringsmod.Registration.ITEMS;

public class ModItems {
        public static RegistryObject<Item> FIRE_RING_1 = ITEMS.register("fire_ring_1", FireRing::create);
        public static RegistryObject<Item> FIRE_RING_2 = ITEMS.register("fire_ring_2", () -> FireRing.create(2));
        public static RegistryObject<Item> FIRE_RING_3 = ITEMS.register("fire_ring_3", () -> FireRing.create(3));
        public static RegistryObject<Item> FIRE_RING_4 = ITEMS.register("fire_ring_4", () -> FireRing.create(4));
        public static RegistryObject<Item> OCEAN_RING_1 = ITEMS.register("ocean_ring_1", OceanRing::create);
        public static RegistryObject<Item> OCEAN_RING_2 = ITEMS.register("ocean_ring_2", () -> OceanRing.create(2));
        public static RegistryObject<Item> OCEAN_RING_3 = ITEMS.register("ocean_ring_3", () -> OceanRing.create(3));

        public ModItems(){}

        public static ModItems init(){
            return new ModItems();
        }
}
