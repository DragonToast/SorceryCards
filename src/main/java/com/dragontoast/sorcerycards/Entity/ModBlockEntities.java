package com.dragontoast.sorcerycards.Entity;

import com.dragontoast.sorcerycards.Block.ModBlocks;
import com.dragontoast.sorcerycards.Entity.custom.DeckBlockEntity;
import com.dragontoast.sorcerycards.SorceryCards;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;
import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, SorceryCards.MODID);

    public static final Supplier<BlockEntityType<DeckBlockEntity>> DECK_BLOCK_ENTITY =
            BLOCK_ENTITY_TYPES.register("deck_block_entity",
                    () -> BlockEntityType.Builder.of(DeckBlockEntity::new, (Block) ModBlocks.DECK.get()).build(null));

    public static void register (IEventBus eventbus){
        BLOCK_ENTITY_TYPES.register(eventbus);
    }
}
