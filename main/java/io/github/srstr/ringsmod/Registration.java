package io.github.srstr.ringsmod;

import io.github.srstr.ringsmod.block.GrandGravelBlock;
import io.github.srstr.ringsmod.item.FireRing;
import io.github.srstr.ringsmod.item.OceanRing;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class Registration {
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RingsMod.MOD_ID);
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RingsMod.MOD_ID);

    public static void registerAll(){
        ITEMS.register("fire_ring_1", FireRing::create);
        ITEMS.register("fire_ring_2", () -> FireRing.create(2));
        ITEMS.register("fire_ring_3", () -> FireRing.create(3));
        ITEMS.register("fire_ring_4", () -> FireRing.create(4));
        ITEMS.register("ocean_ring_1", OceanRing::create);
        ITEMS.register("ocean_ring_2", () -> OceanRing.create(2));
        ITEMS.register("ocean_ring_3", () -> OceanRing.create(3));
        registerBlock("grand_gravel", GrandGravelBlock::create);
        //BLOCKS.register("grand_gravel", () -> new Block(AbstractBlock.Properties.create(Material.SAND)));
        //Registry.register(Registry.CARVER, "ringsmod:catacombs", catacomb_carver);
    }

    public static void register(){
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(eventBus);
        BLOCKS.register(eventBus);

        registerAll();
    }

    public static RegistryObject<Block> registerBlock(String name, Supplier<Block> blockSupplier){
        RegistryObject<Block> regOb = BLOCKS.register(name, blockSupplier);
        ITEMS.register(name, () -> new BlockItem(regOb.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
        return regOb;
    }
}