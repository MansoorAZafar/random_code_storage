package com.ha.hidearmor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = HideArmor.MODID, version = HideArmor.VERSION, clientSideOnly = true)

@SideOnly(Side.CLIENT)
public class HideArmor {
    public static final String MODID = "HideArmor";
    public static final String VERSION = "1.0";

    @EventHandler
    public void preInit(FMLInitializationEvent event) {
        HideArmorConfig.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new HideArmorRenderer());
        ClientCommandHandler.instance.registerCommand(new HideArmorCommand());
    }
}