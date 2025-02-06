package com.dragontoast.sorcerycards.Entity.custom;

import com.dragontoast.sorcerycards.Entity.ModBlockEntities;
import com.dragontoast.sorcerycards.Item.components.ModDataComponents;
import com.dragontoast.sorcerycards.Menu.custom.DeckMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.inventory.ShulkerBoxMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import java.util.stream.IntStream;

public class DeckBlockEntity extends RandomizableContainerBlockEntity {
    private NonNullList<ItemStack> itemStacks = NonNullList.withSize(54, ItemStack.EMPTY);

    public DeckBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DECK_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.sorcerycards.deck");
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.itemStacks;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> pItems) {
        this.itemStacks = pItems;
    }

    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return new DeckMenu(pContainerId, pInventory, this);
    }

    @Override
    public int getContainerSize() {
        return this.itemStacks.size();
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        this.itemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        this.loadFromTag(pTag, pRegistries);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
        if (!this.trySaveLootTable(pTag)) {
            ContainerHelper.saveAllItems(pTag, this.itemStacks, false, pRegistries);
        }
    }

    public void loadFromTag(CompoundTag pTag, HolderLookup.Provider pLevelRegistry) {
        this.itemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(pTag) && pTag.contains("Items", 9)) {
            ContainerHelper.loadAllItems(pTag, this.itemStacks, pLevelRegistry);
        }
    }

    @Override
    public boolean canPlaceItem(int pSlot, ItemStack pStack) {
        if (pStack.has(ModDataComponents.CARD)){
            int row = pSlot/13;
            int column = pSlot - row*13;
            boolean rightSuit = pStack.get(ModDataComponents.CARD.get()).suitValue() == row;
            boolean rightValue = pStack.get(ModDataComponents.CARD.get()).value() == column;
            if (pStack.get(ModDataComponents.CARD.get()).suitValue() == 4 && row == 4){
                return true;
            }
            return rightSuit && rightValue;
        }
        return false;
    }
}