package io.github.srstr.ringsmod;

import io.github.srstr.ringsmod.block.BurntIceBlock;
import io.github.srstr.ringsmod.block.GrandGravelBlock;
import io.github.srstr.ringsmod.entity.RisingBlockEntity;
import io.github.srstr.ringsmod.item.FireRing;
import io.github.srstr.ringsmod.item.OceanRing;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import java.util.function.Supplier;


public class Registration {
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RingsMod.MOD_ID);
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RingsMod.MOD_ID);
    public static DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, RingsMod.MOD_ID);
    public static final EntityType<RisingBlockEntity> RISING_BLOCK_ENTITY = EntityType.Builder.<RisingBlockEntity>create(RisingBlockEntity::new, EntityClassification.MISC).build("rising_block");
    //.size(0.98F, 0.98F).func_233606_a_(10).func_233608_b_(20).


    public static void registerAll(){
        ENTITIES.register("rising_block_entity", () -> RISING_BLOCK_ENTITY);
        ModItems.init();
        ModBlocks.init();

        //registerEnergy("white_fire", RingsmodBlocks.WHITE_FIRE);
    }

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
        BLOCKS.register(eventBus);
        ENTITIES.register(eventBus);
        registerAll();
    }

    public static RegistryObject<Block> registerBlock(String name, Supplier<Block> blockSupplier){
        RegistryObject<Block> regOb = BLOCKS.register(name, blockSupplier);
        ITEMS.register(name, () -> new BlockItem(regOb.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
        return regOb;
    }

//    public static RegistryObject<Block> registerEnergy(String name, Supplier<Block> blockSupplier){
//        return BLOCKS.register(name, blockSupplier);
//    }
}