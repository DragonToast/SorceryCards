package com.dragontoast.sorcerycards.BlockEntity;

import com.dragontoast.sorcerycards.Block.ModBlocks;
import com.dragontoast.sorcerycards.BlockEntity.Custom.DeckBlockEntity;
import com.dragontoast.sorcerycards.Item.custom.CardItem;
import com.dragontoast.sorcerycards.SorceryCards;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(value = Dist.CLIENT, modid = SorceryCards.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, SorceryCards.MODID);

    public static final DeferredHolder<BlockEntityType<?>,BlockEntityType<?>> DECK = BLOCK_ENTITY_TYPES.register("deck", ()-> BlockEntityType.Builder.of(DeckBlockEntity::new, ModBlocks.DECK.get()).build(null));

    @SubscribeEvent
    public static void registerRenderer(EntityRenderersEvent.RegisterRenderers event) {
    }
}