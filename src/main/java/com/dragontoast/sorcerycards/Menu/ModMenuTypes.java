package com.dragontoast.sorcerycards.Menu;

import com.dragontoast.sorcerycards.Menu.custom.DeckMenu;
import com.dragontoast.sorcerycards.Menu.custom.DeckScreen;
import com.dragontoast.sorcerycards.SorceryCards;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(BuiltInRegistries.MENU, SorceryCards.MODID);

    public static final Supplier<MenuType<DeckMenu>> DECK = MENUS.register("deck", () -> new MenuType<>(DeckMenu::new, FeatureFlags.VANILLA_SET));

    public static void register (IEventBus eventbus){
        MENUS.register(eventbus);
    }
}
