package com.dragontoast.sorcerycards.Slot.custom;

import com.dragontoast.sorcerycards.Item.components.ModDataComponents;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class DeckSlot extends Slot {
    public int slot;
    public DeckSlot(Container pContainer, int pSlot, int pX, int pY) {
        super(pContainer, pSlot, pX, pY);
        slot = pSlot;
    }

    @Override
    public boolean mayPlace(ItemStack pStack) {
        if(pStack.getComponents().get(ModDataComponents.CARD.get()).value() + pStack.getComponents().get(ModDataComponents.CARD.get()).suitValue()*13 == slot) {
            return true;
        }
        else return false;
    }
}
