package com.dragontoast.sorcerycards.Menu;

import com.dragontoast.sorcerycards.Menu.custom.DeckMenu;
import com.dragontoast.sorcerycards.SorceryCards;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(BuiltInRegistries.MENU, SorceryCards.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<DeckMenu>> DECK_MENU = MENU_TYPES.register("deck",
            () -> new MenuType<>(DeckMenu::new, FeatureFlags.DEFAULT_FLAGS));
}

