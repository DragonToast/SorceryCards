package com.dragontoast.sorcerycards.Item.custom;

import com.dragontoast.sorcerycards.Entity.custom.DeckBlockEntity;
import com.dragontoast.sorcerycards.Menu.custom.DeckMenu;
import net.minecraft.client.particle.FireworkParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ShulkerBoxMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public class DeckItem extends BlockItem {

    public DeckItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(pPlayer.isShiftKeyDown()){

        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }


    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Player pPlayer = pContext.getPlayer();
        if(pPlayer != null && pContext.getPlayer().isShiftKeyDown()){
            pContext.getLevel().addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, pPlayer.position().x, pPlayer.position().y, pPlayer.position().z,0,0,0);
            return InteractionResult.FAIL;
        }
        else {
            return super.useOn(pContext);
        }

    }

}
