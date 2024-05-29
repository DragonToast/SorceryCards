package com.dragontoast.sorcerycards.Item.custom;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class CardItem extends Item {


    private static final Logger log = LoggerFactory.getLogger(CardItem.class);
    private final int cardValue;
    private final String cardSuit;
    private boolean isActive = false;
    private boolean isActiveWasChanged = false;

    public CardItem(Properties pProperties, int pCardValue, String pCardSuit) {
        super(pProperties);
        cardValue = pCardValue;
        cardSuit = pCardSuit;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (pPlayer.isShiftKeyDown() && !isActiveWasChanged) {
            if (isActive) {
                isActive = false;
                isActiveWasChanged = true;
            } else {
                isActive = true;
                isActiveWasChanged = true;
            }
            return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
        }
        else if (pPlayer.isShiftKeyDown() && isActiveWasChanged) {
            isActiveWasChanged = false;
            return super.use(pLevel, pPlayer, pUsedHand);
        }
        else{
            return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
        }
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if(isActive) {
            if (pEntity instanceof Player) {
                Holder<MobEffect> effect = switch (cardSuit) {
                    case "hearts" -> MobEffects.HEALTH_BOOST;
                    case "spades" -> MobEffects.DIG_SPEED;
                    case "diamonds" -> MobEffects.SATURATION;
                    case "clubs" -> MobEffects.JUMP;
                    case "joker" -> MobEffects.MOVEMENT_SPEED;
                    default -> null;
                };
                if (effect != null) {
                    ((Player) pEntity).addEffect(new MobEffectInstance(effect, 600, cardValue));
                }
            }
        }
    }
}
