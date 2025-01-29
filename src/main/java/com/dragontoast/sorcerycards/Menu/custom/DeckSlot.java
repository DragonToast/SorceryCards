package com.dragontoast.sorcerycards.Menu.custom;

import com.dragontoast.sorcerycards.Item.components.ModDataComponents;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class DeckSlot extends Slot {
    public int row;
    public int column;
    public DeckSlot(Container pContainer, int pSlot, int pX, int pY, int pRow, int pColumn) {
        super(pContainer, pSlot, pX, pY);
        row = pRow;
        column = pColumn;
    }

    @Override
    public boolean mayPlace(ItemStack pStack) {
        if (pStack.has(ModDataComponents.CARD)){
            boolean rightSuit = pStack.get(ModDataComponents.CARD.get()).suitValue() == row;
            boolean rightValue = pStack.get(ModDataComponents.CARD.get()).value() == column;

            return rightSuit && rightValue;
        }
        return false;
    }
}
