package com.dragontoast.sorcerycards.Entity.custom;

import com.dragontoast.sorcerycards.Entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DeckBlockEntity extends BlockEntity {
    public DeckBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DECK_BLOCK_ENTITY.get(), pos, state);
    }
}
