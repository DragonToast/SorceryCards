package com.dragontoast.sorcerycards.Item;

import com.dragontoast.sorcerycards.SorceryCards;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SorceryCards.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SORCERYCARDS_TAB =
            CREATIVE_MODE_TAB.register("sorcerycards", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.JOKER.get())).title(Component.translatable("creativetab.sorcerycards_tab"))
                    .displayItems(((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.JOKER.get());
                    }))
                    .build());
    public static void register (IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
