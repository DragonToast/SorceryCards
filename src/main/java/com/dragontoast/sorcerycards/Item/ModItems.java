package com.dragontoast.sorcerycards.Item;

import com.dragontoast.sorcerycards.Block.ModBlocks;
import com.dragontoast.sorcerycards.Item.custom.CardItem;
import com.dragontoast.sorcerycards.Item.custom.DeckItem;
import com.dragontoast.sorcerycards.SorceryCards;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.rmi.registry.Registry;
import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SorceryCards.MODID);

    public static final DeferredItem<Item> CARD = ITEMS.register("card", () -> new CardItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> TOURMALINE = ITEMS.registerItem("tourmaline", Item::new, new Item.Properties());
    public static final DeferredItem<Item> RAW_TOURMALINE = ITEMS.registerItem("raw_tourmaline", Item::new, new Item.Properties());
    public static final DeferredItem<BlockItem> DECK = ITEMS.register("deck", () -> new DeckItem(ModBlocks.DECK.get(), new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
