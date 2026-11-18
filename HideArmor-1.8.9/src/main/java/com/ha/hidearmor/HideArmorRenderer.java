package com.ha.hidearmor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class HideArmorRenderer {
    
    private static final int NumberOfArmorPieces = 4;
    private ItemStack[] playerArmor = new ItemStack[NumberOfArmorPieces];

    @SubscribeEvent 
    public void onRenderPlayerPre(RenderPlayerEvent.Pre event) {
        if (!HideArmorConfig.getToggle()) {
            return;
        }
        
        final Minecraft mc = Minecraft.getMinecraft();
        final EntityPlayerSP player = mc.thePlayer;

        if (event.entityPlayer.equals(player)) {
            final ItemStack[] armorInventory = player.inventory.armorInventory;

            for (int i = 0; i < NumberOfArmorPieces; ++i) {
                this.playerArmor[i] = armorInventory[i];
                armorInventory[i] = null;
            }
        }
    }

    @SubscribeEvent 
    public void onRenderPlayerPost(RenderPlayerEvent.Post event) {
        if (!HideArmorConfig.getToggle()) {
            return;
        }
        
        final Minecraft mc = Minecraft.getMinecraft();
        final EntityPlayerSP player = mc.thePlayer;

        if (event.entityPlayer.equals(player)) {
            final ItemStack[] armorInventory = player.inventory.armorInventory;

            for (int i = 0; i < NumberOfArmorPieces; ++i) {
                armorInventory[i] = this.playerArmor[i];
                this.playerArmor[i] = null;
            }
        }
    }
}
