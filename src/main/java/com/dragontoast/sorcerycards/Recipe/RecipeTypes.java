package com.dragontoast.sorcerycards.Recipe;

import com.dragontoast.sorcerycards.SorceryCards;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES
            = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, SorceryCards.MODID);
}
