package com.dragontoast.sorcerycards.Item.custom;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CardItem extends Item {


    private final int cardValue;
    private final String cardSuit;
    private boolean isActive = false;
    private boolean changedIsActive = false;

    public CardItem(Properties pProperties, int pCardValue, String pCardSuit) {
        super(pProperties);
        cardValue = pCardValue;
        cardSuit = pCardSuit;
    }
    @Override
    public void verifyComponentsAfterLoad(ItemStack pStack) {
        isActive = Boolean.TRUE.equals(pStack.get(DataComponents.ENCHANTMENT_GLINT_OVERRIDE));
        super.verifyComponentsAfterLoad(pStack);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (pPlayer.isShiftKeyDown() && !changedIsActive) {
            if (isActive) {
                isActive = false;
                stack.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, false);
            } else {
                isActive = true;
                stack.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true);
            }
            changedIsActive = true;
            return InteractionResultHolder.success(stack);
        }
        else if (pPlayer.isShiftKeyDown() && changedIsActive) {
            changedIsActive = false;
            return super.use(pLevel, pPlayer, pUsedHand);
        }
        else{
            return InteractionResultHolder.fail(stack);
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
                    ((Player) pEntity).addEffect(new MobEffectInstance(effect, 20, cardValue));
                }
            }
        }
    }
}
