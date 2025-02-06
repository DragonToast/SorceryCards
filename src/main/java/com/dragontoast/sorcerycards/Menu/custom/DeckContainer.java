package com.dragontoast.sorcerycards.Menu.custom;

import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;

import java.util.List;

public class DeckContainer extends SimpleContainer {

    protected ItemStack deck;
    protected NonNullList<ItemStack> items;
    protected int size;

    public DeckContainer(ItemStack deck) {
        this.deck = deck;
        this.size = 54;
        this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);

        ItemContainerContents contents = deck.get(DataComponents.CONTAINER);

        if (contents != null) {
            contents.copyInto(items);
        }

    }

    @Override
    public ItemStack getItem(int index) {
        return items.get(index);
    }


    @Override
    public int getContainerSize() {
        return this.size;
    }

    @Override
    public void setChanged() {
        deck.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(items));
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        ItemStack itemstack = ContainerHelper.removeItem(items, index, count);
        setChanged();
        return itemstack;
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        ItemStack stack = ContainerHelper.takeItem(items, index);
        setChanged();
        return stack;
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        items.set(index, stack);
        setChanged();
    }

    @Override
    public void clearContent() {
        items.clear();
        setChanged();
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }


    @Override
    public void stopOpen(Player player) {
        setChanged();
    }
}
