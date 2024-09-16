package com.dragontoast.sorcerycards.Menu;

import com.dragontoast.sorcerycards.Block.ModBlocks;
import com.dragontoast.sorcerycards.BlockEntity.Custom.DeckBlockEntity;
import com.dragontoast.sorcerycards.Menu.custom.DeckMenu;
import com.dragontoast.sorcerycards.SorceryCards;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(BuiltInRegistries.MENU, SorceryCards.MODID);

    public static final Supplier<MenuType<DeckMenu>> DECK_MENU = MENU_TYPES.register("deck", () -> new MenuType(DeckMenu::new, FeatureFlags.DEFAULT_FLAGS));
}

