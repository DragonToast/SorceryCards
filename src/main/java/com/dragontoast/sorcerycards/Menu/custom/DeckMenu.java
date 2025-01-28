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
    public DeckMenu(int pContainerId, Inventory pPlayerInventory, Container container) {
        this(pContainerId, pPlayerInventory, new SimpleContainer(27), container);
    }

    public DeckMenu(int pContainerId, Inventory pPlayerInventory) {
        this(pContainerId, pPlayerInventory, new SimpleContainer(27));
    }

    public DeckMenu(int pContainerId, Inventory pPlayerInventory, Container pContainer, Container container){
        super(ModMenuTypes.DECK.get(), pContainerId);
        checkContainerSize(pContainer, 27);
        this.container = container;
        pContainer.startOpen(pPlayerInventory.player);

        for (int row = 0; row < 3; row++) {
            for (int collumn = 0; collumn < 9; collumn++) {
                this.addSlot(new ShulkerBoxSlot(pContainer, collumn + row * 9, 8 + collumn * 18, 18 + row * 18));
            }
        }

        for (int row = 0; row < 3; row++) {
            for (int collumn = 0; collumn < 9; collumn++) {
                this.addSlot(new Slot(pPlayerInventory, collumn + row * 9 + 9, 8 + collumn * 18, 84 + row * 18));
            }
        }

        for (int collumn = 0; collumn < 9; collumn++) {
            this.addSlot(new Slot(pPlayerInventory, collumn, 8 + collumn * 18, 142));
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
