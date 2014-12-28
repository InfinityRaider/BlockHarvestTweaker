package com.InfinityRaider.BlockHarvestTweaker;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class HarvestCheckEventHandler {
    @SubscribeEvent(priority= EventPriority.HIGHEST)
    public void harvestCheckEvent(PlayerEvent.HarvestCheck event) {
        ItemStack stack = event.entityPlayer.getCurrentEquippedItem();
        int reqLevel = event.block.getHarvestLevel();

        if (block.getMaterial().isToolNotRequired())
        {
            return true;
        }

        ItemStack stack = player.inventory.getCurrentItem();
        String tool = block.getHarvestTool(metadata);
        if (stack == null || tool == null)
        {
            return player.canHarvestBlock(block);
        }

        int toolLevel = stack.getItem().getHarvestLevel(stack, tool);
        if (toolLevel < 0)
        {
            return player.canHarvestBlock(block);
        }

        return toolLevel >= block.getHarvestLevel(metadata);
    }
}
