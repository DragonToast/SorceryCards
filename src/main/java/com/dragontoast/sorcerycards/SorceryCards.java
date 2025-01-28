package com.dragontoast.sorcerycards;

import com.dragontoast.sorcerycards.Block.ModBlocks;
import com.dragontoast.sorcerycards.Entity.ModBlockEntities;
import com.dragontoast.sorcerycards.Item.ModCreativeModeTab;
import com.dragontoast.sorcerycards.Item.ModItems;
import com.dragontoast.sorcerycards.Item.components.CardRecord;
import com.dragontoast.sorcerycards.Item.components.ModDataComponents;
import com.dragontoast.sorcerycards.Menu.ModMenuTypes;
import com.dragontoast.sorcerycards.Menu.custom.DeckScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(SorceryCards.MODID)
public class SorceryCards
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "sorcerycards";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();


    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public SorceryCards(IEventBus modEventBus, ModContainer modContainer)
    {
        ModCreativeModeTab.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModDataComponents.DATA_COMPONENT.register(modEventBus);
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::registerScreens);


        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }
    private void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.DECK.get(), DeckScreen::new);
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS){
            event.accept(ModBlocks.DECK);
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.RAW_TOURMALINE);
            event.accept(ModItems.TOURMALINE);
        }
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.accept(ModBlocks.TOURMALINE_BLOCK);

        }
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS){
            event.accept(ModBlocks.TOURMALINE_STONE_ORE);
            event.accept(ModBlocks.TOURMALINE_DEEPSLATE_ORE);
        }
    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            ItemProperties.register(ModItems.CARD.get(), new ResourceLocation(MODID, "value"),
                    (stack, world, living, i) -> {
                        CardRecord record = stack.get(ModDataComponents.CARD);
                        if (record != null)
                            return record.value();
                        else return 0;
                    });
            ItemProperties.register(ModItems.CARD.get(), new ResourceLocation(MODID, "suit_value"),
                    (stack, world, living, i) -> {
                        CardRecord record = stack.get(ModDataComponents.CARD);
                        if (record != null)
                            return record.suitValue();
                        else return 0;
                    });
        }
    }
}
