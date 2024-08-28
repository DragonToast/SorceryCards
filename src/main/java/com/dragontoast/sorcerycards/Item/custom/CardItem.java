package com.dragontoast.sorcerycards.Item.custom;

import com.dragontoast.sorcerycards.Item.components.CardRecord;
import com.dragontoast.sorcerycards.Item.components.ModDataComponents;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
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

    private boolean changedIsActive = false;

    public CardItem(Properties pProperties) {
        super(pProperties.component(ModDataComponents.CARD.get(), new CardRecord(false, 0 , 0)));

    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (pPlayer.isShiftKeyDown() && !changedIsActive && !pLevel.isClientSide) {
            CardRecord record = stack.get(ModDataComponents.CARD.get());
            if(record == null)
                return InteractionResultHolder.fail(stack);
            if (record.isActive()) {
                stack.set(ModDataComponents.CARD, new CardRecord(false, record.value(), record.suitValue()));
                stack.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, false);
            } else {
                stack.set(ModDataComponents.CARD, new CardRecord(true, record.value(), record.suitValue()));
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
        if (pStack.get(ModDataComponents.CARD.get()) != null) {
            int cardSuit = pStack.get(ModDataComponents.CARD.get()).suitValue();
            int cardValue = pStack.get(ModDataComponents.CARD.get()).value();
            if (pStack.get(ModDataComponents.CARD.get()).isActive()) {
                if (pEntity instanceof Player player) {
                    Holder<MobEffect> effect = switch (cardSuit) {
                        case 0 -> MobEffects.HEALTH_BOOST;
                        case 1 -> MobEffects.DIG_SPEED;
                        case 2 -> MobEffects.MOVEMENT_SPEED;
                        case 3 -> MobEffects.JUMP;
                        case 4 -> MobEffects.NIGHT_VISION;
                        default -> MobEffects.LUCK;
                    };
                    if  (player.getEffect(effect) != null && player.getEffect(effect).getDuration() < 3) {
                        player.removeEffect(effect);
                        player.addEffect(new MobEffectInstance(effect, 40, cardValue));
                    }
                    else if (player.getEffect(effect) == null) ((Player) pEntity).addEffect(new MobEffectInstance(effect, 40, cardValue));
                }
            }
        }
    }

    @Override
    public Component getName(ItemStack pStack) {
        return Component.translatable(this.getDescriptionId(pStack) + "." + pStack.get(ModDataComponents.CARD.get()).value()+"."+pStack.get(ModDataComponents.CARD).suitValue());
    }
}
