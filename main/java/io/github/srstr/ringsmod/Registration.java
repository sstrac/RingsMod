package io.github.srstr.ringsmod;

import net.minecraft.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class Registration {
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RingsMod.MOD_ID);

    public static void registerAll(){
        ITEMS.register("fire_ring", FireRing::create);
    }

    public static void register(){
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        registerAll();
    }
}