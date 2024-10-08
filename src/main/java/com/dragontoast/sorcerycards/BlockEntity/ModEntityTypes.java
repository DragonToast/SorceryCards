package com.dragontoast.sorcerycards.BlockEntity;

import com.dragontoast.sorcerycards.Block.ModBlocks;
import com.dragontoast.sorcerycards.BlockEntity.custom.DeckBlockEntity;
import com.dragontoast.sorcerycards.SorceryCards;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, SorceryCards.MODID);

    public static final DeferredHolder<BlockEntityType<?>,BlockEntityType<DeckBlockEntity>> DECK = BLOCK_ENTITY_TYPES.register("deck", ()-> BlockEntityType.Builder.of(DeckBlockEntity::new, ModBlocks.DECK.get()).build(null));
}