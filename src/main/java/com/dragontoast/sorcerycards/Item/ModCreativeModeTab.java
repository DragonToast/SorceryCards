package com.dragontoast.sorcerycards.Item;

import com.dragontoast.sorcerycards.Block.ModBlocks;
import com.dragontoast.sorcerycards.Item.components.CardRecord;
import com.dragontoast.sorcerycards.Item.components.ModDataComponents;
import com.dragontoast.sorcerycards.Item.custom.CardItem;
import com.dragontoast.sorcerycards.SorceryCards;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SorceryCards.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SORCERYCARDS_TAB =
            CREATIVE_MODE_TAB.register("sorcerycards", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.CARD.get())).title(Component.translatable("creativetab.sorcerycards_tab"))
                    .displayItems(((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.TOURMALINE.get());
                        pOutput.accept(ModItems.RAW_TOURMALINE.get());

                        pOutput.accept(ModBlocks.DECK);
                        pOutput.accept(ModBlocks.TOURMALINE_BLOCK);
                        pOutput.accept(ModBlocks.TOURMALINE_STONE_ORE);
                        pOutput.accept(ModBlocks.TOURMALINE_DEEPSLATE_ORE);

                        ItemStack stack = ModItems.CARD.toStack();
                        stack.set(ModDataComponents.CARD, new CardRecord(false, 4, 13));
                        pOutput.accept(stack);
                        for(int a = 0; a < 4; a++){
                            for(int b = 0; b < 13; b++){
                                stack = ModItems.CARD.toStack();
                                stack.set(ModDataComponents.CARD, new CardRecord(false, b, a));
                                pOutput.accept(stack);
                            }
                        }
                    }))
                    .build());
    public static void register (IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
