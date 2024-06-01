package com.dragontoast.sorcerycards.Item.components;

import com.dragontoast.sorcerycards.SorceryCards;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.codecs.PrimitiveCodec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;
public class ModDataComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT =
            DeferredRegister.create(BuiltInRegistries.DATA_COMPONENT_TYPE, SorceryCards.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> IS_ACTIVE =
            DATA_COMPONENT.register("is_active", () -> DataComponents.ENCHANTMENT_GLINT_OVERRIDE
    );
}
