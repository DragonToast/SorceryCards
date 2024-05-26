package com.dragontoast.sorcerycards.Block;

import com.dragontoast.sorcerycards.Item.ModItems;
import com.dragontoast.sorcerycards.SorceryCards;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(SorceryCards.MODID);


    public static final DeferredBlock DECK = registerBlock("deck", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DRIED_KELP_BLOCK)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> DeferredItem<Item> registerBlockItem (String name, DeferredBlock<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register (IEventBus eventbus){
        BLOCKS.register(eventbus);
    }
}