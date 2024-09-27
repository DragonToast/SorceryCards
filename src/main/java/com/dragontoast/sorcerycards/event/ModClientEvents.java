package com.dragontoast.sorcerycards.event;

import com.dragontoast.sorcerycards.Menu.ModMenuTypes;
import com.dragontoast.sorcerycards.Menu.custom.DeckScreen;
import com.dragontoast.sorcerycards.SorceryCards;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.world.inventory.ChestMenu;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(value = Dist.CLIENT, modid = SorceryCards.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModClientEvents {

    @SubscribeEvent
    public static void clientSetup(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.DECK_MENU.get(), DeckScreen::new);
    }
}
