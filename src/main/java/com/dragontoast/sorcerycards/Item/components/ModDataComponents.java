package com.dragontoast.sorcerycards.Item.components;

import com.dragontoast.sorcerycards.Item.custom.CardItem;
import com.dragontoast.sorcerycards.SorceryCards;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDataComponents {
    public static final DeferredRegister.DataComponents DATA_COMPONENT =
            DeferredRegister.createDataComponents(SorceryCards.MODID);

    public static final Codec<CardRecord> BASIC_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.BOOL.fieldOf("is_active").forGetter(CardRecord::isActive),
                    Codec.INT.fieldOf("value").forGetter(CardRecord::value),
                    Codec.INT.fieldOf("suitValue").forGetter(CardRecord::suitValue)
            ).apply(instance, CardRecord::new)
    );
    public static final StreamCodec<ByteBuf, CardRecord> BASIC_STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, CardRecord::isActive,
            ByteBufCodecs.INT, CardRecord::value,
            ByteBufCodecs.INT, CardRecord::suitValue,
            CardRecord::new
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CardRecord>> CARD =
            DATA_COMPONENT.registerComponentType("is_active", builder -> builder
                    .persistent(BASIC_CODEC)
                    .networkSynchronized(BASIC_STREAM_CODEC)
    );
}
