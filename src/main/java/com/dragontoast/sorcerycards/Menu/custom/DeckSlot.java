package com.dragontoast.sorcerycards.Menu.custom;

import com.dragontoast.sorcerycards.Item.components.ModDataComponents;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class DeckSlot extends Slot {
    public DeckSlot(Container pContainer, int pSlot, int pX, int pY) {
        super(pContainer, pSlot, pX, pY);
    }

    @Override
    public boolean mayPlace(ItemStack pStack) {
        if (pStack.has(ModDataComponents.CARD)){
            return pStack.getComponents().get(ModDataComponents.CARD.get()).suitValue() == 1;
        }
        return false;
    }
}
