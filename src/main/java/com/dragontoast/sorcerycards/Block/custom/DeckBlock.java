package com.dragontoast.sorcerycards.Block.custom;

import com.dragontoast.sorcerycards.Block.ModBlocks;
import com.dragontoast.sorcerycards.Entity.ModBlockEntities;
import com.dragontoast.sorcerycards.Entity.custom.DeckBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DeckBlock extends Block implements EntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final ResourceLocation CONTENTS = new ResourceLocation("contents");

    public DeckBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
        );
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state){
        return new DeckBlockEntity(pos, state);
    }

    @Override
    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        return switch (direction){
            case DOWN, UP, NORTH, SOUTH -> Block.box(6, 0, 5, 10, 1, 11);
            case WEST, EAST ->  Block.box(5, 0, 6, 11, 1, 10);
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return super.getStateForPlacement(pContext).setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        if (pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        } else if (pPlayer.isSpectator()) {
            return InteractionResult.CONSUME;
        } else if (pLevel.getBlockEntity(pPos) instanceof DeckBlockEntity deckBlockEntity) {
            pPlayer.openMenu(deckBlockEntity);
            return InteractionResult.CONSUME;
        } else {
            return InteractionResult.PASS;
        }
    }
    @Override
    public BlockState playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (blockentity instanceof DeckBlockEntity deckBlockEntity) {
            if (!pLevel.isClientSide && pPlayer.isCreative() && !deckBlockEntity.isEmpty()) {
                ItemStack itemstack = ModBlocks.DECK.toStack();
                itemstack.applyComponents(blockentity.collectComponents());
                ItemEntity itementity = new ItemEntity(
                        pLevel, (double)pPos.getX() + 0.5, (double)pPos.getY() + 0.5, (double)pPos.getZ() + 0.5, itemstack
                );
                itementity.setDefaultPickUpDelay();
                pLevel.addFreshEntity(itementity);
            } else {
                deckBlockEntity.unpackLootTable(pPlayer);
            }
        }

        return super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
    }

    @Override
    protected void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.is(pNewState.getBlock())) {
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            if (blockentity instanceof DeckBlockEntity) {
                pLevel.updateNeighbourForOutputSignal(pPos, pState.getBlock());
            }

            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }

    @Override
    protected List<ItemStack> getDrops(BlockState pState, LootParams.Builder pParams) {
        BlockEntity blockentity = pParams.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (blockentity instanceof DeckBlockEntity deckBlockEntity) {
            pParams = pParams.withDynamicDrop(CONTENTS, p_56219_ -> {
                for (int i = 0; i < deckBlockEntity.getContainerSize(); i++) {
                    p_56219_.accept(deckBlockEntity.getItem(i));
                }
            });
        }

        return super.getDrops(pState, pParams);
    }
}
