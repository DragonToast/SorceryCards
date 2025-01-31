package com.dragontoast.sorcerycards.Menu.custom;

import com.dragontoast.sorcerycards.Menu.ModMenuTypes;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ShulkerBoxSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class DeckMenu extends AbstractContainerMenu {
    private final Container container;
    public DeckMenu(int pContainerId, Inventory pPlayerInventory) {
        this(pContainerId, pPlayerInventory, new SimpleContainer(54));
    }

    public DeckMenu(int pContainerId, Inventory pPlayerInventory, Container container){
        super(ModMenuTypes.DECK.get(), pContainerId);
        checkContainerSize(container, 54);
        this.container = container;
        container.startOpen(pPlayerInventory.player);

        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 13; column++) {
                this.addSlot(new DeckSlot(container, column + row * 13, 8 + column * 18, 18 + row * 18, row, column));
            }
        }
        for (int column = 0; column < 2; column++){
            this.addSlot(new DeckSlot(container, column + 52 /* 4*13 */, 107 + column * 18 /* 8+5*18+9 */, 90 /* 18+4*18 */, 4, column));
        }

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                this.addSlot(new Slot(pPlayerInventory, column + row * 9 + 9, 44 + column * 18 /* 8+2*18 */, 120 + row * 18 /* 84+2*18 */));
            }
        }

        for (int column = 0; column < 9; column++) {
            this.addSlot(new Slot(pPlayerInventory, column, 44 + column * 18 /* 8+2*18 */, 178 /* 142+2*18 */));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(pIndex);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (pIndex < this.container.getContainerSize()) {
                if (!this.moveItemStackTo(itemstack1, this.container.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, this.container.getContainerSize(), false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return this.container.stillValid(pPlayer);
    }

    @Override
    public void removed(Player pPlayer) {
        super.removed(pPlayer);
        this.container.stopOpen(pPlayer);
    }
}
