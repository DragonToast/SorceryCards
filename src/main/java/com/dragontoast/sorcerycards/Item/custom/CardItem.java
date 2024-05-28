package com.dragontoast.sorcerycards.Item.custom;

import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class CardItem extends Item {


    private static final Logger log = LoggerFactory.getLogger(CardItem.class);
    private final int cardValue;
    private final String cardSuit;
    private final CompoundTag isActive = new CompoundTag();

    public CardItem(Properties pProperties, int pCardValue, String pCardSuit) {
        super(pProperties);
        cardValue = pCardValue;
        cardSuit = pCardSuit;
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        super.onUseTick(pLevel, pLivingEntity, pStack, pRemainingUseDuration);

        if (isActive.getBoolean("isActive")){
            isActive.putBoolean("isActive", false);
            pLivingEntity.sendSystemMessage(Component.literal("made false"));
        }
        else{
            isActive.putBoolean("isActive", true);
            pLivingEntity.sendSystemMessage(Component.literal("made true"));
        }
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
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
